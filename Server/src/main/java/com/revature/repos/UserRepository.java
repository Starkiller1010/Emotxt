package com.revature.repos;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.User;

/**
 * Contains DAO-like calls to the database.
 * All methods are affiliated with the User class.
 */

@Repository
public class UserRepository {
	
	private Logger log = LogManager.getLogger(UserRepository.class);
	private SessionFactory sessionFactory;
	
	@Autowired
	UserRepository(SessionFactory sessionFactory) {
		
		log.info("Getting SessionFactory");
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Gets all users.
	 * @return List<User> - A list of all users.
	 */
	
	public List<User> getAll() {
		
		log.info("Getting all users.");
		String query = "from Users";
		return sessionFactory.getCurrentSession().createQuery(query, User.class).getResultList();
	}
	
	/**
	 * Retrieve a user given their ID
	 * @param id - The ID of the user to fetch
	 * @return User - The user with the matching ID.
	 */
	
	public User getById(int id) {
		
		log.info("Getting user by ID.");
		return sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	/**
	 * Gets user given thier unique username.
	 * @param username - String signifying the user's username.
	 * @return User - The user with the given username.
	 */
	public User getByUsername(String username) {
		
		log.info("Getting user by username.");
		String query = "from Users where user_username = :username";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, User.class)
					.setParameter(1, username)
					.getSingleResult();
		} catch(NoResultException e) {
			log.warn("No result found.");
			return null;
		}
	}
	
	/**
	 * Get a user matching the username and password.
	 * @param username - The user's username.
	 * @param password - The user's password.
	 * @return A user with matching credentials or null if no user is found.
	 */
	
	public User getUserByCredentials(String username, String password) {
		
		log.info("Getting user by credentials.");
		String query = "from Users where username = :un and password = :passwd";
		
		try {
			return sessionFactory.getCurrentSession().createQuery(query, User.class)
					.setParameter(1, username)
					.setParameter(2, password)
					.getSingleResult();
		}
		
		catch(NoResultException e) {
			log.warn("No user found.");
			return null;
		}
	}
	
	/**
	 * Creates a new user.
	 * @param newUser - The new user to add.
	 * @return boolean - true on success. False on failure.
	 * If the transaction fails it is rolled back.
	 */
	
	public boolean add(User newUser) {
		
		log.info("Inside add for UserRepo");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			log.info("Beginning transaction.");
			// Begin Transaction
			session.beginTransaction();
			
			// Persist user that was passed in.
			session.save(newUser);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			log.info("Transaction failed! Rolling back changes now.");
			session.getTransaction().rollback();
			return false;
		}
		log.info("Transaction successful!");
		return true;
	}
	
	/**
	 * Updates a user with new information.
	 * The transaction will throw an exception if the user does not exist.
	 * If this happens, the transaction will be rolled back.
	 * 
	 * @param newUser - User with the same ID as the user to update.
	 * @return boolean - true on success and false on failure/rollback.
	 */
	
	public boolean updateUser(User newUser) {
		
		log.info("Inside updateUser in UserRepo.");
		Session session = sessionFactory.getCurrentSession();
		try {
			log.info("Beginning transaction.");
			session.beginTransaction();
			session.update(newUser);
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			log.info("Transaction failed! Rolling back changes now.");
			session.getTransaction().rollback();
			return false;
		}
		log.info("Transaction successful!");
		return true;
	}
	
	/**
	 * Deletes a user. If the transaction fails for any reason, it will be
	 * rolled back.
	 * 
	 * @param newUser - ID of the user to delete.
	 * @return boolean - True on success and false on failure/rollback.
	 */
	
	public boolean deleteUser(int id) {
		
		log.info("Inside deleteUser in UserRepo.");
		Session session = sessionFactory.getCurrentSession();
		
		try {
			log.info("Loading user from session with ID of " + id);
			User delUser = session.load(User.class, id);
			log.info("Beginning transaction");
			session.beginTransaction();
			session.delete(delUser);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			log.info("Transaction failed! Rolling back now.");
			session.getTransaction().rollback();
			return false;
		}
		log.info("Transaction succeeded!");
		return true;
	}
}
