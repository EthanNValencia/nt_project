package com.nephew.oesa.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nephew.oesa.entities.Appointment;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.SocialMediaProfile;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.employee.EmployeeDailySchedule;
import com.nephew.oesa.entities.services.Services;

public class EmployeeDto {

	private int key;
	private String firstName;
	private String middleName;
	private String lastName;
	private String img;
	private String role_id;
	private String role;
	private String meta;
	private String email;
	private String workPhone;
	private String personalPhone;
	private Set<Services> services = new HashSet<Services>();
	private HashMap<LocalDate, ArrayList<AppointmentDto>> appointments = null;
	private Set<EmployeeDailySchedule> schedule = null;
	private Office office;
	private SocialMediaProfile socialMediaProfile;

	public EmployeeDto(Employee employee, int key) {
		super();
		this.key = key;
		this.firstName = employee.getFirstName();
		this.middleName = employee.getMiddleName();
		this.lastName = employee.getLastName();
		this.img = employee.getImg();
		this.role_id = employee.getRole_id();
		this.role = employee.getRole();
		this.meta = employee.getMeta();
		this.email = employee.getEmail();
		this.workPhone = employee.getWorkPhone();
		this.personalPhone = employee.getPersonalPhone();
		this.services = employee.getServices();
		this.socialMediaProfile = employee.getProfile();
		if(employee.getSchedule() != null) {
			this.schedule = new HashSet<>(employee.getSchedule());
		}
		this.office = employee.getOffice();
		if(employee.getAppointments() != null) {
			this.appointments = new HashMap<LocalDate, ArrayList<AppointmentDto>>();
			setAppointments(employee.getAppointments());
		}
	}
	
	

	public SocialMediaProfile getSocialMediaProfile() {
		return socialMediaProfile;
	}



	public void setSocialMediaProfile(SocialMediaProfile socialMediaProfile) {
		this.socialMediaProfile = socialMediaProfile;
	}



	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public HashMap<LocalDate, ArrayList<AppointmentDto>> getAppointments() {
		return appointments;
	}

	public void setAppointments(HashMap<LocalDate, ArrayList<AppointmentDto>> appointments) {
		this.appointments = appointments;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getPersonalPhone() {
		return personalPhone;
	}

	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Set<EmployeeDailySchedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(Set<EmployeeDailySchedule> schedule) {
		this.schedule = schedule;
	}

	public EmployeeDto() {
		super();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}
	
	public void setAppointments(List<Appointment> appointments) {
		if (appointments == null) {
			return;
		}
		for (Appointment app : appointments) {
			addAppointment(app);
		}
	}

	private void addAppointment(Appointment app) {
		LocalDate localDate = app.getBeginTime().toLocalDate();
		if (appointments.containsKey(localDate)) {
			ArrayList<AppointmentDto> appArrayList = appointments.get(localDate);
			appArrayList.add(new AppointmentDto(app));
			appointments.put(localDate, appArrayList);
		} else {
			ArrayList<AppointmentDto> appArrayList = new ArrayList<>();
			appArrayList.add(new AppointmentDto(app));
			appointments.put(localDate, appArrayList);
		}
	}

	/*
	 * public HashMap<LocalDate, int[]> getStructuredAppointments() { return
	 * structuredAppointments; }
	 * 
	 * public void setStructuredAppointments(HashMap<LocalDate, int[]>
	 * structuredAppointments) { this.structuredAppointments =
	 * structuredAppointments; }
	 */
}
