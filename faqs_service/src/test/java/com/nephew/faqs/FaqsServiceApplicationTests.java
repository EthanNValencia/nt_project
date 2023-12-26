package com.nephew.faqs;

import com.nephew.faqs.entities.Company;
import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.repositories.CompanyRepository;
import com.nephew.faqs.repositories.FAQsRepository;
import com.nephew.faqs.services.JdbcService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(properties = "eureka.client.enabled=false")
class FaqsServiceApplicationTests {

	private List<FAQs> getAllFaqs() {
		return faqsRepository.findAll();
	}

	private List<FAQs> getAllFaqsWithoutCompanyId() {
		return faqsRepository.findAllFaqsWithNullCompanyId();
	}

	private FAQs save(FAQs faq) {
		return faqsRepository.save(faq);
	}

	private Company save(Company company) {
		return companyRepository.save(company);
	}

	@Autowired
	private FAQsRepository faqsRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private JdbcService jdbcService;

	private final String NPT_URL = "npt";
	private final String EGI_URL = "egi";

	private void deleteFaqs(List<FAQs> faqs) {
		if(!faqs.isEmpty()) {
		for(FAQs faq: faqs) {
			jdbcService.deleteFaqById(faq.getId());
		}
		}
	}

	@Order(10)
	@Test
	void deleteFaqs() {
        try {
            List<FAQs> faqs = jdbcService.findAllFaqsByCompanyUrl(NPT_URL);
			deleteFaqs(faqs);
			faqs = jdbcService.findAllFaqsByCompanyUrl(EGI_URL);
			deleteFaqs(faqs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	@Order(20)
	@Test
	void checkIfCompanyExists() {
		Optional<Company> npt = companyRepository.findByCompanyUrl(NPT_URL);
		Optional<Company> egi = companyRepository.findByCompanyUrl(EGI_URL);
		if(npt.isEmpty()) {
			Company newNpt = new Company();
			newNpt.setId(1L);
			newNpt.setCompanyName("Nephew Physical Therapy");
			newNpt.setCompanyUrl(NPT_URL);
			newNpt.setCompanyAcronym(NPT_URL);
			companyRepository.save(newNpt);
		}
		if(egi.isEmpty()) {
			Company newEgi = new Company();
			newEgi.setId(2L);
			newEgi.setCompanyName("EcoGlow Innovations");
			newEgi.setCompanyUrl(EGI_URL);
			newEgi.setCompanyAcronym(EGI_URL);
			companyRepository.save(newEgi);
		}
 	}

	@Order(100)
	@Test
	void createNptFaqs() {
		FAQs faq1 = new FAQs();
		faq1.setQuestion("When is my payment due?");
		faq1.setAnswer("When appointment is made.");
		faq1.setQuestionIsAnswered(true);
		FAQs faq2 = new FAQs();
		faq2.setQuestion("Do I meed to be concerned about my Credit Card security?");
		faq2.setAnswer(
				"Your Card info is stored in our HIPAA compliant software. Once saved, we can only see the last 4 digits.");
		faq2.setQuestionIsAnswered(true);
		FAQs faq3 = new FAQs();
		faq3.setQuestion("Do I meed to be concerned about my Credit Card security?");
		faq3.setAnswer(
				"Your Card info is stored in our HIPAA compliant software. Once saved, we can only see the last 4 digits.");
		faq3.setQuestionIsAnswered(true);
		FAQs faq4 = new FAQs();
		faq4.setQuestion("Will this process thru my insurance?");
		faq4.setAnswer("Nope.");
		faq4.setQuestionIsAnswered(true);
		FAQs faq5 = new FAQs();
		faq5.setQuestion("What is your Cancellation Policy?");
		faq5.setAnswer("Caroline has your time slot reserved JUST FOR YOU. Sorry, no refunds for cancellations.");
		faq5.setQuestionIsAnswered(true);
		FAQs faq6 = new FAQs();
		faq6.setQuestion("What virtual meeting platform do you use for appointments?");
		faq6.setAnswer(
				"ZOOM. We’ll email you a link after scheduling. Come to appointment ready with all your questions!");
		faq6.setQuestionIsAnswered(true);

		jdbcService.insertFaq(faq1, NPT_URL);
		jdbcService.insertFaq(faq2, NPT_URL);
		jdbcService.insertFaq(faq3, NPT_URL);
		jdbcService.insertFaq(faq4, NPT_URL);
		jdbcService.insertFaq(faq5, NPT_URL);
		jdbcService.insertFaq(faq6, NPT_URL);
	}

	@Order(120)
	@Test
	void verifyFaqsWereAssignedToNptJpa() {
		Optional<Company> npt = companyRepository.findByCompanyUrl(NPT_URL);
		if(npt.isEmpty()) {
			fail();
		}
		Company nptCompany = npt.get();
		assertEquals(6, nptCompany.getFaqs().size());
	}

	@Order(125)
	@Test
	void verifyFaqsWereAssignedToNptJdbc() throws SQLException {
		List<FAQs> egiFaqs = jdbcService.findAllFaqsByCompanyUrl(NPT_URL);
		assertEquals(6, egiFaqs.size());
	}

	@Order(200)
	@Test
	void createEgiFaqs() {
		FAQs faq1 = new FAQs();
		faq1.setQuestion("How do SolarGlow Lamps work?");
		faq1.setAnswer("SolarGlow Lamps harness solar energy through photovoltaic cells, converting sunlight into electricity to power LED lights.");
		faq1.setQuestionIsAnswered(true); 
		FAQs faq2 = new FAQs();
		faq2.setQuestion("Can SolarGlow Lamps be used indoors?");
		faq2.setAnswer("SolarGlow Lamps are designed for outdoor use, as they rely on sunlight for power.");
		faq2.setQuestionIsAnswered(true); 
		FAQs faq3 = new FAQs();
		faq3.setQuestion("What is the lifespan of the solar panels used in SolarGlow Lamps?");
		faq3.setAnswer("The solar panels have a long lifespan and are designed to endure various weather conditions.");
		faq3.setQuestionIsAnswered(true); 
		FAQs faq4 = new FAQs();
		faq4.setQuestion("How long do SolarGlow Lamps stay illuminated at night?");
		faq4.setAnswer("The illumination duration depends on the amount of sunlight received during the day; on a full charge, they typically provide several hours of light.");
		faq4.setQuestionIsAnswered(true); 
		FAQs faq5 = new FAQs();
		faq5.setQuestion("How much wind is required to power WindBrite Lanterns?");
		faq5.setAnswer("WindBrite Lanterns are designed to generate energy with even a gentle breeze, and the built-in wind turbine is efficient in various wind conditions.");
		faq5.setQuestionIsAnswered(true); 
		FAQs faq6 = new FAQs();
		faq6.setQuestion("Are WindBrite Lanterns suitable for all outdoor spaces?");
		faq6.setAnswer("Yes, WindBrite Lanterns are versatile and suitable for gardens, patios, balconies, and other outdoor spaces.");
		faq6.setQuestionIsAnswered(true); 
		FAQs faq7 = new FAQs();
		faq7.setQuestion("Do WindBrite Lanterns store excess energy for use during calm periods?");
		faq7.setAnswer("WindBrite Lanterns are designed to provide continuous illumination and do not typically include energy storage features.");
		faq7.setQuestionIsAnswered(true); 
		FAQs faq8 = new FAQs();
		faq8.setQuestion("How does the water-based energy system in HydroBeam Desk Lamps work?");
		faq8.setAnswer("The lamps use a water-based energy system to generate power, converting water flow into energy that powers the LED lights.");
		faq8.setQuestionIsAnswered(true); 
		FAQs faq9 = new FAQs();
		faq9.setQuestion("Can HydroBeam Desk Lamps be used with any type of water?");
		faq9.setAnswer("The lamps are designed to work with a variety of water types, including tap water, and do not require any special water sources.");
		faq9.setQuestionIsAnswered(true); 
		FAQs faq10 = new FAQs();
		faq10.setQuestion("Are HydroBeam Desk Lamps adjustable for different lighting preferences?");
		faq10.setAnswer("Yes, HydroBeam Desk Lamps feature adjustable brightness levels to suit various work or study environments.");
		faq10.setQuestionIsAnswered(true); 
		FAQs faq11 = new FAQs();
		faq11.setQuestion("Is the bamboo used in BambooGlo Pendants sustainably sourced?");
		faq11.setAnswer("Yes, we are committed to using sustainably sourced bamboo in the crafting of BambooGlo Pendants.");
		faq11.setQuestionIsAnswered(true); 
		FAQs faq12 = new FAQs();
		faq12.setQuestion("Can BambooGlo Pendants be used in damp or humid environments?");
		faq12.setAnswer("While the pendants are durable, it is recommended to avoid prolonged exposure to excessive moisture.");
		faq12.setQuestionIsAnswered(true); 
		FAQs faq13 = new FAQs();
		faq13.setQuestion("Do BambooGlo Pendants come with energy-efficient LED bulbs?");
		faq13.setAnswer("Yes, BambooGlo Pendants come equipped with energy-efficient LED bulbs, contributing to a more eco-conscious lifestyle.");
		faq13.setQuestionIsAnswered(true);
		jdbcService.insertFaq(faq1, EGI_URL);
		jdbcService.insertFaq(faq2, EGI_URL);
		jdbcService.insertFaq(faq3, EGI_URL);
		jdbcService.insertFaq(faq4, EGI_URL);
		jdbcService.insertFaq(faq5, EGI_URL);
		jdbcService.insertFaq(faq6, EGI_URL);
		jdbcService.insertFaq(faq7, EGI_URL);
		jdbcService.insertFaq(faq8, EGI_URL);
		jdbcService.insertFaq(faq9, EGI_URL);
		jdbcService.insertFaq(faq10, EGI_URL);
		jdbcService.insertFaq(faq11, EGI_URL);
		jdbcService.insertFaq(faq12, EGI_URL);
		jdbcService.insertFaq(faq13, EGI_URL);
	}

	@Order(220)
	@Test
	void verifyFaqsWereAssignedToEgiJpa() {
		Optional<Company> npt = companyRepository.findByCompanyUrl(EGI_URL);
		if(npt.isEmpty()) {
			fail();
		}
		Company nptCompany = npt.get();
		assertEquals(13, nptCompany.getFaqs().size());
	}

	@Order(225)
	@Test
	void verifyFaqsWereAssignedToEgiJdbc() throws SQLException {
		List<FAQs> egiFaqs = jdbcService.findAllFaqsByCompanyUrl(EGI_URL);
		assertEquals(13, egiFaqs.size());
	}



}
