package com.nephew.website.controllers;

import com.nephew.website.entities.Page;
import com.nephew.website.entities.Website;
import com.nephew.website.services.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class PrivateController {

	@Autowired
	private WebsiteService websiteService;

	@GetMapping("{companyUrl}/website/")
	public ResponseEntity<Website> getWebsite(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<Website>(websiteService.getFirstWebsite(), HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/website/")
	public ResponseEntity<Website> updateWebsite(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.updateWebsite(website), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/website/pages") 
	public ResponseEntity<Website> createPage(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.createPage(website), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/website/pages/paragraph") 
	public ResponseEntity<Website> createParagraph(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Page page) {
		return new ResponseEntity<Website>(websiteService.createParagraph(page), HttpStatus.OK);
	}

}
