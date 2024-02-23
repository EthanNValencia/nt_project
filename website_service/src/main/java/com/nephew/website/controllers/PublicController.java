package com.nephew.website.controllers;


import com.nephew.website.dtos.WebsiteDto;
import com.nephew.website.entities.Website;
import com.nephew.website.services.WebsiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

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

	// http://localhost:8765/website-service/api/v1/public/npt/website/test
	@GetMapping(value = "{companyUrl}/website/test")
	public ResponseEntity<WebsiteDto> getWebsiteDto(@PathVariable(value = "companyUrl") String companyUrl) {
		logger.info("Endpoint hit: /{}/website/", companyUrl);
		return new ResponseEntity<WebsiteDto>(websiteService.getWebsiteDto(companyUrl), HttpStatus.OK);
	}

	// http://localhost:8765/website-service/api/v1/public/npt/website/
	@GetMapping(value = "{companyUrl}/website/")
	public ResponseEntity<Website> getWebsite(@PathVariable(value = "companyUrl") String companyUrl) {
		logger.info("Endpoint hit: /{}/website/", companyUrl);
		return new ResponseEntity<Website>(websiteService.getWebsiteByCompanyUrl(companyUrl), HttpStatus.OK);
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

}
