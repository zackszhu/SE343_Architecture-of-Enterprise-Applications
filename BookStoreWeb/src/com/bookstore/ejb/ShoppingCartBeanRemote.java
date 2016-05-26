package com.bookstore.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.bookstore.model.Books;

@Remote
public interface ShoppingCartBeanRemote {
	public void init(String username);
	public int getID();
	public void addBook(int bookID);
	public List<Books> getBooks();
	public void buyBooks(String[] books);
}
