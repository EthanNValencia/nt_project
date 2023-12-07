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
	private FAQsRepository repo;

	public FAQs saveNewQuestion(FAQs faq) {
		return repo.save(faq);
	}

	public FAQs saveAnsweredQuestion(FAQs faq) {
		faq.setQuestionIsAnswered(true);
		return repo.save(faq);
	}

	public List<FAQs> getAll() {
		List<FAQs> faqs = repo.findAll();
		return faqs;
	}

	public ArrayList<FAQs> getAllAnsweredQuestions() {
		return repo.selectAllAnsweredQuestions().get();
	}

	public ArrayList<FAQs> getAllUnansweredQuestions() {
		return repo.selectAllUnansweredQuestions().get();
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public FAQs saveFaq(FAQs faqs) {
		return repo.save(faqs);
	}
}
