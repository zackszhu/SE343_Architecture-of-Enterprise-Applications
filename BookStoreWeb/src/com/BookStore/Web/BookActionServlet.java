package com.BookStore.Web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.ejb.BookListBeanRemote;
import com.bookstore.ejb.ShoppingCartBeanRemote;

/**
 * Servlet implementation class bookActionServlet
 */
@WebServlet("/BookActionServlet")
public class BookActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookListBeanRemote bookListBeanRemote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		switch(action) {
		case "buy":
			String[] books = request.getParameterValues("bookID");
            ShoppingCartBeanRemote cartBean = (ShoppingCartBeanRemote) request.getSession().getAttribute("shoppingCart");
            cartBean.buyBooks(books);
            request.getSession().setAttribute("shoppingCart", cartBean);
            response.sendRedirect("welcome?position=myBooks");
            break;
		case "add":
			String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String isbn = request.getParameter("isbn");
            double price = Double.parseDouble((String) request.getParameter("price"));
            String description = request.getParameter("description");
            // action
            System.out.println(description);
            bookListBeanRemote.addBooks(bookName, author, publisher, isbn, price, description);
            response.sendRedirect("welcome?position=store");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	

}
