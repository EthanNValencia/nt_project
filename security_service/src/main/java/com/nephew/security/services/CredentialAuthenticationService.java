package com.nephew.security.services;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nephew.security.entities.PendingCredential;
import com.nephew.security.entities.Role;
import com.nephew.security.repositories.CredentialRepository;

@Service
public class CredentialAuthenticationService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CredentialRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SendEmailService sendEmail;

	@Deprecated
	public Token registerUser(RegisterRequest request) {
		Credential user = new Credential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCompanyName(request.getCompanyName());
		user.setRole(Role.USER);
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		Token token = new Token();
		token.setToken(jwtToken);
		return token;
	}
	
	@Deprecated
	public Token registerAdmin(RegisterRequest request) {
		Credential user = new Credential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCompanyName(request.getCompanyName());
		user.setRole(Role.ADMIN);
		userRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		Token token = new Token();
		token.setToken(jwtToken);
		return token;
	}
	
	public void saveApprovedCredential(PendingCredential pendingCredential) {
		Credential user = new Credential();
		user.setFirstName(pendingCredential.getFirstName());
		user.setLastName(pendingCredential.getLastName());
		user.setEmail(pendingCredential.getEmail());
		user.setPassword(pendingCredential.getPassword());
		user.setCompanyName(pendingCredential.getCompanyName());
		user.setRole(pendingCredential.getRole());
		user = userRepository.save(user);
		sendEmail.credentialsApproved(user);
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
