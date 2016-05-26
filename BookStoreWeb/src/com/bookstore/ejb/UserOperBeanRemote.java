package com.bookstore.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.bookstore.model.Users;

@Remote
public interface UserOperBeanRemote {
	public String Login(String username, String password);
	public int Register(String username, String password, String email);
	public List<Users> GetAllUsers();
	public void BanUser(int id);
}
