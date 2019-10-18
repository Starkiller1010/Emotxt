package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.Channel;
import com.revature.models.Message;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repos.ChannelRepository;

// /*
//  * The service layer that validates and calls
//  * methods from ServiceRepo.
//  */

@Service
public class ChannelService {

	private Logger log = LogManager.getLogger(UserService.class);
	private ChannelRepository channelRepo;
	
	@Autowired
	public ChannelService(ChannelRepository channelRepo) {
		this.channelRepo = channelRepo;
	}
	
// 	/**
// 	 * Calls ChannelRepository's getAll method to retrieve the
// 	 * current user's channel list.
// 	 * 
// 	 * @param currentUser - The current user that's logged in.
// 	 * @return List<Channel> - A list of channels the user belongs to.
// 	 */
	
	@Transactional(readOnly=true)
	public List<Channel> getAll(User currentUser) {
		
		log.info("Inside getAll of ChannelService.");
		return channelRepo.getAll(currentUser);
	}
	
// 	/**
// 	 * Calls ChannelRepository's getById method to retrieve a channel
// 	 * based on its ID
// 	 * 
// 	 * @param id - Integer value representing the ID.
// 	 * @return Channel - The channel with the ID. If none exists,
// 	 * null is returned.
// 	 */
	
	@Transactional(readOnly=true)
	public Channel getById(int id) {
		
		log.info("Inside getById in ChannelService.");
		return channelRepo.getById(id);
	}
	
// 	/**
// 	 * Calls ChannelRepository's getAllMembers method to retireve a list
// 	 * of all users belonging to the channel.
// 	 * 
// 	 * @param chan - The channel to get all members from.
// 	 * @return List<User> - A list of users belonging to that channel.
// 	 */
	
	@Transactional(readOnly=true)
	public List<User> getAllMembers(Channel chan) {
		
		log.info("Inside getByChannel in ChannelService.");
		return channelRepo.getAllMembers(chan);
	}
	
	/**
	 * Calls ChannelRepository's createChannel method to create a new channel
	 * 
	 * @param chan - New channel to add
	 * @return Channel - Newly added channel
	 */
	
	public Channel createChannel(Channel chan) {
		
		log.info("Inside createChannel in ChannelService.");
		return channelRepo.createChannel(chan);
	}
	
	/**
	 * Calls ChannelRepository's addMember method to add a new member to a channel.
	 * 
	 * @param chan - The channel to add the user to.
	 * @param newUser - The new user to add.
	 */

	
	public User addMember(Channel chan, User newUser) {
		
		log.info("Inside addMember in ChannelService.");
		log.info("Checking if channel is valid.");
		
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling addMember in repo now...");
			return channelRepo.addMember(newUser, chan);
		}
		return null;
	}
	
 	/**
 	 * Calls ChannelRepository's removeMember method to remove a member from a channel.
 	 * 
 	 * @param chan - The channel to remove the user from.
 	 * @param existingMember - The member to remove.
 	 */
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public User removeMember(Channel chan, User existingMember, Role role) {
		
		log.info("Inside removeMember in ChannelService.");
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling removeMember in repo now...");
			User removedUser = channelRepo.removeMember(existingMember, role, chan);
			if(removedUser == null) return null;
			return removedUser;
		}
		return null;
	}
	
// 	/**
// 	 * Calls ChannelRepository's getMessages method to get all messages belonging to a channel.
// 	 * @param chan - Channel to get all messages from.
// 	 * @return List<Message> - The list of messages.
// 	 */
	
	public List<Message> getMessages(Channel chan) {
		
		log.info("Inside getMessage in ChannelService.");
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling getMessages in repo now...");
			return channelRepo.getMessages(chan);
		}
		return null;
	}
	
// 	/**
// 	 * Calls ChannelRepository's addMessage method to add a new message to a channel.
// 	 * @param msg - The message to add.
// 	 * @param chan - The channel the message should be added to.
// 	 */
	
	public Message addMessage(Message msg, Channel chan) {
		
		log.info("Inside addMessage in ChannelService.");
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling addMessage in repo now...");
			return channelRepo.addMessage(msg, chan);
		}
		return null;
	}
	
// 	/**
// 	 * Calls ChannelRepository's getOpen method to check if a channel is open or not.
// 	 * @param chan - The channel to check the open state.
// 	 * @return boolean - True if open and false if private.
// 	 */
	
	@Transactional(readOnly=true)
	public boolean getOpen(Channel chan) {
		
		log.info("Inside getOpen in ChannelService.");
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling getOpen in repo now...");
			return channelRepo.getOpen(chan);
		}
		return false;
	}
	
// 	/**
// 	 * Calls ChannelRepository's setOpen method to change its openness.
// 	 * @param chan - Channel to set the open property of.
// 	 */
	
	@Transactional(isolation=Isolation.REPEATABLE_READ)
	public void updateOpen(Channel chan) {
		
		log.info("Inside setOpen in ChannelService.");
		if(channelRepo.getById(chan.getId()) != null) {
			log.info("The channel exists. Calling updateOpen in repo now...");
			channelRepo.updateOpen(chan);
		}
	}
	
}
