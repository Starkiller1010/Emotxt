package com.revature.dtos;

import com.revature.models.Channel;
import com.revature.models.Message;

public class ChannelMessagePayload {
	
	private Message msg;
	private Channel chan;
	public ChannelMessagePayload() {
		super();
	}
	public ChannelMessagePayload(Message msg, Channel chan) {
		super();
		this.msg = msg;
		this.chan = chan;
	}
	public Message getMsg() {
		return msg;
	}
	public void setMsg(Message msg) {
		this.msg = msg;
	}
	public Channel getChan() {
		return chan;
	}
	public void setChan(Channel chan) {
		this.chan = chan;
	}

}
