package com.cognizant.springlearn.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.security.JwtUtil;

/**
 * Hands on: Create authentication service that returns JWT.
 *
 * GET /authenticate, credentials supplied via HTTP Basic (-u user:pwd), e.g:
 *   curl -s -u user:pwd http://localhost:8090/authenticate
 * Response:
 *   {"token":"..."}
 *
 * Implemented in 3 steps:
 *   1. This controller is wired into SecurityConfig, which permits
 *      unauthenticated access to /authenticate only.
 *   2. The Authorization header is read and the Base64-encoded
 *      "username:password" credentials are decoded manually.
 *   3. The decoded credentials are authenticated, and if valid, a JWT is
 *      generated for that user and returned.
 */
@RestController
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String BASIC_PREFIX = "Basic ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		log.info("Entering authenticate() method");

		if (authorizationHeader == null || !authorizationHeader.startsWith(BASIC_PREFIX)) {
			log.info("Exiting authenticate() method - missing/invalid Authorization header");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "Missing or invalid Authorization header"));
		}

		// Step 2: Read Authorization header and decode username/password
		String base64Credentials = authorizationHeader.substring(BASIC_PREFIX.length()).trim();
		String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
		String[] parts = credentials.split(":", 2);
		String username = parts[0];
		String password = parts.length > 1 ? parts[1] : "";

		// Step 3: Authenticate the decoded credentials, then generate a token
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException | AuthenticationException e) {
			log.warn("Authentication failed for user={}", username);
			log.info("Exiting authenticate() method - authentication failed");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "Invalid username or password"));
		}

		String token = jwtUtil.generateToken(username);

		log.info("Exiting authenticate() method - token issued");
		return ResponseEntity.ok(Collections.singletonMap("token", token));
	}

}
