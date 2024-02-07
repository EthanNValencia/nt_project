package com.nephew.security.controllers;

import org.apache.http.auth.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.security.dto.Action;
import com.nephew.security.dto.AuthenticationRequest;
import com.nephew.security.dto.InvalidLogin;
import com.nephew.security.dto.RegisterRequest;
import com.nephew.security.dto.Token;
import com.nephew.security.services.CredentialAuthenticationService;
import com.nephew.security.services.RegistrationService;
import com.nephew.security.services.ErrorService;

import jakarta.servlet.http.HttpServletRequest;

// http://localhost:8000/api/v1/public/health
@RestController
@RequestMapping("/api/v1/public")
public class SecurityController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private ErrorService reportingService;

	@Autowired
	private CredentialAuthenticationService authenticationService;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		try {
			registrationService.savePendingCredential(request);
			return ResponseEntity.ok("Credentials were created.");
		} catch (InvalidCredentialsException ice) {
			String errorMessage = ice.getMessage(); 
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

	@PostMapping("/register/approve/{code}")
	public ResponseEntity<String> approvePendingCredentials(@PathVariable(value = "code") String code) {
		logger.info("Recieved request to approve code: " + code);
		try {
			registrationService.approvePendingCredentials(code);
			return ResponseEntity.ok("Credentials were successfully approved.");
		} catch (InvalidCredentialsException ice) {
			String errorMessage = ice.getMessage(); 
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

	@PostMapping("/authenticate")
	public ResponseEntity<Token> authenticate(HttpServletRequest httpServletRequest,
			@RequestBody AuthenticationRequest request) throws BadCredentialsException {
		Token token;
		try {
			token = authenticationService.generateToken(request);
		} catch (BadCredentialsException bce) {
			InvalidLogin invalidLogin = new InvalidLogin();
			invalidLogin.contentType = httpServletRequest.getContentType();
			invalidLogin.remoteAddress = httpServletRequest.getRemoteAddr();
			invalidLogin.method = httpServletRequest.getMethod();
			invalidLogin.localAddress = httpServletRequest.getLocalAddr();
			invalidLogin.email = request.getEmail();
			invalidLogin.password = request.getPassword();
			reportingService.reportInvalidLogin(invalidLogin);
			throw new BadCredentialsException("Credentials provided were invalid.");
		}
		return ResponseEntity.ok(token);
	}

	@GetMapping("/validate/{jwt}")
	public ResponseEntity<Boolean> validateToken(@PathVariable(value = "jwt") String jwt) {
		logger.info("Request recieved to validate token: " + jwt);
		Token token = new Token();
		token.setToken(jwt);
		Boolean validated = authenticationService.validateToken(token);
		if (validated) {
			logger.info("Token was validated!");
		} else {
			logger.warn("Token was not validated!");
		}
		return ResponseEntity.ok(validated);
	}

	@PostMapping("/validate-action/{jwt}")
	public ResponseEntity<Boolean> validateTokenAndAction(@PathVariable(value = "jwt") String jwt,
			@RequestBody Action action) {
		logger.info("Request recieved to validate token: " + jwt);
		Token token = new Token();
		token.setToken(jwt);
		Boolean validated = authenticationService.validateToken(token, action);
		if (validated) {
			logger.info("Token was validated!");
		} else {
			logger.warn("Token was not validated!");
		}
		return ResponseEntity.ok(validated);
	}

}
