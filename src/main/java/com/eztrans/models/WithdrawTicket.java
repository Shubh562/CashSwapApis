package com.eztrans.models;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Formula;

import com.eztrans.enums.TicketStatus;

@Entity
@Table(name = "withdraw_ticket", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class WithdrawTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String payerMobile;
	private String payeeMobile;
	private String payerName;
	private String payeeName;
	private TicketStatus status;
	private long amount;
	private String token;
	private Date createdAt;
	private Date validTill;

	@Formula("status::numeric")
	private int statusId;

	public WithdrawTicket() {
		super();
	}

	public WithdrawTicket(String payerMobile, String payeeMobile, String payerName, String payeeName, long amount,
			String token) {
		super();
		this.payerMobile = payerMobile;
		this.payeeMobile = payeeMobile;
		this.payerName = payerName;
		this.payeeName = payeeName;
		this.amount = amount;
		this.token = token;
		this.status = TicketStatus.ACTIVE;
		this.setCreatedAt(new Date());
		this.setValidTill(new Date(createdAt.getTime() + 2L * 24 * 60 * 60 * 1000));
		this.statusId = this.status.getValue();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCreatedAt() {
		return createdAt.toString();
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getValidTill() {
		return validTill.toString();
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
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
