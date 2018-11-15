package com.shangcai.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.irille.core.repository.query.EntityQuery;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Collection;
import com.shangcai.entity.common.LikesRelation;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.common.Works.Status;
import com.shangcai.entity.common.Works.T;
import com.shangcai.view.common.WorksView;

@Repository
public class WorksDao extends BaseDao<Works> implements IWorksDao {

	/**
	 * 查询所有商品列表
	 */
	@Override
	public List<WorksView> list(Integer pkey, Integer start, Integer limit, String name, Integer status) {
		if (status == null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status)).limit(start, limit).queryList(WorksView.class);
		if (name != null && status == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.TITLE.eq(name))
							.limit(start, limit).queryList(WorksView.class);
		if (pkey != null && status == null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		if (status != null && name != null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.TITLE.eq(name)).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status != null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.STATUS.eq(status)).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status == null && name != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.TITLE.eq(name)).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name != null && pkey != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.TITLE.eq(name), Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
				Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
				Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
				Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Works.class)
						.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
						.queryList(WorksView.class);
	}

	/**
	 * 作品下线
	 */
	@Override
	public void unappr(Works works) {
		Works work= selectFrom(Works.class, works.getPkey());
		if(null==work)
			throw new WebMessageException(ReturnCode.failure,"作品不存在无法进行操作");
		work.stStatus(Status.failure);
		work.setReasonUnappr(works.getReasonUnappr());
		work.upd();
	}

	/**
	 * 作品上线
	 */
	@Override
	public void appr(Works works) {
		Works work = selectFrom(Works.class, works.getPkey());
		if(null==work)
			throw new WebMessageException(ReturnCode.failure,"作品不存在无法进行操作");
		work.stStatus(Status.confirm);
		work.upd();
	}

	@Override
	public List<Works> worksList() {
		return selectFrom(Works.class).queryList();
	}

	@Override
	public boolean exists(Integer worksId) {
		return selectFrom(Works.class).exists();
	}

	@Override
	public List<Works> list(Integer memberId, Integer collection, Integer likes, Byte status, Integer start, Integer limit) {
		EntityQuery<Works> query = selectFrom(Works.class);
		if (memberId != null)
			query.where(T.MEMBER.eq(memberId));
		if (collection != null)
			query.leftJoin(Collection.class, T.PKEY, Collection.T.WORKS).where(Collection.T.MEMBER.eq(collection));
		if (likes != null)
			query.leftJoin(LikesRelation.class, T.PKEY, LikesRelation.T.WORKS) .where(LikesRelation.T.MEMBER.eq(likes));
		if(status != null)
			query.where(T.STATUS.eq(status));
		return query.orderBy(T.CREATED_TIME.desc()).limit(start, limit).queryList();
	}

	@Override
	public Works get(Integer worksId) {
		return selectFrom(Works.class, worksId);
	}

	@Override
	public void add(Works work) {
		work.ins();
	}

	@Override
	public void incrementPv(Integer worksId) {
		String sql = "update "+Works.table.name()+" set "+Works.T.PV_COUNT+"="+Works.T.PV_COUNT+"+1 where "+Works.T.PKEY+"=?";
		sql(sql, worksId).execute();
	}
	
}
