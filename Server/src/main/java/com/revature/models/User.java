package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * User
 */
@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "user_gen_id", sequenceName = "user_seq_id", allocationSize = 1)
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    
//    @ManyToMany(mappedBy = "friendsList", fetch=FetchType.LAZY)
//    private List<Account> friendsList;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(referencedColumnName = "account_id")
//    private Account account;
    
//    @ManyToMany(mappedBy = "friendsList", fetch=FetchType.LAZY)
//    private List<Account> friends;

//    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
//    private List<Channel> channels;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Message> userMessages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Account> getFriends() {
//        return friends;
//    }
//
//    public void setFriendAccounts(List<Account> friends) {
//        this.friends = friends;
//    }

//    public List<Channel> getChannels() {
//        return channels;
//    }

//    public void setChannels(List<Channel> channels) {
//        this.channels = channels;
//    }

    public List<Message> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<Message> userMessages) {
        this.userMessages = userMessages;
    }

   
    
    
}