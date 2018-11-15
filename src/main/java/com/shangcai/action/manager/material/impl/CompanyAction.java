package com.shangcai.action.manager.material.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.manager.ManagerAction;
import com.shangcai.action.manager.material.ICompanyAction;
import com.shangcai.entity.material.Company;
import com.shangcai.service.material.ICompanyService;

@Controller("com.shangcai.action.manager.material.CompanyAction")
@Scope("prototype")
public class CompanyAction extends ManagerAction<Company, Integer> implements ICompanyAction {
	private String name;
	@Autowired
	private ICompanyService  companyService;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取企业信息列表
	 */
	@Override
	public void list() throws Exception {

		write(companyService.list(getName(), getStart(), getLimit()));
	}

}
