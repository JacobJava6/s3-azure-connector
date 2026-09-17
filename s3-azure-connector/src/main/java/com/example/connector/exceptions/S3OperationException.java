package com.example.connector.exceptions;

public class S3OperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public S3OperationException(String message, Throwable cause) {
		super(message, cause);
	};

}
