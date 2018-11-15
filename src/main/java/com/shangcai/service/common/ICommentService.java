package com.shangcai.service.common;

import java.util.List;

import com.shangcai.view.common.CommentView;

public interface ICommentService {

	public void add(Integer memberId, Integer worksId, String content, Integer replyTo);

	/**
	 * 获取指定作品的评论列表,根据时间降序排列
	 * 
	 * @param worksId 作品pkey
	 * @param start
	 * @param limit
	 * @return
	 * @author Jianhua Ying
	 */
	public List<CommentView> list(Integer worksId, Integer start, Integer limit);

}
