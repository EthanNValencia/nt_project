package com.nephew.oesa.entities.employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nephew.oesa.entities.Appointment;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.OfficeDailySchedule;
import com.nephew.oesa.entities.services.Services;
import com.nephew.oesa.entities.website.Website;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 50, nullable = false)
	private String firstName;
	@Column(length = 50, nullable = true)
	private String middleName;
	@Column(length = 50, nullable = false)
	private String lastName;
	private String img;
	private String role_id;
	@Column(length = 82, nullable = true)
	private String role; // This is like a title
	private String meta;
	@Column(length = 82, nullable = true)
	private String email;
	@Column(length = 20, nullable = true)
	private String workPhone;
	@Column(length = 20, nullable = true)
	private String personalPhone;
	private String subject; // her
	private String possessive; // she, she has something (possessive)
	
	public Employee() {
		super();
		this.services = new HashSet<>();
		this.appointments = new ArrayList<>();
		this.schedule = new HashSet<>();
		this.biographicalTexts = new ArrayList<>();			
		this.informationalTexts = new ArrayList<>();
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "employee_service", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	@JsonIgnoreProperties("employees")
	private Set<Services> services;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private List<Appointment> appointments;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private Set<EmployeeDailySchedule> schedule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "office_id")
	@JsonIgnoreProperties("employees")
	private Office office;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private EmployeeContent employeeContent;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private EmployeeSocialMediaProfile profile;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private List<BiographicalText> biographicalTexts;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonIgnoreProperties("employee")
	private List<InformationalText> informationalTexts;

	@Override
	public int hashCode() {
		return Objects.hash(id, img, meta, firstName, middleName, lastName, services, role, role_id);
	}

	public EmployeeSocialMediaProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeSocialMediaProfile profile) {
		this.profile = profile;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id && Objects.equals(img, other.img) && Objects.equals(meta, other.meta)
				&& Objects.equals(firstName, other.lastName) && Objects.equals(services, other.services)
				&& Objects.equals(role, other.role) && Objects.equals(role_id, other.role_id);
	}

	public List<BiographicalText> getBiographicalTexts() {
		return biographicalTexts;
	}

	public List<InformationalText> getInformationalTexts() {
		return informationalTexts;
	}

	public void setBiographicalTexts(List<BiographicalText> biographicalTexts) {
		this.biographicalTexts = biographicalTexts;
	}

	public void setInformationalTexts(List<InformationalText> informationalTexts) {
		this.informationalTexts = informationalTexts;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPossessive() {
		return possessive;
	}

	public void setPossessive(String possessive) {
		this.possessive = possessive;
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

	public EmployeeContent getEmployeeContent() {
		return employeeContent;
	}

	public void setEmployeeContent(EmployeeContent employeeContent) {
		this.employeeContent = employeeContent;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<EmployeeDailySchedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(Set<EmployeeDailySchedule> schedule) {
		this.schedule = schedule;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Employee(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void convertServicesListToSet(List<Services> services) {
		this.services = new HashSet<Services>(services);
	}

	public void assignSpecialtiesWithEmployeeId(Set<Services> specialties) {
		this.services = assignEmployeeToElements(specialties);
	}

	public void assignSpecialtiesWithEmployeeId(List<Services> specialtiesList) {
		this.services = assignEmployeeToElements(new HashSet<>(specialtiesList));
	}

	public Set<Long> returnServicesIdAsSet() {
		Set<Long> serviceIds = new HashSet<>();
		for (Services service : services) {
			serviceIds.add(service.getId());
		}
		return serviceIds;
	}

	private Set<Services> assignEmployeeToElements(Set<Services> specialties) {
		for (Services specialty : specialties) {
			Set<Employee> employees = new HashSet<>();
			employees.add(new Employee(this.getId()));
			specialty.setEmployees(employees);
		}
		return specialties;
	}

	public void generateScheduleFromOffice(Set<OfficeDailySchedule> officeDailySchedule) {
		for (OfficeDailySchedule officeSchedule : officeDailySchedule) {
			EmployeeDailySchedule employeeSchedule = new EmployeeDailySchedule();
			employeeSchedule.setDay(officeSchedule.getDay());
			employeeSchedule.setBeginTime(officeSchedule.getBeginTime());
			employeeSchedule.setEndTime(officeSchedule.getEndTime());
			employeeSchedule.setEmployee(new Employee(id));
			schedule.add(employeeSchedule);
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", img=" + img + ", role_id=" + role_id + ", role=" + role + ", meta=" + meta + ", email="
				+ email + ", workPhone=" + workPhone + ", personalPhone=" + personalPhone + ", subject=" + subject
				+ ", possessive=" + possessive + ", services=" + services.size() + ", appointments="
				+ appointments.size() + ", schedule=" + schedule.size() + ", office=" + office + ", employeeContent="
				+ employeeContent + ", profile=" + profile + ", biographicalTexts=" + biographicalTexts.size()
				+ ", informationalTexts=" + informationalTexts.size() + "]";
	}

}
