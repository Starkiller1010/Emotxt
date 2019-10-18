package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Account
 */
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "ACCOUNTS")
@SequenceGenerator(name="account_gen_id", allocationSize = 1, sequenceName = "account_seq_id")
public class Account {

    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen_id")
    private int id;

    @Column
    private String country;

    @Column
    private String state;
    
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column
    private String bio;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_user", referencedColumnName = "user_id")
    private User user;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    	name = "friends_list",
        joinColumns = {@JoinColumn(name="me", referencedColumnName = "account_id")},
        inverseJoinColumns= {@JoinColumn(name="them", referencedColumnName = "account_id")}
        )
    private List<Account> friends;
 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "SUBSCRIPTIONS",
        joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
        inverseJoinColumns =  @JoinColumn(name = "channel_id", referencedColumnName = "channel_id")
    )
    private List<Channel> subscriptions;

    public Account() {
        super();
    }

    public Account(String country, String state, String bio) {
        this.country = country;
        this.state = state;
        this.bio = bio;
    }

    public Account(int id, String country, String state, Status status, String bio) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.bio = bio;
    }

	public Account(int id, String country, String state, String bio, List<Account> friendsList,
			List<Channel> subscriptions) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.bio = bio;
		this.friends = friendsList;
		this.subscriptions = subscriptions;
	}

	public Account(String country, String state, String bio, List<Account> friendsList, List<Channel> subscriptions) {
		super();
		this.country = country;
		this.state = state;
		this.bio = bio;
		this.friends = friendsList;
		this.subscriptions = subscriptions;
	}

	public Account(String bio, List<Account> friendsList, List<Channel> subscriptions) {
		super();
		this.bio = bio;
		this.friends = friendsList;
		this.subscriptions = subscriptions;
	}

	public Account(List<Account> friendsList, List<Channel> subscriptions) {
		super();
		this.friends = friendsList;
		this.subscriptions = subscriptions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Account> getFriends() {
		return friends;
	}

	public void setFriends(List<Account> friendsList) {
		this.friends = friendsList;
	}

	public List<Channel> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Channel> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
  
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result + id;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((subscriptions == null) ? 0 : subscriptions.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Account other = (Account) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} else if (!friends.equals(other.friends))
			return false;
		if (id != other.id)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (subscriptions == null) {
			if (other.subscriptions != null)
				return false;
		} else if (!subscriptions.equals(other.subscriptions))
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", country=" + country + ", state=" + state + ", status=" + status + ", bio=" + bio + ", friends="
				+ friends + ", subscriptions=" + subscriptions + "]";
	}
}