package com.shangcai.service.design;

import com.irille.core.controller.PageView;
import com.shangcai.view.common.WorksView;

public interface IArticleService {

	/**
	 * 获取设计师作品列表
	 * 
	 * @param status
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	public PageView<WorksView> designerWorksList(Integer pkey, Integer status, Integer start, Integer limit, String name);

	/**
	 * 发布作品-文章
	 * 
	 * @param memberId 用户pkey
	 * @param category 文章作品所属分类
	 * @param title 文章标题
	 * @param description 文章描述
	 * @param pictures 文章图片
	 * @author Jianhua Ying
	 */
	public void publish(Integer memberId, Integer category, String title, String description, String pictures);

}
