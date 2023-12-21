package com.nephew.website.controllers;


import com.nephew.website.dtos.WebsiteDto;
import com.nephew.website.entities.Website;
import com.nephew.website.services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	@Autowired
	private WebsiteService websiteService;

	// This is good for testing for high latency situations on the front end. 
	private void sleepApi() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("{companyUrl}/website/")
	public ResponseEntity<WebsiteDto> getAllEmployees(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<WebsiteDto>(websiteService.getWebsiteDto(companyUrl), HttpStatus.OK);
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

}
