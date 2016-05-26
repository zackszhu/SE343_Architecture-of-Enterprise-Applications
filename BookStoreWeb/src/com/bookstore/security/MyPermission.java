package com.bookstore.security;

import java.security.BasicPermission;

public class MyPermission extends BasicPermission {

	public MyPermission(String name) {
		super(name);
	}
	
	public MyPermission(String name, String action) {
		super(name, action);
	}

}
