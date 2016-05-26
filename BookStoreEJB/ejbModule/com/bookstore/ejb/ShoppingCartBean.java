package com.bookstore.ejb;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.bookstore.model.Books;
import com.bookstore.model.Orders;
import com.bookstore.model.Users;

/**
 * Session Bean implementation class ShoppingCartBean
 */
@Stateful
@LocalBean
@TransactionAttribute(value=REQUIRED)
public class ShoppingCartBean implements ShoppingCartBeanRemote {

	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
	private int userID;
	private List<Books> books;
	
    public ShoppingCartBean() {
        // TODO Auto-generated constructor stub
    	books = new ArrayList<Books>();
    }
    
    public void init(String username) {
    	Users user;
    	try {
			user = (Users)entityManager.createQuery("SELECT u FROM Users u WHERE u.Username = :username")
					.setParameter("username", username)
					.getSingleResult(); 
			userID = user.getUserID();
		} catch (NoResultException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    
    public int getID() {
    	return userID;
    }

    public void addBook(int bookID) {
    	Books book;
    	try {
    		book = (Books) entityManager.createQuery("SELECT b FROM Books b WHERE b.id=:id")
        			.setParameter("id", bookID)
        			.getSingleResult();
    	}
    	catch (NoResultException ex) {
    		book = null;
    	}
    	if (book != null) {
    		books.add(book);
    	}
    }

	public List<Books> getBooks() {
		return books;
	}

	public void buyBooks(String[] books) {
		for (String s : books) {
            int bookID = Integer.parseInt(s);
            // delete from shopping cart
            Orders order = new Orders(bookID, userID);
            entityManager.persist(order);
        }
		
	}
}
