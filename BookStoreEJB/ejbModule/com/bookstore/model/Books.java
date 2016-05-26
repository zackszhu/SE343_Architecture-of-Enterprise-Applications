package com.bookstore.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Books")
public class Books implements Serializable{
	private static final long serialVersionUID = 1L;
	public Books() {
        super();
    }
	
	public Books(String name, String author, String publisher, String isbn, double price, String description) {
		this.Name = name;
		this.Author = author;
		this.Publisher = publisher;
		this.ISBN = isbn;
		this.Price = price;
		this.Status = 0;
		this.Description = description;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getCategory() {
		return Category;
	}

	public void setCategory(int category) {
		Category = category;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}

	@Id
	private int id;
	private String Name;
	private String Author;
	private String Publisher;
	private String ISBN;
	private double Price;
	private int Status;
	private int Category;
	private String Description;
}
