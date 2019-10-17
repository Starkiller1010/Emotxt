// package com.revature.repos;

// import java.util.List;

// import javax.persistence.NoResultException;

// import org.hibernate.SessionFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;

// import com.revature.models.Channel;
// import com.revature.models.Message;
// import com.revature.models.Role;
// import com.revature.models.User;

// @Repository
// public class ChannelRepository {
	
// 	private SessionFactory factory;
	
// 	@Autowired
// 	public ChannelRepository(SessionFactory sessionFactory) {
// 		this.factory = sessionFactory;
// 	}
	
// 	/**
// 	 * Returns all channels owned by a logged in user.
// 	 * @param currentUser - The current user who's logged in.
// 	 * @return List<Channel> - A list of all channels belonging to a specific user.
// 	 */
	
// 	public List<Channel> getAll(User currentUser) {
		
// 		String query = "from channel_users u where u.user_id = :current_user";
// 		try {
// 			return factory.getCurrentSession().createQuery(query, Channel.class)
// 					.setParameter("current_user", currentUser.getId())
// 					.getResultList();
// 		}
// 		catch(NoResultException e) {
// 			return null;
// 		}
		
// 	}
	
// 	/**
// 	 * Locates a channel given its ID.
// 	 * @param id - The id of a channel to get.
// 	 * @return Channel - The channel that was fetched.
// 	 */
	
// 	public Channel getById(int id) {
		
// 		String query = "from channels where channel_id = :id";
// 		try {
// 			return factory.getCurrentSession().createQuery(query, Channel.class)
// 					.setParameter("id", id)
// 					.getSingleResult();
// 		}
// 		catch(NoResultException e) {
// 			return null;
// 		}
// 	}
	
// 	/**
// 	 * Gets all members in a channel
// 	 * @return List<User> - A list of users belonging 
// 	 */
	
// 	public List<User> getAllMembers(Channel chan) {
		
// 		String query = "from channel_users where channel_id = :id";
		
// 		try {
// 			return factory.getCurrentSession().createQuery(query, User.class)
// 					.setParameter("channel_id", chan.getId())
// 					.getResultList();
// 		}
// 		catch(NoResultException e) {
// 			return null;
// 		}
// 	}
	
// 	/**
// 	 * Adds a new user to a given channel
// 	 * @param newUser - The user to be added.
// 	 * @param chan - The channel to add the new user to.
// 	 */
	
// //	public void addMember(User newUser, Channel chan) {
// //		HashMap<User, Role> memberList = chan.getMembers();
// //		if(memberList.size() < 1) {
// //			memberList.put(newUser, Role.OWNER);
// //			chan.setMembers(memberList);
// //		}
// //		else {
// //			memberList.put(newUser, Role.USER);
// //			chan.setMembers(memberList);
// //		}
// //	}
	
// 	public void addMember(User newUser, Channel chan) {
// 		List<User> memberList = chan.getMembers();
// 		memberList.add(newUser);
// 		chan.setMembers(memberList);
// 	}
	
// 	/**
// 	 * Remove a user from a channel
// 	 * @param newUser - User to remove from channel.
// 	 * @param chan - Channel the user will be removed from.
// 	 */
	
// //	public void deleteMember(User delUser, Role role, Channel chan) {
// //		HashMap<User, Role> memberList = chan.getMembers();
// //		for(int i = 0; i < memberList.size(); i++) {
// //			if(memberList.containsKey(delUser)) {
// //				memberList.remove(delUser);
// //				break;
// //			}
// //		}
// //		chan.setMembers(memberList);
// //	}
	
// 	public void removeMember(User delUser, Channel chan) {
// 		List<User> memberList = chan.getMembers();
// 		if(memberList.contains(delUser)) {
// 			memberList.remove(delUser);
// 		}
// 		chan.setMembers(memberList);
// 	}
	
// 	/**
// 	 * Get all messages
// 	 * @param chan - Channel to get messages from.
// 	 * @return List<Messages> - List of messages to return.
// 	 */
	
// 	public List<Message> getMessages(Channel chan) {
// 		String query = "select message_id from channels";
// 		return factory.getCurrentSession().createQuery(query, Message.class).getResultList();
// 	}
	
// 	/**
// 	 * Adds a new message.
// 	 * @param msg - message to add
// 	 * @param chan - Channel to add the message to
// 	 */
	
// 	public void addMessage(Message msg, Channel chan) {
// 		List<Message> messages = chan.getMessages();
// 		messages.add(msg);
// 		chan.setMessages(messages);
// 	}
	
// 	/**
// 	 * Determine if the channel is open
// 	 * @param chan
// 	 * @return boolean - Is the channel open or not?
// 	 */
	
// 	public boolean getOpen(Channel chan) {
		
// 		String query = "select open from channels where channel_id = :id";
// 		try {
// 			return factory.getCurrentSession().createQuery(query, Channel.class)
// 					.setParameter("id", chan.getId())
// 					.getSingleResult()
// 					.isOpen();
// 		}
// 		catch(NoResultException e) {
// 			return false;		// If a channel does not exist, it should be private.
// 		}
// 	}
	
// 	/**
// 	 * Toggle open for a channel
// 	 * @param chan - Channel to be modified.
// 	 */
	
// 	public void updateOpen(Channel chan) {
// 		chan.setOpen();
// 	}
// }
