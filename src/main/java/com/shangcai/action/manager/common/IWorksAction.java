package com.shangcai.action.manager.common;

public interface IWorksAction {

	/**
	 * 获取所有作品列表
	 */
	public void list() throws Exception;

	public void unappr();

	public void appr();

}
