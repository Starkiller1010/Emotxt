package com.revature.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.ChannelMemberPayload;
import com.revature.dtos.ChannelMessagePayload;
import com.revature.models.Channel;
import com.revature.models.Message;
import com.revature.models.User;
import com.revature.services.ChannelService;

/**
 * 
 * Contains GET, POST, PUT and DELETE mappings
 * for channels.
 *
 */
@RestController
@RequestMapping("/channels")
public class ChannelController {

	private Logger log = LogManager.getLogger(ChannelController.class);
	private ChannelService channelService;
	
	/**
	 * GET mapping to retrieve a channel given its ID.
	 * @param id - The ID of the channel to fetch.
	 * @return Channel - The channel with the matching ID
	 */
	
	@GetMapping(value="/id/{id}", produces="application/json")
	public Channel getById(@PathVariable int id) {
		log.info("Inside getChannelById in ChannelController.");
		return channelService.getById(id);
	}
	
	/**
	 * GET mapping to get all members of a given channel.
	 * @param R - The ID of the channel to fetch.
	 * @return Channel - The channel with the matching ID
	 */
	
	@GetMapping(value="/members", produces="application/json")
	public List<User> getAllMembers(@RequestBody Channel chan) {
		log.info("Inside getAllMembers in ChannelController.");
		return channelService.getAllMembers(chan);
	}
	
	/**
	 * POST mapping to create new channel
	 * @param chan - New channel to create
	 * @return Channel - The newly created channel
	 */
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<Channel> createChannel(@RequestBody Channel chan) {
		log.info("Inside createChannel in ChannelController.");
		chan = channelService.createChannel(chan);
		return (chan != null) 
				? new ResponseEntity<>(chan, HttpStatus.CREATED)
				: new ResponseEntity<>(chan, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * POST mapping to add a user to a channel
	 * @param cmp - An object containing a channel and a user, so they can be mapped.
	 * @return ResponseEntity - Added user and status code.
	 */
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<User> addUserToChannel(@RequestBody ChannelMemberPayload cmp) {
		
		log.info("Inside addUserToChannel in ChannelController.");
		User addedUser = channelService.addMember(cmp.getChannel(), cmp.getUser());
		return (addedUser != null) 
				? new ResponseEntity<>(addedUser, HttpStatus.CREATED)
				: new ResponseEntity<>(addedUser, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * POST mapping to add a message to a channel
	 * @param cmp - An object containing a channel and a message, so they can be mapped.
	 * @return ResponseEntity - Added message and status code.
	 */
	
	@PostMapping(consumes="application/json", produces="application/json")
	public ResponseEntity<Message> addMessageToChannel(@RequestBody ChannelMessagePayload cmp) {
		
		log.info("Inside addUserToChannel in ChannelController.");
		Message addedMsg = channelService.addMessage(cmp.getMsg(), cmp.getChan());
		return (addedMsg != null) 
				? new ResponseEntity<>(addedMsg, HttpStatus.CREATED)
				: new ResponseEntity<>(addedMsg, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * DELETE mapping to remove a user from a channel
	 * @param newUser - User to remove
	 * @return ResponseEntity - http status code
	 */
	
	@DeleteMapping(consumes="applicaiton/json", produces="application/json")
	public ResponseEntity<HttpStatus> removeUserFromChannel(@RequestBody ChannelMemberPayload cmp) {
		
		log.info("Inside removeUserFromChannel in ChannelController.");
		User removedUser = channelService.removeMember(cmp.getChannel(), cmp.getUser(), cmp.getRole());
		return (removedUser != null)
				? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
}
