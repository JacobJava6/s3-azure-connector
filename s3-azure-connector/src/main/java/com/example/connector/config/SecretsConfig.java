package com.example.connector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
public class SecretsConfig {

	@Value("${aws.region}")
	private String awsRegion;

	@Bean
	public SecretsManagerClient secretsManagerClient() {
		return SecretsManagerClient.builder().region(Region.of(awsRegion)).build();
	}
}
