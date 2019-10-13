package com.revature.repos;

import java.lang.annotation.Annotation;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;
import com.revature.models.User;

@Repository
public class AccountRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public AccountRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}

	public List<Account> getAll() {
		List accounts = factory.getCurrentSession().createQuery("from Account", Account.class).getResultList();
		System.out.println(accounts);
		return accounts;
	}

	public Account getById(int id) {
		return factory.getCurrentSession().get(Account.class, id);
	}
	
	public List<User> getAccountFriends(int accountId) {
		
	}

	public Account save(Account newAccount) {
		System.out.println("In repos: " + newAccount.getId());
		factory.getCurrentSession().save(newAccount);
		return newAccount;
	}

	public boolean update(Account updatedAccount) {
		Session session = factory.getCurrentSession();
		Account persistentAccount = session.get(Account.class, updatedAccount.getId());
		if(persistentAccount == null) return false;
		persistentAccount.setBio(updatedAccount.getBio());
		persistentAccount.setCountry(updatedAccount.getCountry());
		persistentAccount.setState(updatedAccount.getState());
		persistentAccount.setFriendsList(updatedAccount.getFriendsList());
		persistentAccount.setSubscriptions(updatedAccount.getSubscriptions());
	
		return true;
	}

	public boolean deleteById(int id) {
		Session session = factory.getCurrentSession();
		Account accountForDeletion = session.get(Account.class, id);
		if(accountForDeletion == null) return false;
		session.delete(accountForDeletion);
		return true;
	}

	
}
