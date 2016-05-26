package com.BookStore.Web;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bookstore.ejb.ShoppingCartBeanRemote;
import com.bookstore.model.Books;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShoppingCartBeanRemote cartBean = (ShoppingCartBeanRemote) request.getSession().getAttribute("shoppingCart");
		if (cartBean == null) {
			try {
				InitialContext ic = new InitialContext();
				cartBean = (ShoppingCartBeanRemote)ic.lookup("java:global/BookStoreEJB/ShoppingCartBean!com.bookstore.ejb.ShoppingCartBeanRemote");
				cartBean.init(request.getUserPrincipal().getName());
				request.getSession().setAttribute("shoppingCart", cartBean);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Books> bookList = cartBean.getBooks();
		JSONObject json = new JSONObject();
		JSONArray books = new JSONArray();
		for (Books book : bookList) {
			JSONObject j = new JSONObject();
			j.put("ID", book.getID());
			j.put("Name", book.getName());
			j.put("Price", book.getPrice());
			books.put(j);
		}
		json.put("books", books);
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
//		response.getWriter().write(cartBean.getID());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
