package com.shangcai.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.irille.core.repository.orm.Entity;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.City;
import com.shangcai.entity.common.Collection;
import com.shangcai.entity.common.Comment;
import com.shangcai.entity.common.FollowRelation;
import com.shangcai.entity.common.LikesRelation;
import com.shangcai.entity.common.Manager;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Province;
import com.shangcai.entity.common.Works;
import com.shangcai.entity.design.Article;
import com.shangcai.entity.design.Designer;
import com.shangcai.entity.material.Company;
import com.shangcai.entity.material.Design;

@Configuration
public class XieliaobabaConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(XieliaobabaConfiguration.class);
	
	private static final Set<Class<? extends Entity>> entitySet = new HashSet<>();
	
	static {
		entitySet.add(Session.class);
		entitySet.add(Category.class);
		entitySet.add(City.class);
		entitySet.add(Collection.class);
		entitySet.add(Comment.class);
		entitySet.add(FollowRelation.class);
		entitySet.add(LikesRelation.class);
		entitySet.add(Manager.class);
		entitySet.add(Member.class);
		entitySet.add(Province.class);
		entitySet.add(Works.class);
		entitySet.add(Article.class);
		entitySet.add(Designer.class);
		entitySet.add(Company.class);
		entitySet.add(Design.class);
	}
	
	public XieliaobabaConfiguration() {
		initEntity();
	}
	
	public void initEntity() {
		entitySet.forEach(clazz->{
			logger.info("类初始加载： {}", Entity.table(clazz).name());
		});
	}
	
}
