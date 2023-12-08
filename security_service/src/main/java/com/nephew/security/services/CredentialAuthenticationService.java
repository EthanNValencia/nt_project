package com.nephew.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nephew.security.config.JwtService;
import com.nephew.security.dto.Action;
import com.nephew.security.dto.AuthenticationRequest;
import com.nephew.security.dto.RegisterRequest;
import com.nephew.security.dto.Token;
import com.nephew.security.entities.Credential;
import com.nephew.security.entities.Role;
import com.nephew.security.repositories.CredentialRepository;

@Service
public class CredentialAuthenticationService {

	public CredentialAuthenticationService(PasswordEncoder passwordEncoder, CredentialRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	private final PasswordEncoder passwordEncoder;
	private final CredentialRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public Token registerUser(RegisterRequest request) {
		Credential user = new Credential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setServiceName(request.getServiceName());
		user.setRole(Role.USER);
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		Token token = new Token();
		token.setToken(jwtToken);
		return token;
	}
	
	public Token registerAdmin(RegisterRequest request) {
		Credential user = new Credential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setServiceName(request.getServiceName());
		user.setRole(Role.ADMIN);
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		Token token = new Token();
		token.setToken(jwtToken);
		return token;
	}

	public Token generateToken(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())); // This will throw an exception if the user is not auth. 
		Credential user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User with username/email:" + request.getEmail() + " not found."));
		String jwtToken = jwtService.generateToken(user);
		Token token = new Token();
		token.setToken(jwtToken);
		return token;
	}
	
	public Boolean verifyRole(Credential user, Action action) {
		if(user.getRole().equals(Role.SUPER)) {
			return true;
		}
		if(user.getRole().equals(action.getRole())) {
			return true;
		}
		return false;
	}
	
	public Boolean validateToken(Token token, Action action) {
		String username = jwtService.extractUsername(token.getToken());
		Credential user = userRepository.findByEmail(username).get();
		if(verifyRole(user, action)) {
			return jwtService.isTokenValid(token.getToken(), user);
		}
		System.out.println("Failed to validate role: " + user.getRole() + " with " + action.getRole());
		return false; 
	}
	
	public Boolean validateToken(Token token) {
		String username = jwtService.extractUsername(token.getToken());
		Credential user = userRepository.findByEmail(username).get();
		return jwtService.isTokenValid(token.getToken(), user);
	}

}
