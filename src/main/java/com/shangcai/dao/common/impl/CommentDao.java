package com.shangcai.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.ICommentDao;
import com.shangcai.entity.common.Comment;
import com.shangcai.entity.common.Comment.T;

@Repository
public class CommentDao extends BaseDao<Comment> implements ICommentDao {

	@Override
	public void add(Comment comment) {
		comment.ins();
	}

	@Override
	public List<Comment> list(Integer worksId, Integer start, Integer limit) {
		return selectFrom(Comment.class).where(T.WORKS.eq(worksId)).limit(start, limit).queryList();
	}

}
