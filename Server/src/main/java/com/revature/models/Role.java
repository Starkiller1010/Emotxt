 package com.revature.models;

/**
  * Role
  *  Describes the roles a user can have within channels.
  */
 
 public enum Role {
	 
	 OWNER("OWNER"), ADMIN("ADMIN"), USER("USER"), BANNED("BANNED"), INVITED("INVITED");
	 
	 private int id;
	 private String roleName;
	 
	 /**
	  * 
	  * @param roleName - The name of the role. It should be the same as the enumerated
	  * value with proper casing.
	  */
	 
	 private Role(String roleName) {
		 this.id = this.ordinal();
		 this.roleName = roleName.toUpperCase();
	 }
	 
	 /**
	  * Returns a string with the value of the role's name.
	  * 
	  */
	 
	 public String toString() {
		 return this.roleName;
	 }
	 
	 /**
	  * Returns the id of the current role (i.e. its enumerated position).
	  */
	 
	 public int getId() {
		 return this.id;
	 }
	 
 }