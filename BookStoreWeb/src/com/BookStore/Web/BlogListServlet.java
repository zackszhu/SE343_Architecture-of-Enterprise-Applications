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

import com.bookstore.ejb.PostListBeanRemote;
import com.bookstore.model.Posts;

/**
 * Servlet implementation class BlogListServlet
 */
@WebServlet("/BlogListServlet")
public class BlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PostListBeanRemote postListBeanRemote;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BlogList");
		JSONObject json = new JSONObject();
		JSONArray posts = new JSONArray();
		List<Posts> postList = postListBeanRemote.getPosts(request.getUserPrincipal().getName());
		for (Posts post : postList) {
			JSONObject postJSON = new JSONObject();
			postJSON.put("Title", post.getTitle());
			postJSON.put("Content", post.getContent());
			postJSON.put("Time", post.getTime());
			posts.put(postJSON);		
		}
		json.put("blogs", posts);
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
