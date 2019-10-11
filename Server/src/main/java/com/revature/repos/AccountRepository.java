package com.revature.repos;

import java.lang.annotation.Annotation;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Account;

@Repository
public class AccountRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public AccountRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}

	public List<Account> getAll() {
		return factory.getCurrentSession().createQuery("from Account", Account.class).getResultList();
	}

	public Account getById(int id) {
		return factory.getCurrentSession().get(Account.class, id);
	}

	public Account save(Account newAccount) {
		return (Account) factory.getCurrentSession().save(newAccount);
	}

	public boolean update(Account updatedAccount) {
		Session session = factory.getCurrentSession();
		Account persistentAccount = session.get(Account.class, updatedAccount.getId());
		if(persistentAccount == null) return false;
		persistentAccount.setAboutMe(updatedAccount.getAboutMe());
		persistentAccount.setCountry(updatedAccount.getCountry());
		persistentAccount.setState(updatedAccount.getState());
		persistentAccount.setstatus(updatedAccount.getstatus());
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
