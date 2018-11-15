package com.shangcai.dao.cache.impl;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.cache.ISessionDao;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.cache.Session.T;

@Repository
public class SessionDao extends BaseDao<Session> implements ISessionDao{

	@Override
	public Session get(String token) {
		return selectFrom(Session.class).where(T.TOKEN.eq(token)).query();
	}

}
