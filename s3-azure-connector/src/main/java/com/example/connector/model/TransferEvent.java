package com.example.connector.model;

import java.time.Instant;

public class TransferEvent {

	private String transferId;
	private String fileName;
	private String sourceBucket;
	private String destinationContainer;
	private long fileSize;
	private String status;
	private String errorMessage;
	private long durationMs;
	private Instant timestamp;

	public TransferEvent(String transferId, String fileName, String sourceBucket, String destinationContainer,
			long fileSize, String status, String errorMessage, long durationMs, Instant timestamp) {
		super();
		this.transferId = transferId;
		this.fileName = fileName;
		this.sourceBucket = sourceBucket;
		this.destinationContainer = destinationContainer;
		this.fileSize = fileSize;
		this.status = status;
		this.errorMessage = errorMessage;
		this.durationMs = durationMs;
		this.timestamp = timestamp;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSourceBucket() {
		return sourceBucket;
	}

	public void setSourceBucket(String sourceBucket) {
		this.sourceBucket = sourceBucket;
	}

	public String getDestinationContainer() {
		return destinationContainer;
	}

	public void setDestinationContainer(String destinationContainer) {
		this.destinationContainer = destinationContainer;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getDurationMs() {
		return durationMs;
	}

	public void setDurationMs(long durationMs) {
		this.durationMs = durationMs;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
