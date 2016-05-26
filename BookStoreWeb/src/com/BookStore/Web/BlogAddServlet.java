package com.BookStore.Web;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BlogAddServlet
 */
@WebServlet("/BlogAddServlet")
public class BlogAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		sendMessage(title, content, request.getUserPrincipal().getName());
		response.sendRedirect("blogsPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void sendMessage(String title, String content, String author) {
		String destinationName = "queue/helloQueue";
        Context ic;
        ConnectionFactory cf;
        Connection connection = null;
 
        try {
            ic = new InitialContext();
 
            cf = (ConnectionFactory)ic.lookup("/ConnectionFactory");
            Queue queue = (Queue)ic.lookup(destinationName);
 
            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);
 
            connection.start();
 
            MapMessage message = session.createMapMessage();
            message.setString("title", title);
            message.setString("content", content);
            message.setString("author", author);
            publisher.send(message);
 
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection !=null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        

	}
}
