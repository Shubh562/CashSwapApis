package com.eztrans.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TokenGenerator {
	private final int TOKEN_LENGTH = 32;

	private final String NUMERIC_CHARACTERS = "0123456789";

	public String generateToken() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] tokenBytes = new byte[TOKEN_LENGTH];
		secureRandom.nextBytes(tokenBytes);
		String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
		return token;
	}

	public String generateNumericToken(int tokenLength) {
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokenLength; i++) {
			int randomIndex = secureRandom.nextInt(NUMERIC_CHARACTERS.length());
			sb.append(NUMERIC_CHARACTERS.charAt(randomIndex));
		}
		return sb.toString();
	}
}
