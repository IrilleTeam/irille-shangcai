package com.shangcai.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.ILikesRelationDao;
import com.shangcai.entity.common.LikesRelation;
import com.shangcai.entity.common.LikesRelation.T;

@Repository
public class LikesRelationDao extends BaseDao<LikesRelation> implements ILikesRelationDao {

	@Override
	public LikesRelation findByMemberWorks(Integer memberId, Integer worksId) {
		return selectFrom(LikesRelation.class).where(T.MEMBER.eq(memberId), T.WORKS.eq(worksId)).query();
	}

	@Override
	public int deleteByMemberWorks(Integer memberId, Integer worksId) {
		return delete(LikesRelation.class).where(T.MEMBER.eq(memberId), T.WORKS.eq(worksId)).execute();
	}

}
