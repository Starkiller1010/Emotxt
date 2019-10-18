package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.revature.exceptions.BadRequestException;
import com.revature.models.Account;
import com.revature.models.Channel;
import com.revature.models.User;
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
		List<Account> accounts = accountRepo.getAll();
		return accounts;
	}
	
	@Transactional(readOnly=true)
	public Account getAccountById(int id) {
		Account acct = accountRepo.getById(id);
		return acct;
	}
	
	@Transactional(readOnly=true) 
	public List<User> getFriends(int accountId) {
		List<User> friends = accountRepo.getAccountFriends(accountId);
		return friends;
	}
	
	@Transactional(readOnly=true) 
	public List<Channel> getChannels(int accountId) {
		List<Channel> channels = accountRepo.getAccountChannels(accountId);
		return channels;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Account add(Account newAccount) {
//		if(!validateAccount(newAccount)) {
//			throw new BadRequestException("Invalid account");
//		}
		System.out.println(newAccount + " In Services...");
		
		return accountRepo.save(newAccount);
		
	}
	
	@Transactional
	public boolean update(Account updatedAccount) {
		
//		if(!validateAccount(updatedAccount)) {
//			throw new BadRequestException("Invalid account information provided");
//		}
		
		return accountRepo.update(updatedAccount);
	}
	
	
	protected boolean validateAccount(Account account) {
		if(account == null || account.getState() == "" || account.getCountry() == "") {
			return false;
		}
		return true;
	}

}
