package com.shangcai.dao.cache;

import com.shangcai.entity.cache.Session;

public interface ISessionDao {

	public Session get(String token);
	
	public void add(Session session);
	
	public void upd(Session session);
}
