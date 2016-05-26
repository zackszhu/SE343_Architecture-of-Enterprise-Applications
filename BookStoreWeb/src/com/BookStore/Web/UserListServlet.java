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

import com.bookstore.ejb.UserOperBeanRemote;
import com.bookstore.model.Users;
import com.bookstore.security.MyPermission;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserOperBeanRemote userOperBeanRemote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONArray users = new JSONArray();
		List<Users> userList = userOperBeanRemote.GetAllUsers();
		for (Users user : userList) {
			JSONObject userJSON = new JSONObject();
			userJSON.put("ID", user.getUserID());
			userJSON.put("Name", user.getUsername());
			userJSON.put("Email", user.getEmail());
			userJSON.put("CreateTime", user.getCreateTime());
			userJSON.put("Role", user.getRole());
			users.put(userJSON);
		}
//		for (JSONObject j : bookJSONs) {
//			books.put(j);
//		}
		json.put("users", users);
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
