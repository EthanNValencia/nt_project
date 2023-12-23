package com.nephew.faqs.controllers;

import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.services.FAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class WebsitePrivateController {

	@Autowired
	private FAQsService faqsService;

	@GetMapping("{companyUrl}/faqs/")
	public ResponseEntity<List<FAQs>> getAllFaqs(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<>(faqsService.getAll(companyUrl), HttpStatus.OK);
	}

	@DeleteMapping("{companyUrl}/faqs/{id}")
	public ResponseEntity<Void> deleteFaqById(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") Long id) {
		faqsService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("{companyUrl}/faqs/")
	public ResponseEntity<FAQs> updateFaq(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		return new ResponseEntity<>(faqsService.saveFaq(faqs), HttpStatus.OK);
	}

}
