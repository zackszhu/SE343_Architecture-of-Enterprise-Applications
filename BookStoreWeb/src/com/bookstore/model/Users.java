package com.bookstore.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Users")
public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	public Users() {
        super();
    }
	
	public Users(String username, String password, String email) {
		this.Username = username;
		this.Password = password;
		this.Email = email;
		this.Role = "USER";
		this.CreateTime = new Date(java.util.Calendar.getInstance().getTime().getTime());
	}
	
	public int getUserID() {
		return UserID;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	@Id
	private int UserID;
	private String Username;
	private String Password;
	private String Email;
	private Date CreateTime;
	private String Role;
}
