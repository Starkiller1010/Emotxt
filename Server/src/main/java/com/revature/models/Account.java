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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Account
 */
@Entity
@Table(name = "ACCOUNTS")
@SequenceGenerator(name="account_gen_id", allocationSize = 1, sequenceName = "account_seq_id")
public class Account {

    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String country;

    @Column
    private String state;
    
    @Column
    private String bio;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "FRIENDS",
        joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"),
        inverseJoinColumns =  @JoinColumn(name = "friend_id", referencedColumnName = "user_id")
    )
    private List<User> friendsList;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "CHANNELS",
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

	public Account(int id, String country, String state, String bio, List<User> friendsList,
			List<Channel> subscriptions) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.bio = bio;
		//this.friendsList = friendsList;
		//this.subscriptions = subscriptions;
	}

	public Account(String country, String state, String bio, List<User> friendsList, List<Channel> subscriptions) {
		super();
		this.country = country;
		this.state = state;
		this.bio = bio;
		//this.friendsList = friendsList;
		//this.subscriptions = subscriptions;
	}

	public Account(String bio, List<User> friendsList, List<Channel> subscriptions) {
		super();
		this.bio = bio;
		//this.friendsList = friendsList;
		//this.subscriptions = subscriptions;
	}

	public Account(List<User> friendsList, List<Channel> subscriptions) {
		super();
		//this.friendsList = friendsList;
		//this.subscriptions = subscriptions;
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

//	public List<User> getFriendsList() {
//		return friendsList;
//	}

//	public void setFriendsList(List<User> friendsList) {
//		this.friendsList = friendsList;
//	}

//	public List<Channel> getSubscriptions() {
//		return subscriptions;
//	}

//	public void setSubscriptions(List<Channel> subscriptions) {
//		this.subscriptions = subscriptions;
//	}



    
}