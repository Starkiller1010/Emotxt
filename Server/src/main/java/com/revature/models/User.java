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
import javax.persistence.ManyToMany;
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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "account_id")
    private Account account;

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    private List<Channel> channels;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Message> userMessages;

    private List<User> friendList;

       
}