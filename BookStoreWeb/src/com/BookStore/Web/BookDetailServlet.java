package com.BookStore.Web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bookstore.ejb.BookListBeanRemote;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
	@EJB
	private BookListBeanRemote bookListBeanRemote;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt((String)request.getParameter("BookID"));
		System.out.println("Hello");
		String description = bookListBeanRemote.getDescription(id);
		JSONObject json = new JSONObject();
		json.put("detail", description);
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

}
