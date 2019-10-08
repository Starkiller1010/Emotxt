package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(table = "ACCOUNTS", referencedColumnName = "account_id")
    private Account account;

    @Column
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(table = "MESSAGE_BOARDS", referencedColumnName = "board_id")
    private MessageBoard uBoard;

    public User() {
        super();
    }

    public User(String username, String password, String email, Account account) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.account = account;
    }

    public User(int id, String username, String password, String email, Account account, MessageBoard uBoard) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.account = account;
        this.uBoard = uBoard;
    }

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MessageBoard getuBoard() {
        return uBoard;
    }

    public void setuBoard(MessageBoard uBoard) {
        this.uBoard = uBoard;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((uBoard == null) ? 0 : uBoard.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id != other.id)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (uBoard == null) {
            if (other.uBoard != null)
                return false;
        } else if (!uBoard.equals(other.uBoard))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [account=" + account + ", email=" + email + ", id=" + id + ", password=" + password + ", username="
                + username + "]";
    }

    
}