package com.bookstore.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED; // this one is important!!!

import com.bookstore.model.Users;

/**
 * Session Bean implementation class UserOperBean
 */
@Stateless
@LocalBean
@TransactionAttribute(value=REQUIRED)
public class UserOperBean implements UserOperBeanRemote {
	
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public UserOperBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String Login(String username, String password) {
    	Users user;
    	try {
        	user = (Users) entityManager.createQuery("SELECT u FROM Users u WHERE u.Username=:username")
        			.setParameter("username", username)
        			.getSingleResult();    		
    	}
    	catch (NoResultException ex) {
    		user = null;
    	}
    	if (user == null) {
    		return "FAIL";
    	}
    	if (password.equals(user.getPassword())) {
    		return user.getRole();
    	}
    	return "FAIL";
    }

	public int Register(String username, String password, String email) {
		Users user = new Users(username, password, email);
//		entityManager.getTransaction().begin();
		System.out.println("Persist");
		entityManager.persist(user);
//		entityManager.getTransaction().commit();
		return user.getUserID();
	}

	public List<Users> GetAllUsers() {
		List<Users> users;
		try {
			users = (List<Users>) entityManager.createQuery("SELECT b FROM Users b")
        			.getResultList();   
		}
		catch (NoResultException ex) {
    		users = null;
    	}
		return users;
	}

	public void BanUser(int id) {
		Users user;
    	try {
        	user = (Users) entityManager.createQuery("SELECT u FROM Users u WHERE u.UserID=:id")
        			.setParameter("id", id)
        			.getSingleResult();    		
    	}
    	catch (NoResultException ex) {
    		user = null;
    	}
    	if (user == null) {
    		return;
    	}
    	else {
    		user.setRole("BANNED");
    		entityManager.persist(user);
    	}
	}

}
