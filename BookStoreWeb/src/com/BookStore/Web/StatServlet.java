package com.BookStore.Web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bookstore.ejb.BookListBeanRemote;
import com.bookstore.ejb.OrderListBeanRemote;
import com.bookstore.model.Books;
import com.bookstore.model.Orders;

/**
 * Servlet implementation class StatServlet
 */
@WebServlet("/StatServlet")
public class StatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private BookListBeanRemote bookListBeanRemote;
	
	@EJB
	private OrderListBeanRemote orderListBeanRemote;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONArray books = new JSONArray();
		List<Books> bookList;
		bookList = bookListBeanRemote.getTopFive();
		for (Books book : bookList) {
			JSONObject bookJSON = new JSONObject();
			bookJSON.put("ID", book.getID());
			bookJSON.put("Name", book.getName());
			bookJSON.put("Author", book.getAuthor());
			bookJSON.put("Publisher", book.getPublisher());
			bookJSON.put("ISBN", book.getISBN());
			bookJSON.put("Price", book.getPrice());
			books.put(bookJSON);
		}
		json.put("books", books);
		JSONArray orders = new JSONArray();
		List<Orders> orderList;
		orderList = orderListBeanRemote.getRecentOrders();
		for (Orders order : orderList) {
			JSONObject orderJSON = new JSONObject();
			orderJSON.put("UserID", order.getUserID());
			orderJSON.put("BookID", order.getBookID());
			orderJSON.put("CreateTime", order.getCreateTime());
			orders.put(orderJSON);
		}
		json.put("orders", orders);
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
