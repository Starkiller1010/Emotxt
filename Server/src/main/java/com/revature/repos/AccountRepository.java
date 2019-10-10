package com.revature.repos;

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
}
