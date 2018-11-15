package com.shangcai.action.customer.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.common.ICategoryAction;
import com.shangcai.entity.common.Category;
import com.shangcai.service.common.ICategoryService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.common.impl.CategoryAction")
@Scope("prototype")
public class CategoryAction extends CustomerAction<Category, Integer> implements ICategoryAction {

	@Autowired
	private ICategoryService categoryService;
	
	private Byte type;
	
	@Override
	public void list() throws IOException {
		write(categoryService.list(type));
	}

}
