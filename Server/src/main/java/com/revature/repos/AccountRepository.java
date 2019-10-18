package com.revature.repos;

import java.util.List;

import javax.persistence.Query;

import com.revature.models.Account;
import com.revature.models.Channel;
import com.revature.models.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public AccountRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}

	public List<Account> getAll() {
		List<Account> accounts = factory.getCurrentSession().createQuery("from Account", Account.class).getResultList();
		return accounts;
		
	}

	public Account getById(int id) {
		return factory.getCurrentSession().get(Account.class, id);
	}
	
	public List<User> getAccountFriends(int accountId) {
		/*
		String query = "select f.them from Friend f "
				+ "join Account a on a.account_id = f.me "
				+ "where a.account_id = :accountId";
		*/
		String query = "select users.user_id, users.username, users.email, accounts.state, accounts.country from users join accounts on users.user_id = accounts.account_user where accounts.account_id in (select friends_list.them from friends_list join accounts on friends_list.me = accounts.account_id"
				+ " where accounts.account_id = " + accountId + ")";
				
		@SuppressWarnings("unchecked")
		List<User> friends = factory.getCurrentSession().createNativeQuery(query).getResultList();
		return friends;

	}
	
	public List<Channel> getAccountChannels(int accountId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT channels FROM accounts join subscriptions  WHERE account_id = :id");
		query.setParameter("id", accountId);
		@SuppressWarnings("unchecked")
		List<Channel> channels = query.getResultList();
		return channels;
		
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
		persistentAccount.setFriends(updatedAccount.getFriends());
		persistentAccount.setSubscriptions(updatedAccount.getSubscriptions());
		persistentAccount.setStatus(updatedAccount.getStatus());
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
