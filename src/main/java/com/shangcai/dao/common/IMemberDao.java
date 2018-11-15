package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.Member;

public interface IMemberDao {
	
	public void add(Member member);

	public Member get(Integer pkey);

	public Member findByOpenId(String openid);
	
	public void upd(Member member);

	public List<Member> list();
}
