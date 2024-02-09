package com.nephew.website;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.nephew.website.entities.Company;
import com.nephew.website.entities.Website;
import com.nephew.website.entities.WebsiteSocialMediaProfile;
import com.nephew.website.entities.WebsiteVersion;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class WebsiteServiceApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	@Order(1)
	void createNptWebsite() {
		Website npt = new Website();
		npt.setWebsiteVersion(WebsiteVersion.VERSION_1);
		npt.setCompany(new Company(1));
		npt.setProfile(new WebsiteSocialMediaProfile());
	}

}
