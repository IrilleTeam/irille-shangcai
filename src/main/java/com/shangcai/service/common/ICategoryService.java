package com.shangcai.service.common;

import java.util.List;

import com.shangcai.view.common.CategoryView;

public interface ICategoryService {

	public List<CategoryView> list(Byte type);
	
	
	/**
	 * 获取企业的作品分类列表
	 */
	public List<CategoryView> allList(Byte type) throws Exception;

	/**
	 * 添加企业作品分类
	 */
	public void ins( String name, Integer up, Byte type, Integer sort ) throws Exception;

	/**
	 * 更新鞋企业作品分类
	 */
	public void upd(Integer pkey, String name, Integer up, Byte type, Integer sort) throws Exception;

	/**
	 * 删除鞋企业作品分类
	 */
	public void del(Integer pkey) throws Exception;
}
