package com.nephew.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nephew.security.dto.InvalidLogin;

@Service
public class ErrorService {

	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	public void reportInvalidLogin(InvalidLogin invalidLogin) {
		
		String url = "http://localhost:8765/error-service/api/v1/local/invalid-login"; 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
		    String json = objectMapper.writeValueAsString(invalidLogin);
			HttpEntity<String> request = new HttpEntity<>(json, headers);
			// restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		} catch (JsonProcessingException e) {
		    e.printStackTrace();
		}
	}
}
