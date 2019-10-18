package com.revature.models;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyEnumerated;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_gen_id")
    private int id; 											// Identification number that is unique

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @ElementCollection
    @JoinTable(name = "CHANNEL_USERS", 
    			joinColumns = @JoinColumn(referencedColumnName = "channel_id"), 
    			inverseJoinColumns = @JoinColumn(referencedColumnName = "user_id"))
    @MapKeyEnumerated(value=EnumType.STRING)
    private Map <Role, User> members;

    @OneToMany(mappedBy = "destination")
    private List<Message> messages; // List of messages that have been made in this Channel
    
    @ManyToMany(mappedBy = "subscriptions", fetch=FetchType.LAZY)
    private List<Account> accounts;

    @Column
    private boolean open = true; // Boolean to check if the channel is public or private

    public Channel() {
        super();
    }
   
	public Channel(Map<Role, User> members, boolean open) {
	  this.members = members;
	  this.open = open;
	}
	
	public Channel(int id, Map<Role, User> members, List<Message> messages, boolean open) {
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
    
    public Map<Role, User> getMembers() {
        return members;
    }

    public void setMembers(Map<Role, User> members) {
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

    public void setOpen() {
        this.open = !this.open;
    }

	@Override
	public int hashCode() {
		return Objects.hash(accounts, id, members, messages, open);
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
		return Objects.equals(accounts, other.accounts) && id == other.id && Objects.equals(members, other.members)
				&& Objects.equals(messages, other.messages) && open == other.open;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", members=" + members + ", messages=" + messages + ", accounts=" + accounts
				+ ", open=" + open + "]";
	}

}