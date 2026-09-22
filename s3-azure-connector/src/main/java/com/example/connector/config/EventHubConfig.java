package com.example.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.example.connector.config.properties.EventHubProperties;
import com.example.connector.service.SecretsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class EventHubConfig {

	private final SecretsService secretsService;;
	private final EventHubProperties eventHubProperties;

	public EventHubConfig(SecretsService secretsService, EventHubProperties eventHubProperties) {
		this.secretsService = secretsService;
		this.eventHubProperties = eventHubProperties;
	}

	@Bean
	public EventHubProducerClient eventHubProducerClient() {

		String connectionString = secretsService.getSecret(eventHubProperties.getSecretName());

		return new EventHubClientBuilder().connectionString(connectionString, eventHubProperties.getEventHubName())
				.buildProducerClient();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

}
