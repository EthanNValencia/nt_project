package com.nephew.es.errorservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.es.errorservice.entities.InvalidLoginAttempt;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/local")
public class LocalController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalController.class);


	@PostMapping("/invalid-login")
	public ResponseEntity<Void> reportError(HttpServletRequest request, @RequestBody InvalidLoginInfo invalidLogin) {
		String clientIp = request.getRemoteAddr();
		InvalidLoginAttempt error = new InvalidLoginAttempt();
		error.setContentType(invalidLogin.contentType);
		error.setEmail(invalidLogin.email);
		error.setLocalAddress(invalidLogin.localAddress);
		error.setMethod(invalidLogin.method);
		error.setLocalAddress(invalidLogin.localAddress);
		error.setPassword(invalidLogin.password);
		logger.info("Invalid login attempt was recieved from: " + clientIp);
		logger.info("Error details: " + error.toString());
		try {
			// frontEndErrorService.saveNewFrontEndError(error);
		} catch (Exception ex) {
			logger.warn("An error occured while trying to save the data!");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// String url = "http://localhost:8765/error-service/api/v1/localhost/invalid-login"; 
}

class InvalidLoginInfo {
	public String contentType;
	public String remoteAddress;
	public String method;
	public String localAddress;
	public String email;
	public String password;
}