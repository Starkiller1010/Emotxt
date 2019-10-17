package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.Credentials;
import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.UserService;

/**
 * 
 * Maps GET, POST, PUT, etc. for user methods.
 * Maps to /user
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	private Logger log = LogManager.getLogger(UserController.class);
	private UserService userService;
	
	@Autowired
	public UserController(UserService service) {
		this.userService = service;
	}
	
	/**
	 * GET for retrieving a user by their ID.
	 * @param id - the id to attempt to fetch the user with.
	 * @return User - A user possessing the given ID or null.
	 */
	
	@GetMapping(value="/id/{id}", produces="application/json")
	public User getUserById(@PathVariable int id) {
		
		log.info("Inside of getUserById of UserController.");
		return userService.getById(id);
	}
	
	/**
	 * GET for retrieving a user by their ID.
	 * @param username - the username to attempt to fetch the user with.
	 * @return User - A user possessing the given username or null.
	 */
	
	@GetMapping(value="/username/{username}", produces="application/json")
	public User getUserByUsername(@PathVariable String username) {
		
		log.info("Inside of getUserByUsername of UserController.");
		return userService.getByUsername(username);
	}
	
	/**
	 * GET for retrieving a user by their ID.
	 * @param Principal - A principal for the user, which contains their credentials.
	 * @return User - A user possessing the given username or null.
	 */
	
	@GetMapping(value="/credentials", consumes="applicaiton/josn", produces="application/json")
	public User getUserByCredentails(@RequestBody Principal principal) {
		
		log.info("Inside of getUserByCredentials of UserController.");
		Credentials cred = new Credentials(principal.getUsername(), principal.getPassword());
		return userService.getByCredentials(cred);
	}
	
	/**
	 * POST for adding a new user.
	 * @param User - The new User to add.
	 * @return ResponseEntity - ResponseEntity containing the appropriate status code.
	 */
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<User> addUser(@RequestBody User newUser) {
		
		log.info("Inside of addUser of UserController.");
		newUser = userService.add(newUser);
		return (newUser != null)
				? new ResponseEntity<>(newUser, HttpStatus.CREATED)
				: new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * PUT for modifying an existing user.
	 * @param User - The new User to add.
	 * @return ResponseEntity - ResponseEntity containing the appropriate status code.
	 */
	
	@PutMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<User> updateUser(@RequestBody User existingUser) {
		
		log.info("Inside of addUser of UserController.");
		existingUser = userService.update(existingUser);
		return (existingUser != null)
				? new ResponseEntity<>(existingUser, HttpStatus.OK)
				: new ResponseEntity<>(existingUser, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * DELETE for modifying an existing user.
	 * @param User - The new User to add.
	 * @return ResponseEntity - ResponseEntity containing the appropriate status code.
	 */
	
	@DeleteMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<HttpStatus> deleteUser(@RequestBody User existingUser) {
		
		log.info("Inside of deleteUser of UserController.");
		log.info(existingUser.getId());
		boolean wasDeleted = userService.delete(existingUser.getId());
		return (wasDeleted == true)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
