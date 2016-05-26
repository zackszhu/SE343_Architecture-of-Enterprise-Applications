package com.bookstore.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: Test
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "queue/helloQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "queue/helloQueue")
public class PostMessageBean implements MessageListener {

    /**
     * Default constructor. 
     */
	
	@EJB
	private PostListBeanRemote postListBeanRemote;
	
    public PostMessageBean() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	try {
			String title = ((MapMessage)message).getString("title");
			String content = ((MapMessage)message).getString("content");
			String author = ((MapMessage)message).getString("author");
			postListBeanRemote.addPost(title, content, author);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Hello");
    }

}
