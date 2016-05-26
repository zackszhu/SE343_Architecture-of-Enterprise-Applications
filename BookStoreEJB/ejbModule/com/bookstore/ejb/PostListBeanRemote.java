package com.bookstore.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.bookstore.model.Posts;

@Remote
public interface PostListBeanRemote {
	public List<Posts> getPosts(String author);
	public void addPost(String title, String content, String author);
}
