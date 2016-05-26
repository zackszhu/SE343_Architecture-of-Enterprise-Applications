package com.bookstore.ejb;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.bookstore.model.Posts;

/**
 * Session Bean implementation class PostListBean
 */
@Stateless
@LocalBean
@TransactionAttribute(value=REQUIRED)
public class PostListBean implements PostListBeanRemote {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
    public PostListBean() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Posts> getPosts(String author) {
    	System.out.println("PostList");
    	List<Posts> posts = new ArrayList<Posts>();
    	try {
			posts = (List<Posts>) entityManager.createQuery("SELECT p FROM Posts p WHERE p.Author=:author")
					.setParameter("author", author)
					.getResultList();
		} catch (NoResultException e) {
			// TODO: handle exception
			posts = null;
		}
    	return posts;
    }

	public void addPost(String title, String content, String author) {
		// TODO Auto-generated method stub
		Posts post = new Posts(title, author, content);
		entityManager.persist(post);
	}

}
