package com.revature.dtos;

import com.revature.models.Role;

public class Principal {

	private int id;
	private String username;
	private String password;
	private Role role;
	public Principal(int id, String username, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
