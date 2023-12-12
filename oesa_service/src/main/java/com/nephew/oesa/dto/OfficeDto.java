package com.nephew.oesa.dto;

import java.util.HashSet;
import java.util.Set;

import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.OfficeDailySchedule;

public class OfficeDto {
	
	private long officeId;
	private String street;
	private String unit;
	private String city;
	private String state;
	private String zip;
	private boolean acceptingWalkIns;
	private String mapUrl;
	private String introduction;
	private String fax;
	private String phone;
	private String email;
	private Set<OfficeDailySchedule> schedule = new HashSet<>();
	
	public OfficeDto() {
		super();
	}
	public OfficeDto(Office office) {
		this.officeId = office.getOfficeId();
		this.street = office.getStreet();
		this.unit = office.getUnit();
		this.city = office.getCity();
		this.state = office.getState().toString();
		this.zip = office.getZip();
		this.fax = office.getFax();
		this.phone = office.getPhone();
		this.email = office.getEmail();
		this.acceptingWalkIns = office.isAcceptingWalkIns();
		this.mapUrl = office.getMapUrl();
		this.introduction = office.getIntroduction();
		this.schedule = office.getSchedule();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isAcceptingWalkIns() {
		return acceptingWalkIns;
	}
	public void setAcceptingWalkIns(boolean acceptingWalkIns) {
		this.acceptingWalkIns = acceptingWalkIns;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	
}
