package com.manageuser.model.dto;

import javax.persistence.Column;

public class UserBodyDTO {
	private String name;

	private String email;

	private String phone;

	private String avatar;

	private String password;

	public UserBodyDTO(String name, String email, String phone, String avatar, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.avatar = avatar;
		this.password = password;
	}

	public UserBodyDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
