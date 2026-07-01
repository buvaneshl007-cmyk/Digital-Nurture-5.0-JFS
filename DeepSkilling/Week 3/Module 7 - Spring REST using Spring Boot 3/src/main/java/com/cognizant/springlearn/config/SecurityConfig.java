package com.cognizant.springlearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cognizant.springlearn.security.JwtRequestFilter;

/**
 * Secures the application with Spring Security.
 *
 * - /authenticate is open (antMatcher permitAll) so a client can exchange
 *   HTTP Basic credentials for a JWT.
 * - Every other endpoint requires a valid "Authorization: Bearer <jwt>"
 *   header, enforced by JwtRequestFilter.
 * - "user" / "pwd" is defined as an in-memory user, matching the sample
 *   curl command: curl -s -u user:pwd http://localhost:8090/authenticate
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JwtRequestFilter jwtRequestFilter;

	public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// In-memory authentication, equivalent in spirit to the classic
	// AuthenticationManagerBuilder.inMemoryAuthentication() approach.
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails demoUser = User.withUsername("user")
				.password(passwordEncoder.encode("pwd"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(demoUser);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> authorize
				// URL authorization configuration (antMatchers-style rules)
				.requestMatchers("/authenticate").permitAll()
				.anyRequest().authenticated()
			)
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
