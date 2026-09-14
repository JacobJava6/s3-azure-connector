package com.example.connector.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.connector.config.properties.S3Properties;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class S3Service {

	private final S3Client s3Client;
	private final S3Properties s3Properties;

	public S3Service(S3Client s3Client, S3Properties s3Properties) {
		this.s3Client = s3Client;
		this.s3Properties = s3Properties;
	}

	public List<S3Object> getBucketContentsList() {
		ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(s3Properties.getBucketName()).build();

		ListObjectsV2Response response = s3Client.listObjectsV2(request);

		return response.contents();
	}

	public ResponseInputStream<GetObjectResponse> getObject(String key) {

		GetObjectRequest request = GetObjectRequest.builder().bucket(s3Properties.getBucketName()).key(key).build();

		return s3Client.getObject(request);
	}

}
