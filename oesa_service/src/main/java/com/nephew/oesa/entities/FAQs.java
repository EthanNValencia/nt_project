package com.nephew.oesa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FAQs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private boolean questionIsAnswered;
	@Column(columnDefinition = "TEXT")
	private String question;
	@Column(columnDefinition = "TEXT")
	private String answer;

	public FAQs() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isQuestionIsAnswered() {
		return questionIsAnswered;
	}

	public void setQuestionIsAnswered(boolean questionIsAnswered) {
		this.questionIsAnswered = questionIsAnswered;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "FAQs [id=" + id + ", questionIsAnswered=" + questionIsAnswered + ", question=" + question + ", answer="
				+ answer + "]";
	}

}
