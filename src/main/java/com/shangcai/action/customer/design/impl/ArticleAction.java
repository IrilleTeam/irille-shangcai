package com.shangcai.action.customer.design.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.design.IArticleAction;
import com.shangcai.entity.design.Article;
import com.shangcai.service.design.IArticleService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.design.impl.ArticleAction")
@Scope("prototype")
public class ArticleAction extends CustomerAction<Article, Integer> implements IArticleAction {

	@Autowired
	private IArticleService articleService;
	
	private Integer category;
	private String title;
	private String description;
	private String pictures;
	
	@Override
	public void publish() throws IOException {
		articleService.publish(curMember().getPkey(), category, title, description, pictures);
		write();
	}

}
