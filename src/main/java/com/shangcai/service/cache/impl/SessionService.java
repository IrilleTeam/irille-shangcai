package com.shangcai.service.cache.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangcai.dao.cache.ISessionDao;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.Member;
import com.shangcai.service.cache.ISessionService;

import irille.pub.DateTools;

@Service
public class SessionService implements ISessionService {
	
	@Autowired
	private ISessionDao sessionDao;

	@Override
	public Session get(String token) {
		return sessionDao.get(token);
	}

	@Override
	public Session add(Member member) {
		Session session = new Session();
		session.setAccessdTime(new Date());
		session.setMember(member.getPkey());
		session.setTimeout(30);
		session.setToken(DateTools.getDigest(member.getPkey()+member.getWxOpenId()+System.currentTimeMillis()));
		sessionDao.add(session);
		return session;
	}

	@Override
	public void upd(Session session) {
		sessionDao.upd(session);
	}

}
