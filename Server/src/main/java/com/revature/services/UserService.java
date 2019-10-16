package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.dtos.Credentials;
import com.revature.models.User;
import com.revature.repos.UserRepository;
import com.revature.security.FieldValidator;

/**
 * A service that handles all User related interactions. It primarily handles
 * validation of values that have been passed in by uses who are authorized.
 *
 */

@Service
public class UserService {

	private Logger log = LogManager.getLogger(UserService.class);
	private UserRepository userRepo;
	private final FieldValidator validator = new FieldValidator();
	
	@Autowired
	public UserService(UserRepository repo) {
		
		log.info("Initializing UserService");
		this.userRepo = repo;
	}
	
	@Transactional(readOnly=true)
	public List<User> getAll() {
		
		log.info("Inside getAll UserService.");
		return userRepo.getAll();
	}
	
	@Transactional(readOnly=true)
	public User getById(int id) {
		
		log.info("Inside getById UserService");
		return userRepo.getById(id);
	}
	
	@Transactional(readOnly=true)
	public User getByUsername(String username) {
		
		log.info("Inside getByUsername in UserService");
		User existingUser = null;
		if(validator.validate(username)) {
			existingUser = userRepo.getByUsername(username);
		}
		return existingUser;
	}
	
	@Transactional(readOnly=true)
	public User getByCredentials(Credentials cred) {
		
		log.info("Inside getByCredentials in UserService");
		User existingUser = null;
		existingUser = userRepo.getUserByCredentials(cred.getUsername(), cred.getPassword());
		return existingUser;
	}
	
	public boolean add(User newUser) {
		
		log.info("Inside of add in UserService.");
		if(validator.validate(newUser)) {
			log.info("User is valid");
			return userRepo.add(newUser);
		}
		return false;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public boolean update(User existingUser) {
		log.info("Inside of add in UserService.");
		return userRepo.updateUser(existingUser);
	}
	
	public boolean delete(int id) {
		log.info("Inside of delete in UserService.");
		return userRepo.deleteUser(id);
	}
	
}
