package com.bookstore.restful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.FormParam;//获取表单参数
import javax.ws.rs.Produces;

import com.bookstore.ejb.BookListBeanRemote;
import com.bookstore.model.Books;
//import javax.ws.rs.core.Context;//请求上下文HttpServletRequest
//import javax.ws.rs.QueryParam;//用于获取数据库查询参数
@Path("api")
public class BookRestful {
	/**
	 * 初始化用户数据
	 */
	@EJB
	BookListBeanRemote bookListBeanRemote;
	
	@GET
	@Path("book/{id}")
	@Produces("application/json")
	public Books getById(@PathParam("id") Integer id) {
	    return bookListBeanRemote.getBook(id);
	}
	
	@GET
	@Path("books")
	@Produces("application/json")
	public List<Books> getUsers() {
		return bookListBeanRemote.getAllBooks();
	}
	
	@POST
	@Path("books")
	@Consumes("application/json")
	public void addBooks(Books books) {
		bookListBeanRemote.addBooks(books.getName(), books.getAuthor(), books.getPublisher(), books.getISBN(), books.getPrice(), books.getDescription());		
	}
}