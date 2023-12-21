package com.nephew.website.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class WebsiteSocialMediaProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 120)
	private String linkedin = "";
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
	private String google = "";

	@OneToOne
    @JoinColumn(name = "website_id", referencedColumnName = "id")
	private Website website;

	public WebsiteSocialMediaProfile() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
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

	public String getX() {
		return x;
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

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WebsiteSocialMediaProfile that = (WebsiteSocialMediaProfile) o;
		return getId() == that.getId() && Objects.equals(getLinkedin(), that.getLinkedin()) && Objects.equals(getYoutube(), that.getYoutube()) && Objects.equals(getFacebook(), that.getFacebook()) && Objects.equals(getMyspace(), that.getMyspace()) && Objects.equals(getInstagram(), that.getInstagram()) && Objects.equals(getYelp(), that.getYelp()) && Objects.equals(getTiktok(), that.getTiktok()) && Objects.equals(getX(), that.getX()) && Objects.equals(getPinterest(), that.getPinterest()) && Objects.equals(getSnapchat(), that.getSnapchat()) && Objects.equals(getWhatsapp(), that.getWhatsapp()) && Objects.equals(getTumblr(), that.getTumblr()) && Objects.equals(getGoogle(), that.getGoogle()) && Objects.equals(getWebsite(), that.getWebsite());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getLinkedin(), getYoutube(), getFacebook(), getMyspace(), getInstagram(), getYelp(), getTiktok(), getX(), getPinterest(), getSnapchat(), getWhatsapp(), getTumblr(), getGoogle(), getWebsite());
	}
}
