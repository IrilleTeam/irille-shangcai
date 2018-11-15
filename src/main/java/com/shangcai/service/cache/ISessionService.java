package com.shangcai.service.cache;

import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.Member;

public interface ISessionService {

	public Session get(String token);
	
	public Session add(Member member);
	
	public void upd(Session session);
}
