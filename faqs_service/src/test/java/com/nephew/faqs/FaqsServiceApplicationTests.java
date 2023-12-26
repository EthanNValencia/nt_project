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

	@Order(10)
	@Test
	void deleteFaqs() {
        try {
            List<FAQs> faqs = jdbcService.findAllFaqsByCompanyUrl("npt");
			for(FAQs faq: faqs) {
				jdbcService.deleteFaqById(faq.getId());
			}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	@Order(20)
	@Test
	void checkIfCompanyExists() {
		Optional<Company> npt = companyRepository.findByCompanyUrl(NPT_URL);
		Optional<Company> egi = companyRepository.findByCompanyUrl("egi");
		if(npt.isEmpty()) {
			Company newNpt = new Company();
			newNpt.setId(1L);
			newNpt.setCompanyName("Nephew Physical Therapy");
			newNpt.setCompanyUrl(NPT_URL);
			newNpt.setCompanyAcronym("npt");
		}
		if(egi.isEmpty()) {
			Company newEgi = new Company();
			newEgi.setId(2L);
			newEgi.setCompanyName("EcoGlow Innovations");
			newEgi.setCompanyUrl("egi");
			newEgi.setCompanyAcronym("egi");
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
	void verifyFaqsWereAssignedToCompany() {
		Optional<Company> npt = companyRepository.findByCompanyUrl("npt");
		if(npt.isEmpty()) {
			fail();
		}
		Company nptCompany = npt.get();
		assertEquals(6, nptCompany.getFaqs().size());
	}

	@Order(200)
	@Test
	void createEgiFaqs() {

	}

}
