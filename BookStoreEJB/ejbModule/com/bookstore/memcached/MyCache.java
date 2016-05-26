package com.bookstore.memcached;

import redis.clients.jedis.Jedis;

public class MyCache {
	protected static Jedis jedis = new Jedis("localhost");
	
	public static Jedis getInstance() {
		return jedis;
	}
	
	private MyCache() {}
}
