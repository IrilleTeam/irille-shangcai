package com.shangcai.action.customer.material.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.material.ICompanyAction;
import com.shangcai.entity.material.Company;
import com.shangcai.service.material.ICompanyService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.material.impl.CompanyAction")
@Scope("prototype")
public class CompanyAction extends CustomerAction<Company, Integer> implements ICompanyAction {

	@Autowired
	private ICompanyService companyService;

	private String name;
	private String contact_number;
	private Integer established_time;
	private String address;
	private String profile;

	@Override
	public void upd() throws IOException {
		companyService.upd(curMember().getPkey(), name, contact_number, established_time, address, profile);
		write();
	}

	@Override
	public void get() throws IOException {
		if(getPkey() != null) {
			write(companyService.get(getPkey()));
		} else {
			write(companyService.get(curMember()));
		}
	}

}
