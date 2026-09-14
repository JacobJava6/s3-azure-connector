package com.example.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.connector.config.properties.AzureProperties;
import com.example.connector.service.SecretsService;

@Configuration
public class AzureConfig {

	private final SecretsService secretsService;
	private final AzureProperties azureProperties;

	public AzureConfig(SecretsService secretsService, AzureProperties azureProperties) {
		this.secretsService = secretsService;
		this.azureProperties = azureProperties;
	}

	@Bean
	public BlobServiceClient blobServiceClient() {
		String connectionString = secretsService.getSecret(azureProperties.getSecretName());

		return new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
	}

}
