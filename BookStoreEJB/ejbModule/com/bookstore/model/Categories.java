package com.bookstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Categories")
public class Categories implements Serializable{
	private static final long serialVersionUID = 1L;
	public Categories() {
		super();
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	@Id
	private int CategoryID;
	private String Name;
	private String Permission;
}
