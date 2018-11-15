package com.shangcai.dao.material.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.material.IDesignDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.material.Design;
import com.shangcai.entity.material.Design.T;
import com.shangcai.view.common.WorksView;

@Repository
public class DesignDao extends BaseDao<Design> implements IDesignDao {

	/**
	 * 获取企业作品列表
	 * 
	 * @param status
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	@Override
	public List<WorksView> list(Integer pkey, Integer status, Integer start, Integer limit, String name) {
		if (status == null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status)).limit(start, limit).queryList(WorksView.class);
		if (name != null && status == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.MEMBER_NAME.like("%"+name+"%"))
							.limit(start, limit).queryList(WorksView.class);
		if (pkey != null && status == null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		if (status != null && name != null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.MEMBER_NAME.like("%"+name+"%")).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status != null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.STATUS.eq(status)).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status == null && name != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.MEMBER_NAME.like("%"+name+"%")).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name != null && pkey != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
							.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.MEMBER_NAME.like("%"+name+"%"), Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
				Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
				Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
				Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus"),Design.T.SAMPLING_PRICE.as("samplingPrice")).from(Design.class)
						.leftJoin(Works.class, Works.T.PKEY, Design.T.WORKS)
						.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
						.queryList(WorksView.class);
	}

	@Override
	public Design findByWorksId(Integer worksId) {
		return selectFrom(Design.class).where(T.WORKS.eq(worksId)).query();
	}

	@Override
	public List<Design> getDesignList() {
		return selectFrom(Design.class).queryList();
	}

}
