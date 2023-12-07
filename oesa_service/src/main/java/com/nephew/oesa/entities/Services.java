package com.nephew.oesa.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nephew.oesa.entities.employee.Employee;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 128)
	private String name;
	@ManyToMany(mappedBy = "services")
	private Set<Employee> employees = new HashSet<>();
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
		if (!(obj instanceof Services)) {
            return false;
        }
		Services pa = (Services) obj;
		return this.name.equals(pa.getName());
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(name);
	}
	
	public Services() {
		super();
	}
	
	public Services(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	@Override
	public String toString() {
		return "Specialty [id=" + id + ", name=" + name + "]";
	}

	

}

/*
 * HeadAndNeck: false, Shoulders: false, Elbows: false, Wrists: false, MidBack:
 * false, LowerBack: false, Hip: false, Knees: false, FootAndAnkle: false,
 * 
 * Balance: false, VestibularRehab: false, MassageTherapy: false,
 */