package com.example.connector.service;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

@Service
public class AzureBlobService {

	private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

	public final BlobServiceClient blobServiceClient;

	@Value("${azure.blob.container.name}")
	private String containerName;

	public AzureBlobService(BlobServiceClient blobServiceClient) {
		this.blobServiceClient = blobServiceClient;
	}

	public void upload(String key, InputStream input, long fileSize) {
		logger.info("Using Azure container: {}", containerName);
		BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

		BlobClient blobClient = containerClient.getBlobClient(key);

		blobClient.upload(input, fileSize, true);
	}

}
