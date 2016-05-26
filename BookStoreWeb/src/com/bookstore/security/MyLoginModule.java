package com.bookstore.security;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.bookstore.ejb.UserOperBean;
import com.bookstore.ejb.UserOperBeanRemote;

public class MyLoginModule implements LoginModule{
	private static final String GUEST = "guest";
	private Subject subject;
	private CallbackHandler callbackHandler;
	
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	
	private boolean loginSucceeded = false;
	private MyPrincipal userPrincipal;
	private MyGroup roleGroup;
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
	}
	@Override
	public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("Username");
        PasswordCallback passwordCallback = new PasswordCallback("Password", false);

        Callback[] callbacks = new Callback[]{nameCallback, passwordCallback};
        try
        {
            callbackHandler.handle(callbacks);
        } catch (IOException ioe)
        {
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce)
        {
            throw new LoginException(uce.toString());
        }

        String username = nameCallback.getName();

        if (!GUEST.equalsIgnoreCase(username))
        {
            String password = new String(passwordCallback.getPassword());
            passwordCallback.clearPassword();
            String role = GetRoleAndCheck(username, password);
            if (role != "FAIL")
            {
                userPrincipal = new MyPrincipal(username);
                roleGroup = new MyGroup("Roles");
                roleGroup.addMember(new MyPrincipal(role));
                
                loginSucceeded = true;

                return true;
            } else
            {
                loginSucceeded = false;
                return false;
            }
        } else
        {
            loginSucceeded = true;
            userPrincipal = new MyPrincipal(GUEST);
            roleGroup = new MyGroup("Roles");
            roleGroup.addMember(new MyPrincipal(GUEST));
            return true;
        }
	}
	private String GetRoleAndCheck(String username, String password) {
		Properties properties = new Properties();
		InitialContext context;
		try {
			context = new InitialContext();
			UserOperBeanRemote userOperBeanRemote = (UserOperBeanRemote) context.lookup("java:global/BookStoreWeb/UserOperBean!com.bookstore.ejb.UserOperBeanRemote");
			return userOperBeanRemote.Login(username, password);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL";
		}
		
	}
	@Override
	public boolean commit() throws LoginException {
		if (loginSucceeded == false)
        {
            return false;
        }
        // 认证了 subject 的身份了。
//        subject.getPrincipals().add(userPrincipal);
        subject.getPrincipals().add(roleGroup);
		return true;
	}
	@Override
	public boolean abort() throws LoginException {
		if (loginSucceeded == false)
        {
            userPrincipal = null;
            roleGroup = null;
            return false;
        } else
        {
            logout();
            return true;
        }
	}
	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
