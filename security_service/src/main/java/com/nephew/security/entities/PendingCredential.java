package com.nephew.security.entities;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PendingCredential implements UserDetails {

	private static final long serialVersionUID = 1638134585862758978L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	@Column(length = 50)
	private String companyName;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(length = 50)
	private String pendingCode;
	@Column(length = 50)
	private String approvalCode;

	public PendingCredential(String firstName, String lastName, String email, String password, String companyName, Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
		this.role = role;
	}
	
	public void generatePendingCode() {
		this.pendingCode = String.valueOf(hashCode());
	}

	public String getPendingCode() {
		return pendingCode;
	}

	public void setPendingCode(String pendingCode) {
		this.pendingCode = pendingCode;
	}
	
	public void generateApprovalCode() {
		int minValue = 1;
        int maxValue = 10000;
        double randomValue = Math.random() * (maxValue - minValue + 1) + minValue;
		this.approvalCode = String.valueOf(hashCode() + (int) randomValue);
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public PendingCredential() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, role, companyName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PendingCredential other = (PendingCredential) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(pendingCode, other.pendingCode)
				&& role == other.role && Objects.equals(companyName, other.companyName);
	}

	@Override
	public String toString() {
		return "PendingCredential [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", companyName=" + companyName + ", role=" + role + ", pendingCode=" + pendingCode
				+ ", approvalCode=" + approvalCode + "]";
	}

}