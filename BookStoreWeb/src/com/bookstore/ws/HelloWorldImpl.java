package com.bookstore.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.bookstore.ejb.BookListBeanRemote;

@WebService
public class HelloWorldImpl{
	
	@EJB
	private BookListBeanRemote bookListBeanRemote;

    public void Hello() {
    }

    @WebMethod
    public String getDescription(@WebParam(name="id") int id) {
    	String description = bookListBeanRemote.getDescription(id);
        return description;
    }

}
