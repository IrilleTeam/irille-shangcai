package com.shangcai.dao.common;

import com.shangcai.entity.common.Collection;

public interface ICollectionDao {

	public Collection findByMemberWorks(Integer memberId, Integer worksId);
	
	public void add(Collection collection);
	
	public int deleteByMemberWorks(Integer memberId, Integer worksId);
}
