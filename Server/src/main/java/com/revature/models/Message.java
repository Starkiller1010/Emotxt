package com.revature.models;

import java.sql.Timestamp;

/**
 * Message
 */
public class Message {

    private int id;

    private String body;

    private int AuthorId;

    private Timestamp createdStamp;

    private int Tone;

    public Message() {
        super();
    }

    public Message(String body, int authorId) {
        this.body = body;
        AuthorId = authorId;
        this.createdStamp = new Timestamp(System.currentTimeMillis());
    }

    public Message(int id, String body, int authorId, Timestamp createdStamp, int tone) {
        this.id = id;
        this.body = body;
        AuthorId = authorId;
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

    public int getAuthorId() {
        return AuthorId;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
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
        result = prime * result + AuthorId;
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
        if (AuthorId != other.AuthorId)
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
		return "Message [AuthorId=" + AuthorId + ", Tone=" + Tone + ", body=" + body + ", createdStamp=" + createdStamp
				+ ", id=" + id + "]";
	}

    
}