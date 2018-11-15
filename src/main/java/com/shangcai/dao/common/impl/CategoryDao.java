package com.shangcai.dao.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.ICategoryDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Category.T;
import com.shangcai.entity.common.Category.Type;
import com.shangcai.view.common.CategoryView;

import irille.pub.Log;

@Repository
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {
	public static final Log LOG = new Log(CategoryDao.class);

	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> list() {
		return selectFrom(Category.class).orderBy(T.SORT.asc()).queryList();
	}
	
	/**
	 * 查询分类列表
	 * @return
	 */
    @Override
	public List<CategoryView> allList(Byte type) {
		List<CategoryView> viewList = new ArrayList<CategoryView>();
		List<Category> firstStageList=new ArrayList<Category>();
		if(type.intValue()==2)
		firstStageList = selectFrom(Category.class).where(Category.T.UP.isNull(), Category.T.TYPE.eq(Category.Type.designer)).orderBy(T.SORT.asc()).queryList();
		else
		firstStageList = selectFrom(Category.class).where(Category.T.UP.isNull(), Category.T.TYPE.eq(Category.Type.company)).orderBy(T.SORT.asc()).queryList();	
		
		for (Category category : firstStageList) {
			CategoryView view = new CategoryView();
			view.setPkey(category.getPkey());
			view.setName(category.getName());
			view.setSort(category.getSort());
			List<CategoryView> secondViewList = new ArrayList<CategoryView>();
			List<Category> secondList = selectFrom(Category.class).where(Category.T.UP.eq(category.getPkey())).queryList();
			for (Category second : secondList) {
				CategoryView sendcondview = new CategoryView();
				sendcondview.setParent(category.getPkey());
				sendcondview.setPkey(second.getPkey());
				sendcondview.setName(second.getName());
				sendcondview.setSort(second.getSort());
				secondViewList.add(sendcondview);
			}
			view.setSubs(secondViewList);
			viewList.add(view);
		}
		return viewList;
	}

	@Override
	public Category get(Integer categoryId) {
		return selectFrom(Category.class, categoryId);
	}

	@Override
	public List<Category> list(Type type, boolean isTop) {
		return selectFrom(Category.class).where(type != null, T.TYPE.eq(type)).where(isTop, T.UP.isNull()).orderBy(T.SORT.asc()).queryList();
	}

	@Override
	public Integer countByUp(Integer categoryId) {
		return selectFrom(Category.class).where(T.UP.eq(categoryId)).queryCount();
	}

}
