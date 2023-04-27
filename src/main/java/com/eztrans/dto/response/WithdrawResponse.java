package com.eztrans.dto.response;

import com.eztrans.enums.TicketStatus;
import com.eztrans.models.User;
import com.eztrans.models.WithdrawTicket;

public class WithdrawResponse {
	private long id;
	private String payerMobile;
	private String payeeMobile;
	private String payerName;
	private String payeeName;
	private TicketStatus status;
	private int statusId;
	private long amount;
	private String token;
	private String createdAt;
	private String validTill;

	public WithdrawResponse() {
	}

	public WithdrawResponse(User payer, User payee, WithdrawTicket ticket) {
		super();
		this.id = ticket.getId();
		this.payerMobile = payer.getMobile();
		this.payerName = payer.getName();
		this.payeeMobile = payee.getMobile();
		this.payeeName = payee.getName();
		this.setStatus(ticket.getStatus());
		this.amount = ticket.getAmount();
		this.token = ticket.getToken();
		this.createdAt = ticket.getCreatedAt();
		this.validTill = ticket.getValidTill();
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
}