package com.bookstore.ejb;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Order;

import com.bookstore.model.Orders;

/**
 * Session Bean implementation class OrderListBean
 */
@Stateless
@LocalBean
@TransactionAttribute(value=REQUIRED)
public class OrderListBean implements OrderListBeanRemote {

	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public OrderListBean() {
        // TODO Auto-generated constructor stub
    }

	public List<Orders> getRecentOrders() {
		System.out.println("ordersList");
		List<Orders> orders;
		try {
			orders = (List<Orders>) entityManager.createQuery("SELECT o FROM Orders o")
					.getResultList();
		} catch (NoResultException e) {
			orders = null;
		}
		return orders;
	}

}
