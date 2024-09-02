package com.imaginnovate.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BatchProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String processName;

	private Timestamp startTimestamp;

	private Timestamp endTimestamp;

	private String processedFileName;

	private Long insertedRecordCount;

	private Long updatedRecordCount;

	private Long erroredRecordCOunt;

	public BatchProcess() {

	}

	public BatchProcess(String processName, Timestamp startTimestamp) {
		super();
		this.processName = processName;
		this.startTimestamp = startTimestamp;
	}

	public BatchProcess(String processName, Timestamp endTimestamp, String processedFileName, Long insertedRecordCount,
			Long updatedRecordCount, Long erroredRecordCOunt) {
		super();
		this.processName = processName;
		this.endTimestamp = endTimestamp;
		this.processedFileName = processedFileName;
		this.insertedRecordCount = insertedRecordCount;
		this.updatedRecordCount = updatedRecordCount;
		this.erroredRecordCOunt = erroredRecordCOunt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Timestamp getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Timestamp startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Timestamp getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Timestamp endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getProcessedFileName() {
		return processedFileName;
	}

	public void setProcessedFileName(String processedFileName) {
		this.processedFileName = processedFileName;
	}

	public Long getInsertedRecordCount() {
		return insertedRecordCount;
	}

	public void setInsertedRecordCount(Long insertedRecordCount) {
		this.insertedRecordCount = insertedRecordCount;
	}

	public Long getUpdatedRecordCount() {
		return updatedRecordCount;
	}

	public void setUpdatedRecordCount(Long updatedRecordCount) {
		this.updatedRecordCount = updatedRecordCount;
	}

	public Long getErroredRecordCOunt() {
		return erroredRecordCOunt;
	}

	public void setErroredRecordCOunt(Long erroredRecordCOunt) {
		this.erroredRecordCOunt = erroredRecordCOunt;
	}

	@Override
	public String toString() {
		return "BatchProcess [id=" + id + ", processName=" + processName + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + ", processedFileName=" + processedFileName
				+ ", insertedRecordCount=" + insertedRecordCount + ", updatedRecordCount=" + updatedRecordCount
				+ ", erroredRecordCOunt=" + erroredRecordCOunt + "]";
	}

}
