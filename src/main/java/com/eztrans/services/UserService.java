package com.eztrans.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eztrans.dto.request.LoginRequest;
import com.eztrans.dto.request.SignupRequest;

@Service
public interface UserService {
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	public ResponseEntity<?> createUser(SignupRequest signupRequest);
}
