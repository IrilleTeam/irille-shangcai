package com.shangcai.action.manager.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.manager.ManagerAction;
import com.shangcai.action.manager.common.ICategoryAction;
import com.shangcai.entity.common.Category;
import com.shangcai.service.common.ICategoryService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.manager.common.impl.CategoryAction")
@Scope("prototype")
public class CategoryAction extends ManagerAction<Category, Integer> implements ICategoryAction {

	@Autowired
	private  ICategoryService categoryService;
	
	private String name;
	private Integer up;
	private Byte type;
	private Integer sort;

	/**
	 * 获取设计师分类列表
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void list() throws Exception {
		write(categoryService.allList(type));
	}

	/**
	 * 添加设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void add() throws Exception {
		categoryService.ins(name, up, type, sort);
		write();
	}

	/**
	 * 更新设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void upd() throws Exception {
		System.out.println(getPkey());
		categoryService.upd(getPkey(), name, up, type, sort);
		write();
	}

	/**
	 * 删除设计师分类
	 * 
	 * @author GS
	 * @throws Exception
	 */
	@Override
	public void del() throws Exception {
		categoryService.del(getPkey());
		write();
	}

}
