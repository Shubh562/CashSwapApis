package com.eztrans.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eztrans.dto.request.LoginRequest;
import com.eztrans.dto.request.SignupRequest;
import com.eztrans.dto.response.MessageResponse;
import com.eztrans.dto.response.UserInfoResponse;
import com.eztrans.models.User;
import com.eztrans.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		Optional<User> userObj = userRepository.findByMobile(loginRequest.getMobile());
		if (userObj.isPresent() && userObj.get().getPin().compareTo(loginRequest.getPin()) == 0) {
			return ResponseEntity.ok().body(new UserInfoResponse(userObj.get()));
		}
		return ResponseEntity.badRequest().body("Mobile number or pin is wrong");
		
	}

	public ResponseEntity<?> createUser(SignupRequest signUpRequest) {
		if (userRepository.existsByMobile(signUpRequest.getMobile())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Mobile number is already in use!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName() , signUpRequest.getEmail(), signUpRequest.getMobile(),
				signUpRequest.getPin());

		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
