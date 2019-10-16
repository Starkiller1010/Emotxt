package com.revature.security;

import static com.revature.utils.RegexUtil.USERNAME_REGEX;
import static com.revature.utils.RegexUtil.EMAIL_REGEX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserService;

/**
 * 
 * Contains methods used to validate fields during registration, login, and
 * when calling other services.
 *
 */
public class FieldValidator {
	
	private Logger log = LogManager.getLogger(UserService.class);
	
	/**
	 * 
	 * @param username - The username to validate.
	 * @return boolean - True if the username is valid and false if it is not.
	 */
		
	public boolean validate(String username) {
		log.info("Validating username...");
		if(username == null || username.trim().equals("") || !username.matches(USERNAME_REGEX)) {
			log.warn("Username is invalid!");
			return false;
		}
		log.info("Username is valid.");
		return true;
	}
	
	/**
	 * Validates relevant fields for new users.
	 * @param newUser - User to validate.
	 * @return boolean - True if all values are valid and false otherwise.
	 */
	
	public boolean validate(User newUser) {
		
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String email = newUser.getEmail();
		
		log.info("Validating User...");
		log.info("Validating username...");
		if(username == null || username.trim().equals("") || !username.matches(USERNAME_REGEX)) {
			log.warn("Username is invalid!");
			return false;
		}
		if(password == null || password.trim().equals("") || password.length() < 8) {
			log.warn("Password is invalid!");
			return false;
		}
		if(email == null || email.trim().equals("") || !email.matches(EMAIL_REGEX)) {
			log.warn("Email is invalid!");
			return false;
		}
		return true;
	}
}
