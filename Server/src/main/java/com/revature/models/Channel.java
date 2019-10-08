package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Channel
 */
@Entity
@Table(name = "CHANNELS")
public class Channel {

    @Id
    @Column(name = "CHANNEL_ID")
    private int id;

    private List<MessageBoard> boards; // List of boards that have this Channel

    private List<User> members; // List of Users that are subscribed in this Channel

    private List<Message> messages; // List of messages that have been made in this Channel

    private boolean open = true;

    public Channel() {
        super();
    }

    public Channel(List<MessageBoard> boards, List<User> members, boolean open) {
        this.boards = boards;
        this.members = members;
        this.open = open;
    }

    public Channel(int id, List<MessageBoard> boards, List<User> members, List<Message> messages, boolean open) {
        this.id = id;
        this.boards = boards;
        this.members = members;
        this.messages = messages;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MessageBoard> getBoards() {
        return boards;
    }

    public void setBoards(List<MessageBoard> boards) {
        this.boards = boards;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((boards == null) ? 0 : boards.hashCode());
        result = prime * result + id;
        result = prime * result + ((members == null) ? 0 : members.hashCode());
        result = prime * result + ((messages == null) ? 0 : messages.hashCode());
        result = prime * result + (open ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Channel other = (Channel) obj;
        if (boards == null) {
            if (other.boards != null)
                return false;
        } else if (!boards.equals(other.boards))
            return false;
        if (id != other.id)
            return false;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        if (messages == null) {
            if (other.messages != null)
                return false;
        } else if (!messages.equals(other.messages))
            return false;
        if (open != other.open)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Channel [id=" + id + ", members=" + members + ", open=" + open + "]";
    }

}