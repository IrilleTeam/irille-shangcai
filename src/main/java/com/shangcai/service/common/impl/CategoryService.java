package com.shangcai.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.common.ICategoryDao;
import com.shangcai.entity.common.Category;
import com.shangcai.entity.common.Category.Type;
import com.shangcai.service.common.ICategoryService;
import com.shangcai.view.common.CategoryView;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public List<CategoryView> list(Byte type_byte) {
		Type type;
		try {
			type = (Type)Type.company.getLine().get(type_byte);
		} catch (Exception e) {
			return null;
		}
		
		List<Category> list = categoryDao.list(type, false);
		Map<Integer, CategoryView> map = new HashMap<>();
		list.forEach(category->{
			map.put(category.getPkey(), new CategoryView() {{
				setPkey(category.getPkey());
				setName(category.getName());
			}});
		});
		
		List<CategoryView> views = new ArrayList<>();
		list.forEach(category->{
			if(category.getUp()==null)
				views.add(map.get(category.getPkey()));
			else {
				CategoryView up = map.get(category.getUp());
				if(up.getSubs()==null)
					up.setSubs(new ArrayList<>());
				up.getSubs().add(map.get(category.getPkey()));
			}
		});
		return views;
	}

	/**
	 * 获取设计师分类列表
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public List<CategoryView> allList(Byte type) throws Exception {
	  return	categoryDao.allList(type);
	}

	/**
	 * 添加设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void ins(String name, Integer up, Byte type, Integer sort ) throws Exception {
		if(type == null)
			throw new WebMessageException(ReturnCode.valid_notnull, "分类所属用户类型不可为空");
		if (type != 1 && type != 2)
			throw new WebMessageException(ReturnCode.valid_illegal, "不是正确的分类类型");
		if (null == name || name.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "分类名称不可为空");
		if(up != null) {
			Category upCategory = categoryDao.get(up);
			if(upCategory == null)
				throw new WebMessageException(ReturnCode.valid_illegal, "所选上级分类不存在");
			//如果所选上级分类还有上级分类 ,报错
			if(upCategory.getUp() != null)
				throw new WebMessageException(ReturnCode.valid_illegal, "请选择一级分类作为上级分类");
		}
		if (null == sort)
			sort = 99;
		
		Category category = new Category();
		category.setType(type);
		category.setName(name);
		category.setSort(sort);
		category.setUp(up);
		
		categoryDao.add(category);
	}

	/**
	 * 更新设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void upd(Integer pkey, String name, Integer up, Byte type, Integer sort) throws Exception {
		if(null == pkey)
			throw new WebMessageException(ReturnCode.valid_illegal, "请选中分类进行修改");
		if(null == name || name.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请填写分类名称");
		if(up != null) {
			Category upCategory = categoryDao.get(up);
			if(upCategory == null)
				throw new WebMessageException(ReturnCode.valid_illegal, "所选上级分类不存在");
			//如果所选上级分类还有上级分类 ,报错
			if(upCategory.getUp() != null)
				throw new WebMessageException(ReturnCode.valid_illegal, "请选择一级分类作为上级分类");
			//如果本身有下级分类, 不能选择上级分类
			if(categoryDao.countByUp(pkey) > 0)
				throw new WebMessageException(ReturnCode.valid_illegal, "该分类已有下级分类,不能选择上级分类");
		}
		if(null == sort)
			sort = 99;
		
		Category category = categoryDao.get(pkey);
		if(null == category)
			throw new WebMessageException(ReturnCode.valid_illegal, "分类不存在无法修改");
		
		category.setName(name);
		category.setUp(up);
		category.setSort(sort);
		categoryDao.upd(category);
	}

	/**
	 * 删除设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void del(Integer pkey) throws Exception {
		if(categoryDao.countByUp(pkey) > 0)
			throw new WebMessageException(ReturnCode.failure, "该分类为父分类，不可删除");
		if(categoryDao.del(pkey) == 0) {
			throw new WebMessageException(ReturnCode.valid_illegal, "该分类不存在");
		}
	}

}
