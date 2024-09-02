package com.imaginnovate.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.imaginnovate.utils.CustomDateConverter;
import com.imaginnovate.utils.EntityCounter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class Employee {

	@Id
	@CsvBindByName
	private Long employeeId;

	@CsvBindByName
	private String firstName;

	@CsvBindByName
	private String lastName;

	@CsvBindByName
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private Set<PhoneNumber> phoneNumbers = new HashSet<>();

	//@CsvCustomBindByName(converter = CustomDateConverter.class)
	@CsvBindByName
	private Date doj;

	@CsvBindByName
	private Long salaryInMonths;

	@CsvBindByName
	@Transient
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Long getSalaryInMonths() {
		return salaryInMonths;
	}

	public void setSalaryInMonths(Long salaryInMonths) {
		this.salaryInMonths = salaryInMonths;
	}

	@PrePersist
	public void prePersist() {
		EntityCounter.incrementInserted();
	}

	@PreUpdate
	public void preUpdate() {
		EntityCounter.incrementUpdated();
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumbers=" + phoneNumbers + ", doj=" + doj + ", salaryInMonths=" + salaryInMonths
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	
}
