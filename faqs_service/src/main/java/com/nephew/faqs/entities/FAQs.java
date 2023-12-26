package com.nephew.faqs.entities;

import jakarta.persistence.*;

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
	// (cascade = {CascadeType.PERSIST, CascadeType.MERGE}
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;

	public FAQs(long id, boolean questionIsAnswered, String question, String answer, Company company) {
		this.id = id;
		this.questionIsAnswered = questionIsAnswered;
		this.question = question;
		this.answer = answer;
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

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
				+ answer + "companyId=" + company.getId() + "]";
	}

}
