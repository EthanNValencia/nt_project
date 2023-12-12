package com.nephew.oesa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class OfficeSocialMediaProfile extends SocialMediaProfile {
	
	@OneToOne
    @JoinColumn(name = "id")
	private Office office;

	public OfficeSocialMediaProfile() {
		super();
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "OfficeSocialMediaProfile [id=" + this.getId() + ", office=" + office.getOfficeId() + "]";
	}
	
	
}
