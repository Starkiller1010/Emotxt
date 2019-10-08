package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Account
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    private int id;

    private String country;

    private String state;

    private int statusId;

    private String aboutMe;

    private List<User> Friends = new ArrayList<>();

    public Account() {
        super();
    }

    public Account(String country, String state, String aboutMe, List<User> friends) {
        this.country = country;
        this.state = state;
        this.statusId = 1;
        this.aboutMe = aboutMe;
        Friends = friends;
    }

    public Account(int id, String country, String state, int statusId, String aboutMe, List<User> friends) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.statusId = statusId;
        this.aboutMe = aboutMe;
        Friends = friends;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public List<User> getFriends() {
        return Friends;
    }

    public void setFriends(List<User> friends) {
        Friends = friends;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Friends == null) ? 0 : Friends.hashCode());
        result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + id;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + statusId;
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
        if (Friends == null) {
            if (other.Friends != null)
                return false;
        } else if (!Friends.equals(other.Friends))
            return false;
        if (aboutMe == null) {
            if (other.aboutMe != null)
                return false;
        } else if (!aboutMe.equals(other.aboutMe))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (id != other.id)
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (statusId != other.statusId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Account [Friends=" + Friends + ", aboutMe=" + aboutMe + ", country=" + country + ", id=" + id
                + ", state=" + state + ", statusId=" + statusId + "]";
    }
}