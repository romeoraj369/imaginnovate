package com.imaginnovate.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BatchErrors {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Timestamp timestampField;

	private String filename;

	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BatchErrors() {

	}

	public BatchErrors(Timestamp timestampField, String filename, String message) {
		super();
		this.timestampField = timestampField;
		this.filename = filename;
		this.message = message;
	}

	public Timestamp getTimestampField() {
		return timestampField;
	}

	public void setTimestampField(Timestamp timestampField) {
		this.timestampField = timestampField;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "BatchErrors [id=" + id + ", timestampField=" + timestampField + ", filename=" + filename + ", message="
				+ message + "]";
	}

}
