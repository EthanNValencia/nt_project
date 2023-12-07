package com.nephew.common.dto;

// The key is generated and not the id in the database. Trying to keep database info away from the public. 
public class FAQsDto {
	private int key;
	private boolean questionIsAnswered;
	private String question;
	private String answer;

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

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public FAQsDto() {
		super();
	}

}
