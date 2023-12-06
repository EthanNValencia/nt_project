package com.nephew.es.errorservice.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nephew.es.errorservice.entities.FrontEndError;
import com.nephew.es.errorservice.services.FrontEndErrorService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

	@Autowired
	private FrontEndErrorService frontEndErrorService;

	@PostMapping("/error/")
	public ResponseEntity<Void> reportError(HttpServletRequest request, @RequestBody Root root) {
		String clientIp = request.getRemoteAddr();
		FrontEndError error = new FrontEndError();
		error.setName(root.name);
		error.setCode(root.code);
		error.setMessage(root.message);
		error.setMethod(root.config.method);
		error.setUrl(root.config.url);
		error.setData(root.config.data);
		error.setClientIp(clientIp);
		if (root.response != null) {
			error.setStatus(root.response.status);
			error.setStatusText(root.response.statusText);
		}
		error.setRequestHeaders("Empty for now.");
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);
		error.setFormattedDateTime(formattedDateTime);
		logger.info("New error was recieved from: " + clientIp);
		logger.info("Error details: " + error.toString());
		try {
			frontEndErrorService.saveNewFrontEndError(error);
		} catch (Exception ex) {
			logger.warn("An error occured while trying to save the data!");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

	@PostMapping
	public ResponseEntity<Void> invalidLoginAttempt(HttpServletRequest request, InvalidLogin invalidLogin) {
		String clientIp = request.getRemoteAddr();

		try {

		} catch (Exception ex) {
			logger.warn("An error occured while trying to save the data!");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}

class InvalidLogin {
	public String clientIp;
	public String methodRequest;

}

class Config {
	public Transitional transitional;
	public ArrayList<String> adapter;
	public ArrayList<Object> transformRequest;
	public ArrayList<Object> transformResponse;
	public int timeout;
	public String xsrfCookieName;
	public String xsrfHeaderName;
	public int maxContentLength;
	public int maxBodyLength;
	public Env env;
	public Headers headers;
	public String method;
	public String url;
	public String data;
}

class Data {
	public Date beginTime;
	public Date endTime;
}

class Env {
}

class Headers {
	@JsonProperty("Accept")
	public String accept;
	@JsonProperty("Content-Type")
	public String contentType;
}

class Request {
}

class Response {
	public Data data;
	public int status;
	public String statusText;
	public Headers headers;
	public Config config;
	public Request request;
}

class Root {
	public String stack;
	public String message;
	public String name;
	public String code;
	public Config config;
	public Request request;
	public Response response;
}

class Transitional {
	public boolean silentJSONParsing;
	public boolean forcedJSONParsing;
	public boolean clarifyTimeoutError;
}
