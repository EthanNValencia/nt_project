package com.nephew.es.emailservice.dto;

public class EmailDto {

	private String to;
	private String from;
	private String html;
	private String subject;

	public EmailDto() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "EmailDto [to=" + to + ", from=" + from + ", html=" + html + ", subject=" + subject + "]";
	}
}