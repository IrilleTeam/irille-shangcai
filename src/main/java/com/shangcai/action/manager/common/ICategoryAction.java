package com.shangcai.action.manager.common;

public interface ICategoryAction {

	/**
	 * 获取企业的作品分类列表
	 */
	public void list() throws Exception;

	/**
	 * 添加企业作品分类
	 */
	public void add() throws Exception;

	/**
	 * 更新鞋企业作品分类
	 */
	public void upd() throws Exception;

	/**
	 * 删除鞋企业作品分类
	 */
	public void del() throws Exception;
}
