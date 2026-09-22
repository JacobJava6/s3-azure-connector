package com.example.connector.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.example.connector.model.TransferEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransferEventPublisher {

	private static final Logger logger = LoggerFactory.getLogger(TransferEventPublisher.class);

	private final EventHubProducerClient producerClient;
	private final ObjectMapper objectMapper;

	public TransferEventPublisher(EventHubProducerClient producerClient, ObjectMapper objectMapper) {
		this.producerClient = producerClient;
		this.objectMapper = objectMapper;
	}

	public void publish(TransferEvent event) {
		try {
			String json = objectMapper.writeValueAsString(event);

			EventData eventData = new EventData(json);

			producerClient.send(Collections.singletonList(eventData));

			logger.info("Transfer event published successfully for file: {}", event.getFileName());

		} catch (JsonProcessingException e) {
			logger.error("Failed to serialize transfer event for file: {}", event.getFileName(), e);

		} catch (RuntimeException e) {
			logger.error("Failed to publish transfer event for file: {}", event.getFileName(), e);
		}
	}

}
