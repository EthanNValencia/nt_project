package com.nephew.website.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Website {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 50)
	private String name;

	@Column(length = 80)
	private String url;

	@Enumerated(EnumType.STRING)
	private WebsiteVersion websiteVersion;

	@OneToOne(mappedBy = "website", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("website")
	private WebsiteSocialMediaProfile profile;

	@OneToOne
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;
	
	@OneToMany(mappedBy = "website", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("website")
	private List<Page> pages = new ArrayList<>();

	public WebsiteVersion getWebsiteVersion() {
		return websiteVersion;
	}

	public void setWebsiteVersion(WebsiteVersion websiteVersion) {
		this.websiteVersion = websiteVersion;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Website() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WebsiteSocialMediaProfile getProfile() {
		return profile;
	}

	public void setProfile(WebsiteSocialMediaProfile profile) {
		this.profile = profile;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, id, name, pages, profile);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Website other = (Website) obj;
		return Objects.equals(url, other.url) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(pages, other.pages) && Objects.equals(profile, other.profile);
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", homeUrl=" + url + ", profile=" + profile + ", pages="
				+ pages.size() + "]";
	}

	public void addPage(Page page) {
		pages.add(page);
	}

}
