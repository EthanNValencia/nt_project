package com.nephew.security.services;

import java.util.Optional;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nephew.security.dto.RegisterRequest;
import com.nephew.security.entities.Credential;
import com.nephew.security.entities.PendingCredential;
import com.nephew.security.repositories.CredentialRepository;
import com.nephew.security.repositories.PendingCredentialRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private PendingCredentialRepository pendingCredRepo;
	
	@Autowired
	private CredentialRepository credRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private CredentialAuthenticationService credAuthService;
	
	public void savePendingCredential(RegisterRequest request) throws InvalidCredentialsException {
		PendingCredential user = new PendingCredential();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCompanyName(request.getCompanyName());
		user.setRole(request.getRole());
		
		if(!checkIfCredentialEmailExists(request.getEmail())) {
			user = pendingCredRepo.save(user);
			user.generatePendingCode();
			user.generateApprovalCode();
			user = pendingCredRepo.save(user);
			sendEmailService.credentialsPending(user);
		} else {
			throw new InvalidCredentialsException("Credentials for " + request.getEmail() + " already exist.");
		}
		
	}
	
	public boolean checkIfCredentialEmailExists(String email) {
		Optional<Credential> credential = credRepo.findByEmail(email);
		if(credential.isEmpty()) {
			return false;
		}
		if(credential.isPresent()) {
			return true;
		}
		return true;
	}

	public void approvePendingCredentials(String code) throws InvalidCredentialsException {
		Optional<PendingCredential> user = pendingCredRepo.findByPendingCode(code);
		if(user.isEmpty()) {
			user = pendingCredRepo.findByApprovalCode(code);
		}
		
		if(user.isPresent()) {
			credAuthService.saveApprovedCredential(user.get());
			pendingCredRepo.delete(user.get());
		} else {
			throw new InvalidCredentialsException("Credentials were not found.");
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