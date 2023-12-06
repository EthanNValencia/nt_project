package com.common.model.generic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.model.generic.entities.Page;
import com.common.model.generic.entities.PageText;
import com.common.model.generic.entities.Website;
import com.common.model.generic.repositories.PageRepository;
import com.common.model.generic.repositories.WebsiteRepository;

@Service
public class WebsiteService {
	
	@Autowired
	private WebsiteRepository websiteRepo;
	
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
