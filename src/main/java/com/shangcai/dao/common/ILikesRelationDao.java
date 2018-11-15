package com.shangcai.dao.common;

import com.shangcai.entity.common.LikesRelation;

public interface ILikesRelationDao {
	
	public LikesRelation findByMemberWorks(Integer memberId, Integer worksId);
	
	public void add(LikesRelation likesRelation);
	
	public int deleteByMemberWorks(Integer memberId, Integer worksId);

}
