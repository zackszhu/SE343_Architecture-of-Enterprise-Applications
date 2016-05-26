package com.BookStore.Web;

import java.io.IOException;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.login.LoginContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.security.MyCallbackHandler;

/**
 * Servlet implementation class TestSecurity
 */
@WebServlet("/TestSecurity")
public class TestSecurity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSecurity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name") != null ? request.getParameter("name") : "michael";
        sendMessage(name);
        response.getWriter().println(String.format("check your console (%s)", name));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	void sendMessage(String name) {
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
 
            TextMessage message = session.createTextMessage(name);
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
