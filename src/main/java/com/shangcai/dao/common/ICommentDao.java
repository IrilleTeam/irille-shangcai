package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.Comment;

public interface ICommentDao {

	public void add(Comment comment);

	public List<Comment> list(Integer worksId, Integer start, Integer limit);

}
