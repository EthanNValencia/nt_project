package com.nephew.faqs;

import com.nephew.faqs.entities.Company;
import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.repositories.CompanyRepository;
import com.nephew.faqs.repositories.FAQsRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Order(10)
	@Test
	void deleteFaqs() {
		faqsRepository.deleteAll();
	}

	@Order(20)
	@Test
	void checkIfCompanyExists() {
		Optional<Company> npt = companyRepository.findByCompanyUrl("npt");
		Optional<Company> egi = companyRepository.findByCompanyUrl("egi");
		if(npt.isEmpty()) {
			Company newNpt = new Company();
			newNpt.setId(1L);
			newNpt.setCompanyName("Nephew Physical Therapy");
			newNpt.setCompanyUrl("npt");
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
		save(faq1);
		save(faq2);
		save(faq3);
		save(faq4);
		save(faq5);
		save(faq6);
	}

	@Order(110)
	@Test
	void assignNptFaqsToNptCompany() {
		Optional<Company> npt = companyRepository.findByCompanyUrl("npt");
		if(npt.isEmpty()) {
			fail();
		}
		Company nptCompany = npt.get();
		List<FAQs> faqs = getAllFaqsWithoutCompanyId();
		for(FAQs faq : faqs) {
			faq.setCompany(nptCompany);
		}
		faqsRepository.saveAll(faqs);
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
