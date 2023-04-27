package com.eztrans.dto.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String mobile;

	@NotBlank
    private String pin;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
