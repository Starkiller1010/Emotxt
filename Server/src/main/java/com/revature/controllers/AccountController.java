package com.revature.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Account;
import com.revature.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService service) {
		super();
		this.accountService = service;
	}
	
	@GetMapping(produces="application/json")
	public List<Account> getAllAccounts() {
		return accountService.getAll();
	}
	
}
