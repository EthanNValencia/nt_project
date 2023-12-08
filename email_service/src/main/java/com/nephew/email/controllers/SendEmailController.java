package com.nephew.email.controllers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.email.dto.EmailDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@RestController
@RequestMapping("/api/v1/public")
public class SendEmailController {
	private static final String SENDGRID = System.getenv("SENDGRID_API_KEY");

	@PostMapping("/send")
	public ResponseEntity<Void> sendEmail(@RequestBody EmailDto emailDto) throws IOException {
		System.out.println(emailDto);
		Email from = new Email();
		
		if(emailDto.getFrom() == null) {
			from = new Email("nephewtechnologies@gmail.com");
		} else {
			from = new Email(emailDto.getFrom());
		}
		
		Email to = new Email();
		if(emailDto.getTo() == null) {
			to = new Email("nephewtechnologies@gmail.com");
		} else {
			to = new Email(emailDto.getFrom());
		}
		
		String subject = emailDto.getSubject();
		Content content = new Content("text/html", emailDto.getHtml());
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(SENDGRID);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

}
