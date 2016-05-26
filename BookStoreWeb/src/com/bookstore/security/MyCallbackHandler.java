package com.bookstore.security;

import java.io.IOException;

import javax.security.auth.callback.*;


public class MyCallbackHandler  implements CallbackHandler{
	private String username;
    private String password;


    public MyCallbackHandler (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
    {
        System.out.println("handle");
        for (Callback callback : callbacks)
        {
            if (callback instanceof NameCallback)
            {
                ((NameCallback) callback).setName(username);
            }
            else if (callback instanceof PasswordCallback)
            {
                ((PasswordCallback) callback).setPassword(password.toCharArray());
            }
        }
        System.out.println("handle over");
    }
}
