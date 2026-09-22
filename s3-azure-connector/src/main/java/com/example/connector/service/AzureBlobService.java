package com.example.connector.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobStorageException;
import com.example.connector.config.properties.AzureProperties;
import com.example.connector.exceptions.AzureOperationException;

@Service
public class AzureBlobService {

	private final BlobServiceClient blobServiceClient;
	private final AzureProperties azureProperties;

	public AzureBlobService(BlobServiceClient blobServiceClient, AzureProperties azureProperties) {
		this.blobServiceClient = blobServiceClient;
		this.azureProperties = azureProperties;
	}

	public void upload(String key, InputStream input, long fileSize) {

		try {
			BlobContainerClient containerClient = blobServiceClient
					.getBlobContainerClient(azureProperties.getContainerName());

			BlobClient blobClient = containerClient.getBlobClient(key);

			blobClient.upload(input, fileSize, true);
		} catch (BlobStorageException e) {
			throw new AzureOperationException("Failed to upload blob to Azure: " + key, e);
		}

	}

	public String getContainerName() {
		return azureProperties.getContainerName();
	}

}
