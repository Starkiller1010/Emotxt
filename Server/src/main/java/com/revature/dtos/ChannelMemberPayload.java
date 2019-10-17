package com.revature.dtos;

import com.revature.models.Channel;
import com.revature.models.Role;
import com.revature.models.User;

/**
 * A DTO used for channel-member methods. It allows the response body to contain
 * more than one object.
 *
 */
public class ChannelMemberPayload {
	
	private User user;
	private Channel channel;	
	private Role role;
	
	public ChannelMemberPayload() {
		super();
	}
	
	public ChannelMemberPayload(User user, Channel channel, String role) {
		super();
		this.user = user;
		this.channel = channel;
		this.role = Role.valueOf(role);
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
