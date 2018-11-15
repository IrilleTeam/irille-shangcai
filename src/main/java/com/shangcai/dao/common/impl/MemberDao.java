package com.shangcai.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Member.T;

@Repository
public class MemberDao extends BaseDao<Member> implements IMemberDao {

	public Member get(Integer pkey) {
		return selectFrom(Member.class, pkey);
	}

	public void upd(Member bean) {
		bean.upd();
	}

	@Override
	public List<Member> list() {

		return selectFrom(Member.class).queryList();
	}

	@Override
	public Member findByOpenId(String openid) {
		return selectFrom(Member.class).where(T.WX_OPEN_ID.eq(openid)).query();
	}

}
