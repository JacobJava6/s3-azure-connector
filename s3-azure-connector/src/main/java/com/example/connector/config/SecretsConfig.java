package com.example.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.connector.config.properties.AwsProperties;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class SecretsConfig {

	private final AwsProperties awsProperties;

	public SecretsConfig(AwsProperties awsProperties) {
		this.awsProperties = awsProperties;
	}

	@Bean
	public SecretsManagerClient secretsManagerClient() {
		return SecretsManagerClient.builder().region(Region.of(awsProperties.getRegion())).build();
	}
}
