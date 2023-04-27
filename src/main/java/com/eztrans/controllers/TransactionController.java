package com.eztrans.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eztrans.dto.request.AtmQrDataRequest;
import com.eztrans.dto.request.LoginRequest;
import com.eztrans.dto.request.SendMoneyRequest;
import com.eztrans.dto.request.SignupRequest;
import com.eztrans.dto.request.WithdrawRequest;
import com.eztrans.services.TransactionService;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping(value = "/sendMoney")
	public ResponseEntity<?> sendMoney(@Valid @RequestBody SendMoneyRequest request) {
		return transactionService.sendMoney(request);
	}

	@PostMapping(value = "/withdrawList")
	public ResponseEntity<?> withdrawList(@Valid @RequestBody WithdrawRequest request) {
		return transactionService.withdrawList(request);
	}

	@PostMapping(value = "/withdrawDetails")
	public ResponseEntity<?> withdrawDetails(@Valid @RequestBody WithdrawRequest request) {
		return transactionService.withdrawDetails(request);
	}
	
	@PostMapping(value = "/verifyAtmQrData")
	public ResponseEntity<?> verifyAtmQrData(@Valid @RequestBody AtmQrDataRequest request) {
		return ResponseEntity.ok("success: " + transactionService.verifyAtmQREncodedToken(request.getTokenString(), request.getAtmQrDataString()));
	}
}
