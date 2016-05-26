package com.BookStore.Web;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/helloName")
public class HelloName {

    @OnMessage
    public void sayHello(Session session, String text) {
        System.out.println("Say hello to '" + text + "'");
        for (Session sess : session.getOpenSessions()) {
        	if (sess.isOpen())
				try {
					sess.getBasicRemote().sendText(text);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }   
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }
    
    @OnClose
    public void helloOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }
}