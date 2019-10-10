package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Channel
 */
@Entity
@Table(name = "CHANNELS")
@SequenceGenerator(name="channel_gen_id", allocationSize = 1, sequenceName = "channel_seq_id")
public class Channel {

    @Id
    @Column(name = "channel_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id; // Identification number that is unique

    @Column
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "CHANNEL_USERS", joinColumns = @JoinColumn(referencedColumnName = "channel_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "user_id"))
    private List<User> members; // List of Users that are subscribed in this Channel

    @Column
    @OneToMany
    @JoinColumn(name = "message_id")
    private List<Message> messages; // List of messages that have been made in this Channel

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "USER_ROLES",
        joinColumns = @JoinColumn(referencedColumnName = "role_id"),
        inverseJoinColumns = @JoinColumn(referencedColumnName = "user_id")
    )
    // private List<Role> assignedRoles;

    @Column
    private boolean open = true; // Boolean to check if the channel is public or private

    public Channel() {
        super();
    }

    public Channel(List<User> members, boolean open) {
        this.members = members;
        this.open = open;
    }

    public Channel(int id, List<User> members, List<Message> messages, boolean open) {
        this.id = id;
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