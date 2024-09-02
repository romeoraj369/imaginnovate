package com.imaginnovate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long phoneNumber;

	@Column(name = "employee_id")
	private Long employeeId;
	
	public PhoneNumber() {
	}

	public PhoneNumber(Long employeeId2, String phoneNumber2) {
		phoneNumber = Long.parseLong(phoneNumber2);
		employeeId = employeeId2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "PhoneNumber [id=" + id + ", phoneNumber=" + phoneNumber + ", employeeId=" + employeeId + "]";
	}
	

}
