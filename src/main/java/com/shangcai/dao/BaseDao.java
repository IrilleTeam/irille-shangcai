package com.shangcai.dao;

import java.util.List;

import com.irille.core.repository.Query2;
import com.irille.core.repository.orm.Entity;

import irille.pub.util.GenericsUtils;

public class BaseDao<T extends Entity> extends Query2 {
	
	@SuppressWarnings("unchecked")
	public BaseDao() {
		entityClass = (Class<T>)GenericsUtils.getSuperClassGenricType(getClass());
	}

	private Class<T> entityClass;
	
	public void add(T entity) {
		entity.ins();
	}
	
	public void upd(T entity) {
		entity.upd();
	}

	public List<T> list() {
		return selectFrom(entityClass).queryList();
	}
	
	public int del(Integer pkey) {
		return delete(entityClass).where(Entity.table(entityClass).primaryKey().field().eq(pkey)).execute();
	}
}
