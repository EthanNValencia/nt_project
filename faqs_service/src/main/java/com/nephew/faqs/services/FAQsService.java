package com.nephew.oesa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.repositories.FAQsRepository;

@Service
public class FAQsService {
	@Autowired
	private FAQsRepository faqsRepository;
	public FAQs saveNewQuestion(FAQs faq) {
		return faqsRepository.save(faq);
	}
	public FAQs saveAnsweredQuestion(FAQs faq) {
		faq.setQuestionIsAnswered(true);
		return faqsRepository.save(faq);
	}
	public List<FAQs> getAll(String companyUrl) {
		List<FAQs> faqs = faqsRepository.findByCompanyUrl(companyUrl);
		return faqs;
	}
	public ArrayList<FAQs> getAllAnsweredQuestions() {
		return faqsRepository.selectAllAnsweredQuestions().get();
	}

	public ArrayList<FAQs> getAllUnansweredQuestions() {
		return faqsRepository.selectAllUnansweredQuestions().get();
	}

	public void deleteById(Long id) {
		faqsRepository.deleteById(id);
	}

	public FAQs saveFaq(FAQs faqs) {
		return faqsRepository.save(faqs);
	}
}
