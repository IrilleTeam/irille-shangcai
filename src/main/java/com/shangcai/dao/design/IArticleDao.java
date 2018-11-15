package com.shangcai.dao.design;

import java.util.List;

import com.shangcai.entity.design.Article;
import com.shangcai.view.common.WorksView;

public interface IArticleDao {
	public List<WorksView> list(Integer pkey, Integer status, Integer start, Integer limit, String name);

	public Article findByWorksId(Integer worksId);
	
	public void add(Article article);
	
	public List<Article> getArticleList();
}
