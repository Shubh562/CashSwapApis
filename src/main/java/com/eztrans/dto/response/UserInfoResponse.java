package com.eztrans.dto.response;

import com.eztrans.models.User;

public class UserInfoResponse {
	private Long id;
	private String mobile;
	private String email;
	private String name;
	

	
	public UserInfoResponse(User user) {
		super();
		this.id = user.getId();
		this.mobile = user.getMobile();
		this.email = user.getEmail();
		this.name = user.getName();
	}

	public UserInfoResponse(Long id, String mobile, String email, String name) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.name = name;
	}
		

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
