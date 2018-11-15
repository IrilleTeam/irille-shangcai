package com.shangcai.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.IFollowRelationDao;
import com.shangcai.entity.common.FollowRelation;
import com.shangcai.entity.common.FollowRelation.T;

@Repository
public class FollowRelationDao extends BaseDao<FollowRelation> implements IFollowRelationDao  {

	@Override
	public FollowRelation findByMemberFollower(Integer memberId, Integer followerId) {
		return selectFrom(FollowRelation.class).where(T.MEMBER.eq(memberId), T.FOLLOWER.eq(followerId)).query();
	}

}
