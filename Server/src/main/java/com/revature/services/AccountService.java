package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.exceptions.BadRequestException;
import com.revature.models.Account;
import com.revature.repos.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepo;
	
	@Autowired
	public AccountService(AccountRepository repo) {
		super();
		this.accountRepo = repo;
	}
	
	@Transactional(readOnly=true)
	public List<Account> getAll() {
		return accountRepo.getAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Account add(Account newAccount) {
		if(!validateAccount(newAccount)) {
			throw new BadRequestException("Invalid account");
		}
		
		return accountRepo.save(newAccount);
		
	}
	
	
	protected boolean validateAccount(Account account) {
		if(account == null || account.getState() == "" || account.getCountry() == "") {
			return false;
		}
		return true;
	}

}
