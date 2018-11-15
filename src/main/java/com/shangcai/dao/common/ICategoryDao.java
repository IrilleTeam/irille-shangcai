package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Category.Type;
import com.shangcai.view.common.CategoryView;

public interface ICategoryDao {
	
	public  List<Category> list();
	
	public Category get(Integer categoryId);
	
	/**
	 * 查询分类列表
	 * 
	 * @param type 分类类型 为空表示查询所有类型的分类
	 * @param top 是否为一级分类
	 * @return
	 * @author Jianhua Ying
	 */
	public List<Category> list(Type type, boolean isTop);
	/**
	 * 获取所有分类
	 * @return
	 */
	public  List<CategoryView> allList(Byte type);

	public void upd(Category category);
	
	/**
	 * 统计该分类下有几个下级分类
	 * @param categoryId
	 */
	public Integer countByUp(Integer categoryId);
	
	public int del(Integer categoryId);

	public void add(Category category);
}
