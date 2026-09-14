package com.example.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.connector.config.properties.AwsProperties;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

	private final AwsProperties awsProperties;

	public AwsConfig(AwsProperties awsProperties) {
		this.awsProperties = awsProperties;
	}

	@Bean
	public S3Client s3Client() {
		return S3Client.builder().region(Region.of(awsProperties.getRegion())).build();
	}

}
