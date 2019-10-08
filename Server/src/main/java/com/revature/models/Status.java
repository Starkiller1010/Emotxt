package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Status
 */
@Entity
@Table(name = "USER_STATUS")
@SequenceGenerator(name="status_gen_id", allocationSize = 1, sequenceName = "status_seq_id")
public class Status {

    @Id
    @Column(name = "STATUS_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "STATUS_TYPE")
    private String status_type;

    public Status() {
        super();
    }

    public Status(String status_type) {
        this.status_type = status_type;
    }

    public Status(int id, String status_type) {
        this.id = id;
        this.status_type = status_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((status_type == null) ? 0 : status_type.hashCode());
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
        Status other = (Status) obj;
        if (id != other.id)
            return false;
        if (status_type == null) {
            if (other.status_type != null)
                return false;
        } else if (!status_type.equals(other.status_type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Status [id=" + id + ", status_type=" + status_type + "]";
    }

}