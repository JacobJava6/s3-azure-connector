package com.example.connector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

		return secretsManagerClient.getSecretValue(GetSecretValueRequest.builder().secretId(secretName).build())
				.secretString();
	}

}
