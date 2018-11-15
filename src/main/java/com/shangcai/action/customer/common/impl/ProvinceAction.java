package com.shangcai.action.customer.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.common.IProvinceAction;
import com.shangcai.entity.common.Province;
import com.shangcai.service.common.IProvinceService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.common.ProvinceAction")
@Scope("prototype")
public class ProvinceAction extends CustomerAction<Province, Integer> implements IProvinceAction {

	@Autowired
	private IProvinceService provinceService;

	@Override
	public void listLocation() throws IOException {
		write(provinceService.listLocation());
	}

}
