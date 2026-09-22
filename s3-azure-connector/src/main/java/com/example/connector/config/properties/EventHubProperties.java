package com.example.connector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "azure.eventhub")
public class EventHubProperties {

	@NotBlank
	private String secretName;
	@NotBlank
	private String eventHubName;

	public String getSecretName() {
		return secretName;
	}

	public void setSecretName(String secretName) {
		this.secretName = secretName;
	}

	public String getEventHubName() {
		return eventHubName;
	}

	public void setEventHubName(String eventHubName) {
		this.eventHubName = eventHubName;
	}

}
