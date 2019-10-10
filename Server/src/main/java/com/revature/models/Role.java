// package com.revature.models;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.SequenceGenerator;
// import javax.persistence.Table;

// import org.springframework.beans.factory.annotation.Value;

// /**
//  * Role
//  */
// @Entity
// @Table(name = "ROLES")
// @SequenceGenerator(name="role_gen_id", allocationSize = 1, sequenceName = "role_seq_id")
// public class Role {

//     @Id
//     @Column(name = "ROLE_ID")
//     @GeneratedValue(strategy = GenerationType.SEQUENCE)
//     private int id;

//     @Value("User")
//     @Column(name = "ROLE_TYPE")
//     private String role_type;

//     public Role() {
//         super();
//     }

//     public Role(String role_type) {
//         this.role_type = role_type;
//     }

//     public Role(int id, String role_type) {
//         this.id = id;
//         this.role_type = role_type;
//     }

//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getRole_type() {
//         return role_type;
//     }

//     public void setRole_type(String role_type) {
//         this.role_type = role_type;
//     }

//     @Override
//     public int hashCode() {
//         final int prime = 31;
//         int result = 1;
//         result = prime * result + id;
//         result = prime * result + ((role_type == null) ? 0 : role_type.hashCode());
//         return result;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (obj == null)
//             return false;
//         if (getClass() != obj.getClass())
//             return false;
//         Role other = (Role) obj;
//         if (id != other.id)
//             return false;
//         if (role_type == null) {
//             if (other.role_type != null)
//                 return false;
//         } else if (!role_type.equals(other.role_type))
//             return false;
//         return true;
//     }

//     @Override
//     public String toString() {
//         return "Role [id=" + id + ", role_type=" + role_type + "]";
//     }
// }