package com.example.connector.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.connector.config.properties.AzureProperties;

@Service
public class AzureBlobService {

	public final BlobServiceClient blobServiceClient;
	private final AzureProperties azureProperties;

	public AzureBlobService(BlobServiceClient blobServiceClient, AzureProperties azureProperties) {
		this.blobServiceClient = blobServiceClient;
		this.azureProperties = azureProperties;
	}

	public void upload(String key, InputStream input, long fileSize) {
		BlobContainerClient containerClient = blobServiceClient
				.getBlobContainerClient(azureProperties.getContainerName());

		BlobClient blobClient = containerClient.getBlobClient(key);

		blobClient.upload(input, fileSize, true);
	}

}
