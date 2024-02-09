package com.nephew.website;

import com.nephew.website.entities.*;
import com.nephew.website.repositories.CompanyRepository;
import com.nephew.website.repositories.WebsiteRepository;
import com.nephew.website.repositories.WebsiteSocialMediaProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class WebsiteServiceApplicationTests {
	private final long WEBSITE_ID = 1L;
	private final long COMPANY_ID = 1L;
	private final String COMPANY_URL = "npt";
	private final long WEBSITE_PROFILE_ID = 1L;
	private final String WEBSITE_NAME = "Nephew Physical Therapy";
	private final String WEBSITE_URL = "/";
	private final String WEBSITE_PROFILE_YOUTUBE = "https://www.youtube.com/channel/UCP0_SudzP9_KQQKsUXOJPRg";
	private final String WEBSITE_PROFILE_FACEBOOK = "https://www.facebook.com/nephewpt";
	private final String WEBSITE_PROFILE_INSTAGRAM = "https://www.instagram.com/nephewpt/";
	private final String WEBSITE_PROFILE_YELP = "https://www.yelp.com/biz/nephew-physical-therapy-holland";
	@Autowired
	private WebsiteRepository websiteRepository;
	@Autowired
	private WebsiteSocialMediaProfileRepository websiteSocialMediaProfileRepository;
	@Autowired
	private CompanyRepository companyRepository;

	private Website findWebsiteById(long id) {
		return websiteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Website not found."));
	}
	private Optional<Website> findWebsiteOptionalById(long id) {
		return websiteRepository.findById(id);
	}
	private Company findCompanyById(long id) {
		return companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found."));
	}
	private Optional<Company> findCompanyOptionalById(long id) {
		return companyRepository.findById(id);
	}
	private WebsiteSocialMediaProfile findWebsiteProfileById(long id) {
		return websiteSocialMediaProfileRepository.findById(WEBSITE_ID)
				.orElseThrow(() -> new EntityNotFoundException("Website profile not found."));
	}
	private Optional<WebsiteSocialMediaProfile> findWebsiteProfileOptionalById(long id) {
		return websiteSocialMediaProfileRepository.findById(id);
	}
	private Website save(Website website) {
		return websiteRepository.save(website);
	}
	private Company save(Company company) {
		return companyRepository.save(company);
	}
	private WebsiteSocialMediaProfile save(WebsiteSocialMediaProfile profile) {
		return websiteSocialMediaProfileRepository.save(profile);
	}
	/*
	DROP TABLE IF EXISTS website, website_social_media_profile, page, page_text;
	 */

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Order(280)
	@Test
	void createWebsite() {
		if (findWebsiteOptionalById(WEBSITE_ID).isEmpty()) {
			Website website = new Website();
			website.setId(WEBSITE_ID);
			website.setWebsiteVersion(WebsiteVersion.VERSION_1);
			website.setName(WEBSITE_NAME);
			website.setUrl(WEBSITE_URL);
			website.initializePages(PageVersion.VERSION_1);
			save(website);
		}
	}

	@Order(285)
	@Test
	void createWebsiteProfile() {
		if (findWebsiteProfileOptionalById(WEBSITE_ID).isEmpty()) {
			WebsiteSocialMediaProfile profile = new WebsiteSocialMediaProfile();
			profile.setId(WEBSITE_PROFILE_ID);
			profile.setYoutube(WEBSITE_PROFILE_YOUTUBE);
			profile.setFacebook(WEBSITE_PROFILE_FACEBOOK);
			profile.setInstagram(WEBSITE_PROFILE_INSTAGRAM);
			profile.setYelp(WEBSITE_PROFILE_YELP);
			save(profile);
		}
	}

	@Order(288)
	@Test
	void attachWebsiteAndProfile() {
		Website website = findWebsiteById(WEBSITE_ID);
		WebsiteSocialMediaProfile profile = findWebsiteProfileById(WEBSITE_PROFILE_ID);
		profile.setWebsite(website);
		website.setProfile(profile);
		save(website);
	}

	@Order(290)
	@Test
	void verifyWebsiteProfileYoutube() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_YOUTUBE, website.getProfile().getYoutube());
	}

	@Order(291)
	@Test
	void verifyWebsiteProfileFacebook() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_FACEBOOK, website.getProfile().getFacebook());
	}

	@Order(292)
	@Test
	void verifyWebsiteProfileInstagram() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_INSTAGRAM, website.getProfile().getInstagram());
	}

	@Order(293)
	@Test
	void verifyWebsiteProfileYelp() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_PROFILE_YELP, website.getProfile().getYelp());
	}

	@Order(294)
	@Test
	void verifyWebsiteName() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_NAME, website.getName());
	}

	@Order(295)
	@Test
	void verifyWebsiteProfileUrl() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_URL, website.getUrl());
	}

	@Order(300)
	@Test
	void attachWebsiteToCompany() {
		Website website = findWebsiteById(WEBSITE_ID);
		Company company = findCompanyById(COMPANY_ID);
		company.setWebsite(website);
		company.assignIdToChildren();
		save(company);
		save(website);
	}

	@Order(301)
	@Test
	void verifyCompanyWebsiteIsNotNull() {
		Company company = findCompanyById(COMPANY_ID);
		assertNotNull(company.getWebsite());
	}

	@Order(305)
	@Test
	void saveCompanyDoesNotRemoveWebsite() {
		Company company = findCompanyById(COMPANY_ID);
		company.assignIdToChildren();
		save(company);
		Website website = findWebsiteById(WEBSITE_ID);
		assertNotNull(website.getCompany());
	}

	@Order(350)
	@Test
	void websiteShouldHaveVersion1() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WebsiteVersion.VERSION_1.getValue(), website.getWebsiteVersion().getValue());
	}

	@Order(2800)
	@Test
	void websiteShouldBeNpt() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(COMPANY_URL, website.getCompany().getCompanyUrl());
	}

	@Order(2801)
	@Test
	void websiteShouldHaveWebsiteId() {
		Website website = findWebsiteById(WEBSITE_ID);
		assertEquals(WEBSITE_ID, website.getId());
	}

}
