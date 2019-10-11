package com.revature.repos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Message;


public class MessageRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public MessageRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}

	public List<Message> getAll() {
		return factory.getCurrentSession().createQuery("from Account", Message.class).getResultList();
	}

	public Message getById(int id) {
		return factory.getCurrentSession().get(Message.class, id);
	}

	public Message save(Message newMessage) {
		return (Message) factory.getCurrentSession().save(newMessage);
	}

	public boolean update(Message updatedMessage) {
		Session session = factory.getCurrentSession();
		Message persistentMessage = session.get(Message.class, updatedMessage.getId());
		if(persistentMessage == null) return false;
		persistentMessage.setauthor(updatedMessage.getauthor());
		persistentMessage.setBody(updatedMessage.getBody());
		persistentMessage.setTone(updatedMessage.getTone());
		return true;
	}

	public boolean deleteById(int id) {
		Session session = factory.getCurrentSession();
		Message messageForDeletion = session.get(Message.class, id);
		if(messageForDeletion == null) return false;
		session.delete(messageForDeletion);
		return true;
	}


}
