package com.example.connector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.connector.exceptions.SecretRetrievalException;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Service
public class SecretsService {

	private final SecretsManagerClient secretsManagerClient;

	@Value("${aws.region}")
	private String awsRegion;

	public SecretsService(SecretsManagerClient secretsManagerClient) {
		this.secretsManagerClient = secretsManagerClient;
	}

	public String getSecret(String secretName) {

		try {
			return secretsManagerClient.getSecretValue(GetSecretValueRequest.builder().secretId(secretName).build())
					.secretString();
		} catch (SecretRetrievalException e) {
			throw new SecretRetrievalException("Failed to retrieve secret: " + secretName, e);
		}

	}

}
