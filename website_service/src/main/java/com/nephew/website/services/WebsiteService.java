package com.nephew.website.services;


import com.nephew.website.dtos.WebsiteDto;
import com.nephew.website.repositories.PageRepository;
import com.nephew.website.repositories.WebsiteSocialMediaProfileRepository;
import com.nephew.website.entities.Page;
import com.nephew.website.entities.Website;
import com.nephew.website.repositories.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {
	
	@Autowired
	private WebsiteRepository websiteRepository;
	
	@Autowired
	private WebsiteSocialMediaProfileRepository websiteSocialMediaProfileRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	public Website getFirstWebsite() {
		Website website = websiteRepository.findAll().getFirst();
		return website;
	}
	
	public void assignWebsiteToChildren(Website website) {
		website.getProfile().setWebsite(website);
		for(Page page: website.getPages()) {
			page.setWebsite(website);
		}
	}

	public Website updateWebsite(Website website) {
		assignWebsiteToChildren(website);
		Website updatedWebsite = websiteRepository.save(website);
		// WebsiteSocialMediaProfile profile = new WebsiteSocialMediaProfile();
		// profile.setWebsite(updatedWebsite);
		// websiteSocialMediaProfileRepository.save(profile);
		return updatedWebsite;
	}

	public Website createPage(Website website) {
		website = websiteRepository.findById(website.getId()).get();
		Page page = new Page();
		page.setWebsite(website);
		website.getPages().add(page);
		assignWebsiteToChildren(website);
		return websiteRepository.save(website);
	}

	public Website createParagraph(Page page) {
		page = pageRepository.findById(page.getId()).get();
		pageRepository.save(page);
		Website website = websiteRepository.findById(page.getWebsite().getId()).get();
		return website;
	}

	public WebsiteDto getWebsiteDto(String companyUrl) {
		Website website = websiteRepository.findByCompanyUrl(companyUrl);
		WebsiteDto websiteDto = new WebsiteDto(website);
		return websiteDto;
	}

	public Website getWebsiteByCompanyUrl(String companyUrl) {
		Website website = websiteRepository.findByCompanyUrl(companyUrl);
		return website;
	}
}
