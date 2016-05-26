package com.bookstore.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.bookstore.model.Books;

@Remote
public interface BookListBeanRemote {
	public List<Books> getAllBooks();
	public List<Books> getBooks(String username);
	public void addBooks(String bookName, String author, String publisher, String isbn, double price, String description);
	public List<Books> getTopFive();
	public String getDescription(int id);
	public Books getBook(int id);
}
