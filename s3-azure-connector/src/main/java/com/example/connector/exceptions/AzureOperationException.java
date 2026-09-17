package com.example.connector.exceptions;

public class AzureOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AzureOperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
