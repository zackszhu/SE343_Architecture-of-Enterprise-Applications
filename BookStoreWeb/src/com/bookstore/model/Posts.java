package com.bookstore.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.text.AbstractDocument.Content;

@Entity(name = "Posts")
public class Posts implements Serializable {
	private static final long serialVersionUID = 1L;
	public Posts() {
		super();
	}
	
	public Posts(String title, String author, String content) {
		Title = title;
		Author = author;
		Content = content;
		Time = new Date(java.util.Calendar.getInstance().getTime().getTime());
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}

	@Id
	private int id;
	private String Title;
	private String Author;
	private Date Time;
	private String Content;
}
