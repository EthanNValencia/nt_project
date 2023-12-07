package com.nephew.error.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FrontEndError {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30)
	private String name;
	@Column(length = 30)
	private String code;
	@Column(length = 45)
	private String message;
	@Column(length = 10)
	private String method;
	@Column(length = 140)
	private String url;
	@Column(columnDefinition = "TEXT")
	private String data;
	@Column(columnDefinition = "TEXT")
	private String requestHeaders;
	@Column(length = 30)
	private String clientIp;
	@Column(length = 10)
	private int status;
	@Column(length = 30)
	private String statusText;
	@Column(length = 30)
	private String formattedDateTime;

	public FrontEndError() {
		super();
	}

	public String getFormattedDateTime() {
		return formattedDateTime;
	}

	public void setFormattedDateTime(String formattedDateTime) {
		this.formattedDateTime = formattedDateTime;
	}

	public int getStatus() {
		return status;
	}
	
	

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	@Override
	public String toString() {
		return "FrontEndError [id=" + id + ", name=" + name + ", code=" + code + ", message=" + message + ", method="
				+ method + ", url=" + url + ", data=" + data + ", clientIp=" + clientIp + "]";
	}

}
