package com.cognizant.springlearn.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Utility responsible for generating and validating JSON Web Tokens.
 *
 * JWT structure: header.payload.signature (each part is Base64 encoded).
 * - header    -> algorithm & token type
 * - payload   -> claims, e.g. subject (username), issued-at, expiry
 * - signature -> HMAC of header+payload using a server-side secret key,
 *                used to detect tampering
 */
@Component
public class JwtUtil {

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	// Demo signing key only. In a real application, load this from a secure
	// secrets store / environment variable instead of hard-coding it.
	private static final String SECRET = "cognizant-spring-learn-demo-jwt-secret-key-please-change-me";
	private static final long EXPIRATION_MILLIS = 20 * 60 * 1000; // 20 minutes

	private final Key signingKey = Keys.hmacShaKeyFor(SECRET.getBytes());

	/**
	 * Step 3 of the authentication flow: generate a signed JWT for the
	 * given (already authenticated) username, with an expiry set.
	 */
	public String generateToken(String username) {
		log.info("Entering generateToken() method for user={}", username);

		Date issuedAt = new Date();
		Date expiry = new Date(issuedAt.getTime() + EXPIRATION_MILLIS);

		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(issuedAt)
				.setExpiration(expiry)
				.signWith(signingKey, SignatureAlgorithm.HS256)
				.compact();

		log.info("Exiting generateToken() method");
		return token;
	}

	public String extractUsername(String token) {
		return parseClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			Claims claims = parseClaims(token);
			return claims.getExpiration().after(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			log.debug("JWT validation failed: {}", e.getMessage());
			return false;
		}
	}

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(signingKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
