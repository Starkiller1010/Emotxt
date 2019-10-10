package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Message
 */
@Entity
@Table(name = "MESSAGES")
@SequenceGenerator(name="message_gen_id", allocationSize = 1, sequenceName = "message_seq_id")
public class Message {

    @Id
    @Column(name = "message_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private String body;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;

    @Column
    private Timestamp createdStamp;

    private int Tone;

    public Message() {
        super();
    }

    public Message(String body, User author) {
        this.body = body;
        this.author = author;
        this.createdStamp = new Timestamp(System.currentTimeMillis());
    }

    public Message(int id, String body, User author, Timestamp createdStamp, int tone) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.createdStamp = createdStamp;
        Tone = tone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getauthor() {
        return author;
    }

    public void setauthor(User author) {
        this.author = author;
    }

    public Timestamp getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Timestamp createdStamp) {
        this.createdStamp = createdStamp;
    }

    public int getTone() {
        return Tone;
    }

    public void setTone(int tone) {
        Tone = tone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + Tone;
        result = prime * result + ((body == null) ? 0 : body.hashCode());
        result = prime * result + ((createdStamp == null) ? 0 : createdStamp.hashCode());
        result = prime * result + id;
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
        Message other = (Message) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (Tone != other.Tone)
            return false;
        if (body == null) {
            if (other.body != null)
                return false;
        } else if (!body.equals(other.body))
            return false;
        if (createdStamp == null) {
            if (other.createdStamp != null)
                return false;
        } else if (!createdStamp.equals(other.createdStamp))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Message [author=" + author.getId() + ", Tone=" + Tone + ", body=" + body + ", createdStamp=" + createdStamp
                + ", id=" + id + "]";
    }


    
}