package com.nephew.security.services;

import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nephew.security.entities.Credential;
import com.nephew.security.entities.PendingCredential;

import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Service
public class SendEmailService {
	
    private static Logger logger = Logger.getLogger(SendEmailService.class.getName());
	
	@Autowired
	private RestTemplate restTemplate;

	public void credentialsPending(PendingCredential user) {
		EmailDto email = new EmailDto();
		email.setTo(null);
		email.setHtml(user.toString());
		email.setSubject("New Pending User: Approval Requested");
		sendEmailToService(email);
	}
	
	public void credentialsApproved(Credential user) {
		EmailDto email = new EmailDto();
		email.setTo(user.getEmail());
		email.setSubject("Your account has been approved!");
		email.setHtml("Please go back to localhost:4001/login to access the admin panel.");
		sendEmailToService(email);
	}
	
	public void sendEmailToService(EmailDto email) {
	    String url = "http://localhost:8765/email-service/api/v1/public/send";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmailDto> requestEntity = new HttpEntity<>(email, headers);
	    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
	    if (responseEntity.getStatusCode() == HttpStatus.OK) {
	        logger.info("Email sent successfully");
	    } else {
	    	logger.warning("Failed to send email. Status code: " + responseEntity.getStatusCode());
	    }
	}
 	
}

class EmailDto {

	private String to;
	private String from;
	private String html;
	private String subject;

	public EmailDto() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "EmailDto [to=" + to + ", from=" + from + ", html=" + html + ", subject=" + subject + "]";
	}
}