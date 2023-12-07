package com.nephew.oesa.entities.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Website {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 50)
	private String name;

	@Column(length = 80)
	private String homeUrl;

	@OneToOne(mappedBy = "website", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("website")
	private WebsiteSocialMediaProfile profile;
	
	@OneToMany(mappedBy = "website", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnoreProperties("website")
    private List<Page> pages = new ArrayList<>();

	public Website() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
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
		return Objects.hash(homeUrl, id, name, pages, profile);
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
		return Objects.equals(homeUrl, other.homeUrl) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(pages, other.pages) && Objects.equals(profile, other.profile);
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", homeUrl=" + homeUrl + ", profile=" + profile + ", pages="
				+ pages.size() + "]";
	}

	public void addPage(Page page) {
		pages.add(page);
	}
	
	

}
