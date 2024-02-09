package com.nephew.website.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/***
	 * The pageName is used to specify a custom page name in the website. For example, perhaps
	 * there is page of PageType.PRODUCT and the client wants that page to be Our Stuff, this
	 * field will support that requirement.
	 */
	@Column(length = 25)
	private String pageName;

	/***
	 * I want to support the functionality to easily active and deactive pages. I also want
	 * the navigation on the front end to be able to quickly determine if a route should
	 * be accessible.
	 */
	private Boolean active;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private PageType pageType = PageType.Unspecified;

	@Enumerated(EnumType.STRING)
	private PageVersion pageVersion;

	@ManyToOne
	@JoinColumn(name = "website_id", referencedColumnName = "id")
	private Website website;

	public PageVersion getPageVersion() {
		return pageVersion;
	}

	public void setPageVersion(PageVersion pageVersion) {
		this.pageVersion = pageVersion;
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

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		if(pageName == null) {
			this.pageName = this.pageType.toString();
		} else {
			this.pageName = pageName;
		}
	}

	public PageType getPageType() {
		return pageType;
	}

	public void setPageType(PageType pageType) {
		this.pageType = pageType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pageName, pageType);
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
		return id == other.id && Objects.equals(pageName, other.pageName)
				&& pageType == other.pageType;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", pageName=" + pageName + ", pageType=" + pageType + "]";
	}

}
