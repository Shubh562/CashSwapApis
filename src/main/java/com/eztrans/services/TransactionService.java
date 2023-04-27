package com.eztrans.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eztrans.dto.request.SendMoneyRequest;
import com.eztrans.dto.request.WithdrawRequest;

@Service
public interface TransactionService {
	public ResponseEntity<?> sendMoney(SendMoneyRequest sendMoneyRequest);
	public String getQRCodeData(String token);
	public boolean verifyAtmQREncodedToken(String tokenString, String atmQrDataString);
	public ResponseEntity<?> withdrawList(WithdrawRequest request);
	public ResponseEntity<?> withdrawDetails(WithdrawRequest request);
}
