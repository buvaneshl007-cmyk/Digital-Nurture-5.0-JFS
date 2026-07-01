package com.cognizant.springlearn.security;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Spring filter that runs once per request. Reads the "Authorization: Bearer <token>"
 * header, validates the JWT, and - if valid - populates the SecurityContext so
 * downstream authorization rules (see SecurityConfig) treat the request as authenticated.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtUtil jwtUtil;

	public JwtRequestFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

			String token = authorizationHeader.substring(BEARER_PREFIX.length());

			if (jwtUtil.isTokenValid(token)) {
				String username = jwtUtil.extractUsername(token);

				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);

				log.debug("Authorized request for user={}", username);
			} else {
				log.debug("Rejected request: invalid or expired JWT");
			}
		}

		filterChain.doFilter(request, response);
	}

}
