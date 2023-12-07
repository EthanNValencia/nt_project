package com.nephew.error.entities;

public class InvalidLoginAttempt {

	private String contentType;
	private String remoteAddress;
	private String method;
	private String localAddress;
	private String email;
	private String password;

	public InvalidLoginAttempt() {
		super();
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InvalidLoginAttempt [contentType=" + contentType + ", remoteAddress=" + remoteAddress + ", method="
				+ method + ", localAddress=" + localAddress + ", email=" + email + ", password=" + password + "]";
	}

}
