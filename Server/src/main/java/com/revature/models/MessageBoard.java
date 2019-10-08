package com.revature.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MessageBoard
 */
@Entity
@Table(name = "MESSAGE_BOARD")
public class MessageBoard {

    private int id;

    private int ownerId;

    private List<Channel> channels;

    public MessageBoard() {
        super();
    }

    public MessageBoard(int id, int ownerId, List<Channel> channels) {
        this.id = id;
        this.ownerId = ownerId;
        this.channels = channels;
    }

    public MessageBoard(int ownerId, List<Channel> channels) {
        this.ownerId = ownerId;
        this.channels = channels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((channels == null) ? 0 : channels.hashCode());
        result = prime * result + id;
        result = prime * result + ownerId;
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
        MessageBoard other = (MessageBoard) obj;
        if (channels == null) {
            if (other.channels != null)
                return false;
        } else if (!channels.equals(other.channels))
            return false;
        if (id != other.id)
            return false;
        if (ownerId != other.ownerId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MessageBoard [channels=" + channels + ", id=" + id + ", ownerId=" + ownerId + "]";
    }

    
}