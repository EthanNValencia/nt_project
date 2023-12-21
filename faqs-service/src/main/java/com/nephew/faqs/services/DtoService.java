package com.nephew.faqs.services;


import com.nephew.faqs.dtos.FAQsDto;
import com.nephew.faqs.entities.FAQs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DtoService {
	
	public FAQsDto convertFAQsDto(FAQs faq) {
		FAQsDto faqsDto = new FAQsDto();
		faqsDto.setAnswer(faq.getAnswer());
		faqsDto.setQuestion(faq.getQuestion());
		faqsDto.setQuestionIsAnswered(faq.isQuestionIsAnswered());
		return faqsDto;
	}

	public ArrayList<FAQsDto> convertFAQsDto(ArrayList<FAQs> faqs) {
		ArrayList<FAQsDto> faqsDto = new ArrayList<>();
		int key = 0;
		for(FAQs faq : faqs) {
			FAQsDto faqDto = new FAQsDto();
			faqDto.setKey(key); // This is to provide the front-end with a unique key. 
			faqDto.setAnswer(faq.getAnswer());
			faqDto.setQuestion(faq.getQuestion());
			faqDto.setQuestionIsAnswered(faq.isQuestionIsAnswered());
			faqsDto.add(faqDto);
			key = key +  1;
		}
		return faqsDto;
	}
	
}
