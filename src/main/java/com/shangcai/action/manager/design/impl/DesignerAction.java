package com.shangcai.action.manager.design.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.manager.ManagerAction;
import com.shangcai.action.manager.design.IDesignerAction;
import com.shangcai.entity.design.Designer;
import com.shangcai.service.common.IMemberService;

@Controller("com.shangcai.action.manager.design.impl.DesignerAction")
@Scope("prototype")
public class DesignerAction extends ManagerAction<Designer, Integer> implements IDesignerAction {

	private String name;
	@Autowired
    private IMemberService memberService;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设计师列表
	 * 
	 * @author GS
	 * @throws Exception
	 * 
	 */
	@Override
	public void list() throws Exception {
		write(memberService.getDesignerList(getStart(), getLimit(), name));
	}

}
