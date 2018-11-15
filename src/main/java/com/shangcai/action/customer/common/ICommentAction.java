package com.shangcai.action.customer.common;

import java.io.IOException;

public interface ICommentAction {

	/**
	 * 评论作品&回复评论
	 */
	public void add() throws IOException;

	/**
	 * 作品评论列表
	 */
	public void list() throws IOException;
}
