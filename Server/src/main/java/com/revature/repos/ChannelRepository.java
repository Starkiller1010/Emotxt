package com.revature.repos;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.models.Channel;
import com.revature.models.Message;
import com.revature.models.Role;
import com.revature.models.User;

@Repository
public class ChannelRepository {
	
	private SessionFactory factory;
	private Logger log = LogManager.getLogger(ChannelRepository.class);
	
	@Autowired
	public ChannelRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	/**
	 * Returns all channels owned by a logged in user.
	 * @param currentUser - The current user who's logged in.
	 * @return List<Channel> - A list of all channels belonging to a specific user.
	 */
	
	public List<Channel> getAll(User currentUser) {
		
		log.info("Inside of getAll in ChannelRepository.");
		String query = "from channel_users u where u.user_id = :current_user";
		try {
			return factory.getCurrentSession().createQuery(query, Channel.class)
					.setParameter("current_user", currentUser.getId())
					.getResultList();
		}
		catch(NoResultException e) {
			return null;
		}
		
	}
	
	/**
	 * Locates a channel given its ID.
	 * @param id - The id of a channel to get.
	 * @return Channel - The channel that was fetched.
	 */
	
	public Channel getById(int id) {
		
		log.info("Inside of getById in ChannelRepository.");
		String query = "from channels where channel_id = :id";
		try {
			return factory.getCurrentSession().createQuery(query, Channel.class)
					.setParameter("id", id)
					.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Gets all members in a channel
	 * @return List<User> - A list of users belonging 
	 */
	
	@SuppressWarnings("unchecked")
	public List<User> getAllMembers(Channel chan) {
		
		log.info("Inside of getAllMembers in ChannelRepository.");
//		Map<Role, User> members = new HashMap<Role, User>();
		String query = "select new map(c.members_KEY, c.members_user_id) from channel_users c where c.channel_id = :id";
		//String query = "select new Role from channel_users.members_KEY where channel_id = :id";
		try {
			return factory.getCurrentSession().createQuery(query)
					.setParameter("id", chan.getId())
					.getResultList();
			
			
//			List<Map<Role, User>> result = (List<Map<Role, User>>) factory.getCurrentSession().createQuery(query)
//					.setParameter("id", chan.getId())
//					.getResultList();
//			for(Map<Role, User> el : result) {
//				el.forEach((k, v) -> members.put(k, v));
//			}
//			return members;
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Adds a new user to a given channel
	 * @param newUser - The user to be added.
	 * @param chan - The channel to add the new user to.
	 */
	
	public Channel createChannel(Channel chan) {
		
		log.info("Inside of createChannel in ChannelRepository.");
		Session session = factory.openSession();
		try {
			log.info("Beginning transaction...");
			session.beginTransaction();
			session.save(chan);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			log.warn("Transaction failed! Rolling back changes now!");
			session.getTransaction().rollback();
			return null;
		}
		finally {
			log.info("Closing session.");
			session.close();
		}
		log.info("Transaction was successful!");
		return chan;
	}
	
	/**
	 * Adds a new user to a given channel
	 * @param newUser - The user to be added.
	 * @param chan - The channel to add the new user to.
	 * @return 
	 */
	
	public User addMember(User newUser, Channel chan) {
		
		log.info("Inside of addMember in ChannelRepository.");
		Map<Role, User> memberList = chan.getMembers();
		if(memberList.size() < 1) {
			memberList.put(Role.OWNER, newUser);
			chan.setMembers(memberList);
		}
		else {
			memberList.put(Role.USER, newUser);
			chan.setMembers(memberList);
		}
		return newUser;
	}
	
	/**
	 * Remove a user from a channel
	 * @param newUser - User to remove from channel.
	 * @param chan - Channel the user will be removed from.
	 */
	
	public User removeMember(User delUser, Role role, Channel chan) {
		
		log.info("Inside of removeMember in ChannelRepository.");
		Map<Role, User> memberList = chan.getMembers();
		if(memberList.containsValue(delUser)) {
			memberList.remove(role, delUser);
			chan.setMembers(memberList);
			return delUser;
		}
		return null;
	}
	
	/**
	 * Get all messages
	 * @param chan - Channel to get messages from.
	 * @return List<Messages> - List of messages to return.
	 */
	
	public List<Message> getMessages(Channel chan) {
		
		log.info("Inside of getMessages in ChannelRepository.");
		String query = "select message_id from channels";
		return factory.getCurrentSession().createQuery(query, Message.class).getResultList();
	}
	
	/**
	 * Adds a new message.
	 * @param msg - message to add
	 * @param chan - Channel to add the message to
	 */
	
	public Message addMessage(Message msg, Channel chan) {
		
		log.info("Inside of addMessages in ChannelRepository.");
		if(msg.getBody().trim() == "" || msg.getBody().trim().isEmpty()) {
			log.warn("Message body was empty.");
			return null;
		}
		List<Message> messages = chan.getMessages();
		messages.add(msg);
		chan.setMessages(messages);
		return msg;
	}
	
	/**
	 * Determine if the channel is open
	 * @param chan
	 * @return boolean - Is the channel open or not?
	 */
	
	public boolean getOpen(Channel chan) {
		
		log.info("Inside of getOpen in ChannelRepository.");
		String query = "select open from channels where channel_id = :id";
		try {
			return factory.getCurrentSession().createQuery(query, Channel.class)
					.setParameter("id", chan.getId())
					.getSingleResult()
					.isOpen();
		}
		catch(NoResultException e) {
			return false;		// If a channel does not exist, it should be private.
		}
	}
	
	/**
	 * Toggle open for a channel
	 * @param chan - Channel to be modified.
	 */
	
	public void updateOpen(Channel chan) {
		
		log.info("Inside of updateOpen in ChannelRepository.");
		chan.setOpen();
	}
}