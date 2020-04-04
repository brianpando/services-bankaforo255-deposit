package com.app.aforo255.deposit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.aforo255.deposit.domain.Transaction;
import com.app.aforo255.deposit.producer.DepositEventProducer;
import com.app.aforo255.deposit.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class DepositEventController {

	private Logger log = LoggerFactory.getLogger(DepositEventController.class);
	@Autowired
	private ITransactionService transactionService; 

	@Autowired
	DepositEventProducer depositEventProducer;
	@PostMapping("/v1/depositEvent")
	public ResponseEntity<Transaction> postLibraryEvent(@RequestBody Transaction transactionEvent) throws JsonProcessingException{
		
		Transaction transSql = transactionService.save(transactionEvent);
		log.info("antes sendDepositEvent_Approach3 ");
		depositEventProducer.sendDepositEvent_Approach3(transSql);
		log.info("despues sendDepositEvent_Approach3 ");	
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transSql);
		
	}
	
}
