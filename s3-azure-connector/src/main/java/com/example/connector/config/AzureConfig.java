package com.example.connector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.connector.service.SecretsService;

@Configuration
public class AzureConfig {

	@Value("${azure.blob.connection-string}")
	private String connectionStringSecret;

	private final SecretsService secretsService;

	public AzureConfig(SecretsService secretsService) {
		this.secretsService = secretsService;
	}

	@Bean
	public BlobServiceClient blobServiceClient() {
		String connectionString = secretsService.getSecret(connectionStringSecret);

		return new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	}

}
