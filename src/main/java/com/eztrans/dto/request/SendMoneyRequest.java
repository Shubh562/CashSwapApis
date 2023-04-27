package com.eztrans.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class SendMoneyRequest {
	@NotBlank
	private String payerMobile;
	@NotBlank
	private String payeeMobile;
	@Min(0)
	private int amount;

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getPayeeMobile() {
		return payeeMobile;
	}

	public void setPayeeMobile(String payeeMobile) {
		this.payeeMobile = payeeMobile;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
