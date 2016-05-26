package com.BookStore.Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bookstore.ejb.BookListBeanRemote;
import com.bookstore.model.Books;
import com.bookstore.security.MyPermission;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SecurityManager sm;
	
	@EJB
	private BookListBeanRemote bookListBeanRemote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub
        sm = System.getSecurityManager();
    }
    
    private boolean canAccess(Books book) {
    	if (sm != null) {
    		try {
				sm.checkPermission(new MyPermission(Integer.toString(book.getCategory())));
				return true;
			} catch (SecurityException e) {
				return false;
			}
    	}
    	else {
    		return true;
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BookList");
		JSONObject json = new JSONObject();
		JSONArray books = new JSONArray();
		List<Books> bookList;
		if (request.getParameter("Place") == null) {
			bookList = bookListBeanRemote.getAllBooks();
		}
		else {
			bookList = bookListBeanRemote.getBooks(request.getUserPrincipal().getName());
		}
		for (Books book : bookList) {
			if (canAccess(book)) {
				JSONObject bookJSON = new JSONObject();
				bookJSON.put("ID", book.getID());
				bookJSON.put("Name", book.getName());
				bookJSON.put("Author", book.getAuthor());
				bookJSON.put("Publisher", book.getPublisher());
				bookJSON.put("ISBN", book.getISBN());
				bookJSON.put("Price", book.getPrice());
				books.put(bookJSON);
			}
			
		}
//		for (JSONObject j : bookJSONs) {
//			books.put(j);
//		}
		json.put("books", books);
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
