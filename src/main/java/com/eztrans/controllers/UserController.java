package com.eztrans.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eztrans.dto.request.LoginRequest;
import com.eztrans.dto.request.SignupRequest;
import com.eztrans.services.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/hello")
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok("hello");
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.authenticateUser(loginRequest);
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {
		return userService.createUser(signUpRequest);
	}
}
