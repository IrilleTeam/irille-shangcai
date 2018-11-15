package com.shangcai.action.customer.common;

import java.io.IOException;

public interface IWorksAction {

	/**
	 * 作品列表
	 * @throws IOException
	 */
	public void list() throws IOException;

	/**
	 * 作品详情
	 * @throws IOException
	 */
	public void get() throws IOException;
	
	/**
	 * 给作品点赞
	 * @throws IOException
	 */
	public void like() throws IOException;
	
	/**
	 * 取消作品点赞
	 * @throws IOException
	 */
	public void unlike() throws IOException;
	
	/**
	 * 收藏作品
	 * @throws IOException
	 */
	public void collect() throws IOException;
	
	/**
	 * 取消收藏
	 * @throws IOException
	 */
	public void uncollect() throws IOException;
}
