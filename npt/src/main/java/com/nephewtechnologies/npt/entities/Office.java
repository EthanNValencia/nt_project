package com.nephewtechnologies.npt.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nephewtechnologies.npt.entities.employee.Employee;

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
public class Office {

	// 12723 N Bellwood Dr STE 10, Holland, MI 49424
	// info@nephewpt.com
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
	private String mapUrl; // https://www.google.com/maps?q=+12723+N+Bellwood+Dr.,+Suite+10+Holland,+MI+49424
	@Column(length = 200)
	private String introduction; // You may always visit our office, located on Holland’s north side
	
	@OneToOne(mappedBy = "office", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private OfficeSocialMediaProfile officeSocialMedialProfile;
	
	@OneToMany(mappedBy = "office", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private Set<OfficeDailySchedule> schedule = new HashSet<>();

	@OneToMany(mappedBy = "office", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("office")
	private Set<Employee> employees = new HashSet<>();
	
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

	public Office() {
		super();
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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
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
