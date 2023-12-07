package com.nephewtechnologies.npt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class SocialMediaProfile {

	@Column(length = 120)
	private String linkedin = ""; // https://www.linkedin.com
	@Column(length = 120)
	private String youtube = "";
	@Column(length = 120)
	private String facebook = "";
	@Column(length = 120)
	private String myspace = "";
	@Column(length = 120)
	private String instagram = "";
	@Column(length = 120)
	private String yelp = "";
	@Column(length = 120)
	private String tiktok = "";
	@Column(length = 120)
	private String x = ""; // twitter
	@Column(length = 120)
	private String pinterest = "";
	@Column(length = 120)
	private String snapchat = "";
	@Column(length = 120)
	private String whatsapp = "";
	@Column(length = 120)
	private String tumblr = "";
	@Column(length = 120)
	private String google = ""; // google maps

	public SocialMediaProfile() {
		super();
	}

	public String getX() {
		return x;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getPinterest() {
		return pinterest;
	}

	public void setPinterest(String pinterest) {
		this.pinterest = pinterest;
	}

	public String getSnapchat() {
		return snapchat;
	}

	public void setSnapchat(String snapchat) {
		this.snapchat = snapchat;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getTumblr() {
		return tumblr;
	}

	public void setTumblr(String tumblr) {
		this.tumblr = tumblr;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getMyspace() {
		return myspace;
	}

	public void setMyspace(String myspace) {
		this.myspace = myspace;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getYelp() {
		return yelp;
	}

	public void setYelp(String yelp) {
		this.yelp = yelp;
	}

	public String getTiktok() {
		return tiktok;
	}

	public void setTiktok(String tiktok) {
		this.tiktok = tiktok;
	}

}
