package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column
    private String aboutMe;

    public Account() {
        super();
    }

    public Account(String country, String state, String aboutMe) {
        this.country = country;
        this.state = state;
        this.aboutMe = aboutMe;
    }

    public Account(int id, String country, String state, Status status, String aboutMe) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.status = status;
        this.aboutMe = aboutMe;
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

    public Status getstatus() {
        return status;
    }

    public void setstatus(Status status) {
        this.status = status;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public String toString() {
        return "Account [aboutMe=" + aboutMe + ", country=" + country + ", id=" + id
                + ", state=" + state + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + id;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
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
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    
}