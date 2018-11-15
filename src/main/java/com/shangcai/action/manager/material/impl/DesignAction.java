package com.shangcai.action.manager.material.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.manager.ManagerAction;
import com.shangcai.action.manager.material.IDesignAction;
import com.shangcai.entity.material.Design;
import com.shangcai.service.material.IDesignService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.manager.material.impl.DesignAction")
@Scope("prototype")
public class DesignAction extends ManagerAction<Design, Integer> implements IDesignAction {

	private String name;
	private Integer status;
	@Autowired
	private  IDesignService  designService;
    
	/**
	 * 获取企业作品列表
	 */
	@Override
	public void list() throws Exception {
		write(designService.companyWorksList(getPkey(), status, getStart(), getLimit(), name));
	}
}
