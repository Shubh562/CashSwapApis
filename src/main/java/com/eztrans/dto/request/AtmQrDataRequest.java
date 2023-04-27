package com.eztrans.dto.request;

import javax.validation.constraints.NotBlank;

public class AtmQrDataRequest {
	@NotBlank
	private String tokenString;

	@NotBlank
    private String atmQrDataString;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public String getAtmQrDataString() {
		return atmQrDataString;
	}

	public void setAtmQrDataString(String atmQrDataString) {
		this.atmQrDataString = atmQrDataString;
	}

}
