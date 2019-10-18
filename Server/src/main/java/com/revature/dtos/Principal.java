package com.revature.dtos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Channel;
import com.revature.models.Role;

public class Principal {

	private int id;
	private int account_id;
	private String username;
	private String password;
	private Role role;
	private String state;
	private String country;
	private List<Account> friends;
	private List<Channel> subscriptions;
	
	public Principal() {
		super();
	}
	
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Account> getFriends() {
		return friends;
	}

	public void setFriends(List<Account> friends) {
		this.friends = friends;
	}

	public List<Channel> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Channel> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	
}
