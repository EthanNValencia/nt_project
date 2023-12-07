package com.nephewtechnologies.npt.entities.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(length = 15)
	private String pageName;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private PageType pageType = PageType.UNSPECIFIED;
	
	@Transient
	private PageType[] pageTypeArr = PageType.values();

	@ManyToOne
	@JoinColumn(name = "website_id", referencedColumnName = "id")
	private Website website;

	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("page")
	private List<PageText> pageTexts = new ArrayList<>();

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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public PageType getPageType() {
		return pageType;
	}

	public void setPageType(PageType pageType) {
		this.pageType = pageType;
	}

	public List<PageText> getPageTexts() {
		return pageTexts;
	}

	public void setPageTexts(List<PageText> pageTexts) {
		this.pageTexts = pageTexts;
	}

	public PageType[] getPageTypeArr() {
		return pageTypeArr;
	}

	public void setPageTypeArr(PageType[] pageTypeArr) {
		this.pageTypeArr = pageTypeArr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pageName, pageTexts, pageType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return id == other.id && Objects.equals(pageName, other.pageName) && Objects.equals(pageTexts, other.pageTexts)
				&& pageType == other.pageType;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", pageName=" + pageName + ", pageType=" + pageType + ", pageTexts="
				+ pageTexts.size() + "]";
	}

}
