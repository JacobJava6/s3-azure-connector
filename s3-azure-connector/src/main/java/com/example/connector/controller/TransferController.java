package com.example.connector.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.connector.model.TransferResult;
import com.example.connector.service.TransferService;

@RestController
public class TransferController {
	
	
	private final TransferService transferService;
	
	public TransferController(TransferService transferService) {
		this.transferService=transferService;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<TransferResult> transfer(){
		TransferResult result= transferService.transfer();
		return ResponseEntity.ok(result);
	}

}
