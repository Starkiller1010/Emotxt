package com.revature.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.ErrorResponse;
import com.revature.exceptions.BadRequestException;
import com.revature.models.Account;
import com.revature.models.Channel;
import com.revature.models.User;
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
	
	@GetMapping(value="/{id}/friends", produces="application/json")
	public List<User> getAccountFriends(@PathVariable int id) {
		if(id < 1) {
			throw new BadRequestException("Invalid id for account.");
		}
		
		return  accountService.getFriends(id);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public Account getAccountById(@PathVariable int id) {
		
		return accountService.getAccountById(id);
	}
	
	
	@PostMapping(produces="application/json", consumes="application/json")
	public Account addAccount(@RequestBody Account newAccount, HttpServletResponse resp) {
		Account account = accountService.add(newAccount);
		
		return account;
	}
	
	@PutMapping(produces="application/json", consumes="application/json")
	public boolean updateAccount(@RequestBody Account updatedAccount, HttpServletResponse resp) {
		boolean didUpdate = accountService.update(updatedAccount);
		return didUpdate;
	}
	
	
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse handleBadRequestException(BadRequestException bre) {
		ErrorResponse err = new ErrorResponse();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setMessage(bre.getMessage());
		err.setTimestamp(System.currentTimeMillis());
		return err;
	}
		
}
