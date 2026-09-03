package com.example.connector.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
public class AzureBlobService {
	
	public final BlobServiceClient blobServiceClient;
	
	@Value("${AZURE_BLOB_CONTAINER_NAME}")
	private String containerName;
	
	public AzureBlobService(BlobServiceClient blobServiceClient) {
		this.blobServiceClient=blobServiceClient;
	}
	
	public void upload(String key, InputStream input, long fileSize) {
		BlobContainerClient containerClient =
	            blobServiceClient.getBlobContainerClient(containerName);

	    BlobClient blobClient =
	            containerClient.getBlobClient(key);

	    blobClient.upload(input, fileSize, true);
	}
	

}
