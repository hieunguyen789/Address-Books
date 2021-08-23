package com.cfmaddressbook.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="email", unique = true)
	@Size(min=4, max=30, message="minum of 2 characters")
	private String email;
	
	@Column(name="password")
	@Size(min=4, message="password need at least 4 characters")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="enable")
	private boolean enable;


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", enable="
				+ enable + "]";
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

}
