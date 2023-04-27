package com.eztrans.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "token", uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "atmQrDataString"), @UniqueConstraint(columnNames = "atmQrDataString") })
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String tokenString;
	private String atmQrDataString;
	private Date createdAt;

	public Token() {
	}

	public Token(String tokenString, String atmQrDataString) {
		super();
		this.tokenString = tokenString;
		this.atmQrDataString = atmQrDataString;
		this.setCreatedAt(new Date());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public String getAtmQrDataString() {
		return atmQrDataString;
	}

	public void setAtmQrDataString(String atmArDataString) {
		this.atmQrDataString = atmArDataString;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
