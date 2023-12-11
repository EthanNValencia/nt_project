package com.nephew.oesa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nephew.oesa.entities.website.Website;

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
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 60)
	private String companyName;
	@Column(length = 20)
	private String companyAcronym;
	@Column(length = 30)
	private String companyUrl; // This is what will be used in the url to identify the company. 
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("company")
	private List<FAQs> faqs;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("company")
	private List<Office> offices;
	
	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("company")
	private Website website;
	
	public Company() {
		super();
        this.faqs = new ArrayList<>();
        this.offices = new ArrayList<>();
	}

	public void assignIdToChildren() {
		for(FAQs faq : faqs) {
			faq.setCompany(this);
		}
		for(Office office : offices) {
			office.setCompany(this);
		}
		website.setCompany(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getCompanyAcronym() {
		return companyAcronym;
	}

	public void setCompanyAcronym(String companyAcronym) {
		this.companyAcronym = companyAcronym;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<FAQs> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<FAQs> faqs) {
		this.faqs = faqs;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyName, faqs, id, offices, website);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(companyName, other.companyName) && Objects.equals(faqs, other.faqs) && id == other.id
				&& Objects.equals(offices, other.offices) && Objects.equals(website, other.website);
	}
	
}
