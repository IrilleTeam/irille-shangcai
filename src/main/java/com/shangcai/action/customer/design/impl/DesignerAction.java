package com.shangcai.action.customer.design.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.design.IDesignerAction;
import com.shangcai.entity.design.Designer;
import com.shangcai.service.design.IDesignerService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.design.impl.DesignerAction")
@Scope("prototype")
public class DesignerAction extends CustomerAction<Designer, Integer> implements IDesignerAction {

	@Autowired
	private IDesignerService designerService;

	private String name;
	private String contact_number;
	private Short age;
	private Byte sex;
	private Short working_years;
	private String profile;

	@Override
	public void upd() throws IOException {
		designerService.upd(curMember().getPkey(), name, contact_number, age, sex, working_years, profile);
		write();
	}

	@Override
	public void get() throws IOException {
		if(getPkey() != null) {
			write(designerService.get(getPkey()));
		} else {
			write(designerService.get(curMember()));
		}
	}

}
