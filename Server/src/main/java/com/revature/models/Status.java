package com.revature.models;

/**
 * Status
 */

public enum Status {

	 ONLINE("ONLINE"), OFFLINE("OFFLINE"), AWAY("AWAY"), BUSY("BUSY"), INVISIBLE("INVISIBLE");
	 
	 private int id;
	 private String statusName;
	 
	 /**
	  * 
	  * @param statusName - The name of the status. It should be the same as the enumerated
	  * value with proper casing.
	  */
	 
	 private Status(String statusName) {
		 this.id = this.ordinal();
		 this.statusName = statusName.toUpperCase();
	 }
	 
	 /**
	  * Returns a string with the value of the status' name.
	  * 
	  */
	 
	 public String toString() {
		 return this.statusName;
	 }
	 
	 /**
	  * Returns the id of the current status (i.e. its enumerated position).
	  */
	 
	 public int getId() {
		 return this.id;
	 }

}