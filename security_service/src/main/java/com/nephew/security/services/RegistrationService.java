package com.nephew.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nephew.security.dto.RegisterRequest;
import com.nephew.security.entities.Credential;
import com.nephew.security.entities.PendingCredential;
import com.nephew.security.entities.Role;
import com.nephew.security.repositories.PendingCredentialRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private PendingCredentialRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private CredentialAuthenticationService credAuthService;
	
	public void savePendingCredential(RegisterRequest request) {
		PendingCredential user = new PendingCredential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setServiceName(request.getServiceName());
		user.setRole(request.getRole());
		user = repo.save(user);
		user.generatePendingCode();
		user = repo.save(user);
		sendEmailService.credentialsPending(user);
	}

	public void approvePendingCredentials(String code) {
		Optional<PendingCredential> user = repo.findByPendingCode(code);
		if(user.isPresent()) {
			credAuthService.saveApprovedCredential(user.get());
		} else {
			
		}
	}
	
	
}

/*
doesn't work
if (request.getRole().equals(Role.USER.toString().toLowerCase())) {
	user.setRole(Role.USER);
} else if (request.getRole().equals(Role.ADMIN.toString().toLowerCase())) {
	user.setRole(Role.ADMIN);
} else if (request.getRole().equals(Role.ERROR.toString().toLowerCase())) {
	user.setRole(Role.ERROR);
} else if (request.getRole().equals(Role.SUPER.toString().toLowerCase())) {
	user.setRole(Role.SUPER);
}
*/