package com.nephew.ss.ntsecurityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthFilter {

	public AuthFilter(AuthenticationProvider authenticationProvider) {
		super();
		this.authenticationProvider = authenticationProvider;
	}

	private final AuthenticationProvider authenticationProvider;

	private final String[] permittedEndpoints = {"/api/v1/auth/**", "/api/v1/public/**"};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println(http.toString());
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth -> auth
                .requestMatchers("/error").permitAll()
                .requestMatchers("/api/v1/public/**").permitAll()
                .requestMatchers("/security-service/api/v1/public/**").permitAll()
                .anyRequest().authenticated()) // permitAll for now? 
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider);
		return http.build();
	}
	
}
