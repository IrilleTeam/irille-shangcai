package com.shangcai.service.material;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.irille.core.controller.PageView;
import com.shangcai.view.common.WorksView;
import com.shangcai.view.material.ThemeView;

public interface IDesignService {
	
	/**
	 * 获取企业作品列表
	 * @param status
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	public PageView<WorksView> companyWorksList(Integer pkey, Integer status, Integer start, Integer limit, String name);
	
	/**
	 * 发布作品-模板
	 * 
	 * @param category 模板作品所属分类
	 * @param title 标题
	 * @param samplingPrice 采样价
	 * @param themes 模板主题列表
	 * @throws JsonProcessingException 
	 * @author Jianhua Ying 
	 */
	public void publish(Integer memberId, Integer category, String title, BigDecimal samplingPrice, List<ThemeView> themes) throws JsonProcessingException;

}
