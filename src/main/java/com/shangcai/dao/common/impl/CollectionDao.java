package com.shangcai.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.ICollectionDao;
import com.shangcai.entity.common.Collection;
import com.shangcai.entity.common.Collection.T;

@Repository
public class CollectionDao extends BaseDao<Collection> implements ICollectionDao  {

	@Override
	public Collection findByMemberWorks(Integer memberId, Integer worksId) {
		return selectFrom(Collection.class).where(T.MEMBER.eq(memberId), T.WORKS.eq(worksId)).query();
	}

	@Override
	public int deleteByMemberWorks(Integer memberId, Integer worksId) {
		return delete(Collection.class).where(T.MEMBER.eq(memberId), T.WORKS.eq(worksId)).execute();
	}
}
