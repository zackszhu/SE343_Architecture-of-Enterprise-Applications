package com.BookStore.Web;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.New;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.security.MyCallbackHandler;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] msgStrings = {"login", "username", "password", "submit", "signin", "error"};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.authenticate(response);
		Locale locale = getLocale(request);
		System.out.println(locale);
	    String date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale).format(new Date());
		
		if (request.getUserPrincipal() == null) {
			request.setAttribute("Time", date);
			ResourceBundle labels = ResourceBundle.getBundle("IndexLanguage", locale);
			for (String mString : msgStrings) {
				request.setAttribute(mString, labels.getString(mString));
			}
            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
        }
		else {
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
	
	private Locale getLocale(HttpServletRequest request) {
		Locale locale;
		if (request.getParameter("locale") == null) {
			locale = request.getLocale();
		}
		else if (request.getParameter("locale").equals("en")) {
			locale = new Locale("en", "GB");
		}
		else if (request.getParameter("locale").equals("zh")) {
			locale = new Locale("zh", "CN");
		}
		else {
			locale = request.getLocale();
		}
		return locale;
	}

}
