package com.nephew.oesa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.website.Page;
import com.nephew.oesa.entities.website.PageText;
import com.nephew.oesa.entities.website.Website;
import com.nephew.oesa.entities.website.WebsiteSocialMediaProfile;
import com.nephew.oesa.repositories.PageRepository;
import com.nephew.oesa.repositories.WebsiteRepository;
import com.nephew.oesa.repositories.WebsiteSocialMediaProfileRepository;

@Service
public class WebsiteService {
	
	@Autowired
	private WebsiteRepository websiteRepo;
	
	@Autowired
	private WebsiteSocialMediaProfileRepository websiteSocialMediaProfileRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	public Website getWebsite() {
		Website website = websiteRepo.findAll().get(0);
		return website;
	}
	
	public void assignWebsiteToChildren(Website website) {
		website.getProfile().setWebsite(website);
		for(Page page: website.getPages()) {
			page.setWebsite(website);
			for(PageText pageTexts : page.getPageTexts()) {
				pageTexts.setPage(page);
			}
		}
	}

	public Website updateWebsite(Website website) {
		assignWebsiteToChildren(website);
		Website updatedWebsite = websiteRepo.save(website);
		// WebsiteSocialMediaProfile profile = new WebsiteSocialMediaProfile();
		// profile.setWebsite(updatedWebsite);
		// websiteSocialMediaProfileRepository.save(profile);
		return updatedWebsite;
	}

	public Website createPage(Website website) {
		website = websiteRepo.findById(website.getId()).get();
		Page page = new Page();
		page.setWebsite(website);
		website.getPages().add(page);
		assignWebsiteToChildren(website);
		return websiteRepo.save(website);
	}

	public Website createParagraph(Page page) {
		page = pageRepository.findById(page.getId()).get();
		PageText pageText = new PageText();
		pageText.setPage(page);
		page.getPageTexts().add(pageText);
		pageRepository.save(page);
		Website website = websiteRepo.findById(page.getWebsite().getId()).get();
		return website;
	}
}
