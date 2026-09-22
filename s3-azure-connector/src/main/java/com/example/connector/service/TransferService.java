package com.example.connector.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.connector.model.TransferEvent;
import com.example.connector.model.TransferResult;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class TransferService {

	private static final Logger logger = LoggerFactory.getLogger(TransferService.class);

	private final S3Service s3Service;
	private final AzureBlobService azureBlobService;
	private final TransferEventPublisher transferEventPublisher;

	public TransferService(S3Service s3Service, AzureBlobService azureBlobService,
			TransferEventPublisher transferEventPublisher) {
		this.s3Service = s3Service;
		this.azureBlobService = azureBlobService;
		this.transferEventPublisher = transferEventPublisher;
	}

	public TransferResult transfer() {

		List<S3Object> s3BucketContentList = s3Service.getBucketContentsList();

		int totalFiles = s3BucketContentList.size();
		List<String> successfulFiles = new ArrayList<>();
		List<String> failedFiles = new ArrayList<>();

		long transferStartTime = System.currentTimeMillis();

		for (S3Object s3Object : s3BucketContentList) {

			String key = s3Object.key();
			long fileSize = s3Object.size();
			String transferId = UUID.randomUUID().toString();
			long fileStartTime = System.currentTimeMillis();

			try (ResponseInputStream<GetObjectResponse> inputStream = s3Service.getObject(key)) {
				azureBlobService.upload(key, inputStream, s3Object.size());

				successfulFiles.add(key);

				long fileTime = System.currentTimeMillis() - fileStartTime;

				logger.info(
						"Transfer successful: file={}, size={} bytes, source=S3, destination=Azure Blob, time={} ms",
						key, fileSize, fileTime);
				TransferEvent event = new TransferEvent(transferId, key, s3Service.getBucketName(),
						azureBlobService.getContainerName(), fileSize, "SUCCESS", null, fileTime, Instant.now());

				transferEventPublisher.publish(event);
			} catch (Exception e) {
				failedFiles.add(key);

				long fileTime = System.currentTimeMillis() - fileStartTime;

				logger.error(
						"Transfer failed: file={}, size={} bytes, source=S3, destination=Azure Blob, time={} ms, error={}",
						key, fileSize, fileTime, e.getMessage());

				TransferEvent event = new TransferEvent(transferId, key, s3Service.getBucketName(),
						azureBlobService.getContainerName(), fileSize, "FAILED", e.getMessage(), fileTime,
						Instant.now());

				transferEventPublisher.publish(event);
			}

		}
		long totalTime = System.currentTimeMillis() - transferStartTime;
		logger.info("Transfer complete: total={}, successful={}, failed={}, totalTime={} ms", totalFiles,
				successfulFiles.size(), failedFiles.size(), totalTime);

		return new TransferResult(totalFiles, successfulFiles.size(), failedFiles.size(), successfulFiles, failedFiles);
	}

}
