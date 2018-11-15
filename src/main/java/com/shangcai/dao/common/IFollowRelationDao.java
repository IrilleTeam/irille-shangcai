package com.shangcai.dao.common;

import com.shangcai.entity.common.FollowRelation;

public interface IFollowRelationDao {

	public void add(FollowRelation followRelation);
	
	public FollowRelation findByMemberFollower(Integer memberId, Integer followerId);
	
	public int del(Integer pkey);
	
}
