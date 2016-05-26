package com.BookStore.Web;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.ejb.ShoppingCartBeanRemote;

/**
 * Servlet implementation class AddShoppingCartServlet
 */
@WebServlet("/AddShoppingCartServlet")
public class AddShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShoppingCartServlet() {
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
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		cartBean.addBook(bookID);
		request.getSession().setAttribute("shoppingCart", cartBean);
		response.sendRedirect("shoppingCartPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
