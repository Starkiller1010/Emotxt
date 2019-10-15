package com.revature.dtos;

public class Principal {

	private int id;
	private String username;
	private String role;
	private String status;
	
	public Principal(int id, String username, String role) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
	}
	public Principal(int id, String username, String role, String status) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
