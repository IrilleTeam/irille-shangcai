package com.shangcai.action.manager.design.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.manager.ManagerAction;
import com.shangcai.action.manager.design.IArticleAction;
import com.shangcai.entity.design.Article;
import com.shangcai.service.design.IArticleService;

@Controller("com.shangcai.action.manager.design.impl.ArtcleAciton")
@Scope("prototype")
public class ArticleAciton extends ManagerAction<Article, Integer> implements IArticleAction {

	private String name;
	private Integer status;
    @Autowired
    private  IArticleService articleService;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取设计师作品列表
	 * 
	 * @author GS
	 * @throws IOException
	 */
	@Override
	public void list() throws Exception {
		write(articleService.designerWorksList(getPkey(), getStatus(), getStart(), getLimit(), getName()));
	}
}
