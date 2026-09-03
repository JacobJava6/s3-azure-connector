package com.example.connector.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.connector.model.TransferResult;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class TransferService {
	
	public final S3Service s3Service;
	public final AzureBlobService azureBlobService;
	
	public TransferService(S3Service s3Service, AzureBlobService azureBlobService) {
		this.s3Service=s3Service;
		this.azureBlobService=azureBlobService;
	}
	
	public TransferResult transfer() {
		
		List<S3Object> s3BucketContentList=s3Service.getBucketContentsList();
		s3Service.printBucketContents(s3BucketContentList);
		for(S3Object s3Object: s3BucketContentList) {
			
			String key=s3Object.key();
			
			try(ResponseInputStream<GetObjectResponse> inputStream = s3Service.getObject(key)){
				 azureBlobService.upload(
			                key,
			                inputStream,
			                s3Object.size()
			        );
			}catch(Exception e) {}
			
			
		}
		
		return new TransferResult(0, 0, 0, null, null);
	}

}
