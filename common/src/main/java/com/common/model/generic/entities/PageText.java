package com.common.model.generic.entities;

import com.common.model.generic.entities.text.Text;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PageText extends Text {

	@ManyToOne
	@JoinColumn(name = "page_id", referencedColumnName = "id")
	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
