package com.revature.repos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Channel;
import com.revature.models.Message;
import com.revature.models.User;

public class ChannelRepository {
	
	private SessionFactory factory;
	
	@Autowired
	public ChannelRepository(SessionFactory sessionFactory) {
		this.factory = sessionFactory;
	}
	
	/**
	 * Returns all channels owned by a logged in user.
	 * @param currentUser
	 * @return
	 */
	
	public List<Channel> getAll(User currentUser) {
		String query = "from channel_users u where u.user_id = :current_user";
		return factory.getCurrentSession().createQuery(query, Channel.class)
				.setParameter("current_user", currentUser.getId())
				.getResultList();
	}
	
	/**
	 * Locates a channel given its ID.
	 * @param id
	 * @return
	 */
	
	public Channel getById(int id) {
		String query = "from channels where channel_id = :id";
		return factory.getCurrentSession().createQuery(query, Channel.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	/**
	 * Gets all members in a channel
	 * @param owner
	 * @return
	 */
	
	public List<Channel> getAllMembers(User owner) {
		String query = "from channel_users";
		return factory.getCurrentSession().createQuery(query, Channel.class).getResultList();
	}
	
	/**
	 * Adds a new user to a given channel
	 * @param newUser
	 * @param chan
	 */
	
	public void addMember(User newUser, Channel chan) {
		List<User> memberList = chan.getMembers();
		memberList.add(newUser);
		chan.setMembers(memberList);
	}
	
	/**
	 * Remove a user from a channel
	 * @param newUser
	 * @param chan
	 */
	
	public void deleteMember(User newUser, Channel chan) {
		ArrayList<User> memberList = (ArrayList<User>) chan.getMembers();
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).getUsername() == newUser.getUsername()) {
				memberList.remove(i);
				break;
			}
		}
		chan.setMembers(memberList);
	}
	
	/**
	 * Get all messages
	 * @param chan
	 * @return
	 */
	
	public List<Message> getMessages(Channel chan) {
		String query = "from channels";
		return factory.getCurrentSession().createQuery(query, Message.class).getResultList();
	}
	
	/**
	 * Adds a new message.
	 * @param msg
	 * @param chan
	 */
	
	public void addMessage(Message msg, Channel chan) {
		List<Message> messages = chan.getMessages();
		messages.add(msg);
		chan.setMessages(messages);
	}
	
	/**
	 * Determine if the channel is open
	 * @param chan
	 * @return
	 */
	
	public boolean getOpen(Channel chan) {
		String query = "select open from channels where channel_id = :id";
		return factory.getCurrentSession().createQuery(query, Channel.class)
				.setParameter("id", chan.getId())
				.getSingleResult()
				.isOpen();
	}
	
	/**
	 * Toggle open for a channel
	 * @param chan
	 * @param bool
	 */
	
	public void updateOpen(Channel chan, boolean bool) {
		chan.setOpen();
	}
}
