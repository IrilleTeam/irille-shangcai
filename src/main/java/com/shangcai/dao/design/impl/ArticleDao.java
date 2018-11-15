package com.shangcai.dao.design.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.design.IArticleDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.design.Article;
import com.shangcai.entity.design.Article.T;
import com.shangcai.view.common.WorksView;

@Repository
public class ArticleDao extends BaseDao<Article> implements IArticleDao {

	/**
	 * 获取设计师作品信息
	 * 
	 * @return
	 */
	@Override
	public List<WorksView> list(Integer pkey, Integer status, Integer start, Integer limit, String name) {
		if (status == null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status)).limit(start, limit).queryList(WorksView.class);
		if (name != null && status == null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.MEMBER_NAME.like("%"+name+"%"))
							.limit(start, limit).queryList(WorksView.class);
		if (pkey != null && status == null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).where(Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		if (status != null && name != null && pkey == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.MEMBER_NAME.like("%"+name+"%")).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status != null && name == null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.STATUS.eq(status)).limit(start, limit)
							.queryList(WorksView.class);
		if (pkey != null && status == null && name != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.MEMBER.eq(pkey), Works.T.MEMBER_NAME.like("%"+name+"%")).limit(start, limit)
							.queryList(WorksView.class);
		if (status != null && name != null && pkey != null)
			return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
					Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
					Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
					Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
							.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
							.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY)
							.where(Works.T.STATUS.eq(status), Works.T.MEMBER_NAME.like("%"+name+"%"), Works.T.MEMBER.eq(pkey))
							.limit(start, limit).queryList(WorksView.class);
		return select(Works.T.PKEY.as("pkey"),Works.T.MEMBER_NAME.as("publisher"), Works.T.TITLE.as("worksName"),
				Category.T.NAME.as("category"), Works.T.CREATED_TIME.as("createdTime"),
				Works.T.LIKES_COUNT.as("likeRelation"), Works.T.PV_COUNT.as("browsingVolume"),
				Works.T.STATUS.as("status"), Works.T.STATUS.as("worksStatus")).from(Article.class)
						.leftJoin(Works.class, Works.T.PKEY, Article.T.WORKS)
						.leftJoin(Category.class, Category.T.PKEY, Works.T.CATEGROY).limit(start, limit)
						.queryList(WorksView.class);
	}

	@Override
	public Article findByWorksId(Integer worksId) {
		return selectFrom(Article.class).where(T.WORKS.eq(worksId)).query();
	}

	@Override
	public List<Article> getArticleList() {
		return selectFrom(Article.class).queryList();
	}
}
