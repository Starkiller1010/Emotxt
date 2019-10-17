package com.revature.repos;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;
import com.revature.models.Channel;
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
		return accounts;
		
	}

	public Account getById(int id) {
		Account account = factory.openSession().get(Account.class, id);
		return account;
	}
	

	
	public List<User> getAccountFriends(int accountId) {
		String query = "select username from Friends_list "
				+ "join accounts on Accounts.account_id = Freinds_list.me "
				+ "join acounts on Accounts.account_id = Friends_list.them "
				+ "join users on Users.user_id = Accounts.userInfo "
				+ "where Accounts.account_id = :accountId";
		
		return factory.getCurrentSession().createQuery(query, User.class)
			.setParameter("accountId", accountId)
			.getResultList();

	}
	
	public List<Channel> getAccountChannels(int accountId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("SELECT channels FROM accounts join subscriptions  WHERE account_id = :id");
		query.setParameter("id", accountId);
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
		persistentAccount.setFriendsList(updatedAccount.getFriendsList());
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
