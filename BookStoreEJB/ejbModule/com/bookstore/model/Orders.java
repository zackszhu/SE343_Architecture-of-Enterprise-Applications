package com.bookstore.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Orders")
public class Orders implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Orders() {
		super();
	}
	
	public Orders(int bookID, int userID) {
		BookID = bookID;
		UserID = userID;
		CreateTime = new Date(java.util.Calendar.getInstance().getTime().getTime());
	}
	
	public int getBookID() {
		return BookID;
	}
	public void setBookID(int bookID) {
		BookID = bookID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	@Id
	private int OrderID;
	private int BookID;
	private int UserID;
	private Date CreateTime;
	private int Status;
}
