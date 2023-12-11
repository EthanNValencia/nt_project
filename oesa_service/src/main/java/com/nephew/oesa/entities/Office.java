package com.nephew.oesa.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.website.Website;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "office_id")
	private long officeId;
	@Column(length = 128, nullable = false)
	private String street;
	@Column(length = 20, nullable = false)
	private String unit;
	@Column(length = 20, nullable = false)
	private String city;
	@Column(length = 2, nullable = false)
	private String state;
	@Column(length = 15, nullable = false)
	private String zip;
	@Column(length = 20, nullable = true)
	private String phone;
	@Column(length = 20, nullable = true)
	private String fax;
	@Column(length = 82, nullable = true)
	private String email;
	@Column(nullable = false)
	private boolean acceptingWalkIns;
	@Column(length = 150)
	private String mapUrl; 
	@Column(length = 200)
	private String introduction; 
	
	
	
	public Office() {
		super();
		this.employees = new ArrayList<>();
		this.schedule = new HashSet<>();
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private Company company;
	
	@OneToOne(mappedBy = "office", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private OfficeSocialMediaProfile officeSocialMedialProfile;
	
	@OneToMany(mappedBy = "office", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private Set<OfficeDailySchedule> schedule;

	@OneToMany(mappedBy = "office", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private List<Employee> employees;
	
	public void assignIdToChildren() {
		for(OfficeDailySchedule schedule: schedule) {
			schedule.setOffice(new Office(officeId));
		}
		for(Employee employee : employees) {
			employee.setOffice(new Office(officeId));
		}
		if(officeSocialMedialProfile != null) {
			officeSocialMedialProfile.setOffice(new Office(officeId));
		} 
	}

	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public OfficeSocialMediaProfile getOfficeSocialMedialProfile() {
		return officeSocialMedialProfile;
	}

	public void setOfficeSocialMedialProfile(OfficeSocialMediaProfile officeSocialMedialProfile) {
		this.officeSocialMedialProfile = officeSocialMedialProfile;
	}

	public Office(long id) {
		super();
		this.officeId = id;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAcceptingWalkIns() {
		return acceptingWalkIns;
	}

	public void setAcceptingWalkIns(boolean acceptingWalkIns) {
		this.acceptingWalkIns = acceptingWalkIns;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Set<OfficeDailySchedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(Set<OfficeDailySchedule> schedule) {
		this.schedule = schedule;
	}

	public void addSchedule(OfficeDailySchedule companyDailySchedule) {
		schedule.add(companyDailySchedule);
	}

	@Override
	public String toString() {
		return "Office [officeId=" + officeId + ", street=" + street + ", unit=" + unit + ", city=" + city + ", state="
				+ state + ", zip=" + zip + ", phone=" + phone + ", fax=" + fax + ", email=" + email
				+ ", acceptingWalkIns=" + acceptingWalkIns + ", mapUrl=" + mapUrl + ", introduction=" + introduction
				+ "]";
	}

}
