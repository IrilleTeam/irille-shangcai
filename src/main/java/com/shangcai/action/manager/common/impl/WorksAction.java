package com.shangcai.action.manager.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.irille.core.controller.PageView;
import com.shangcai.action.BaseAction;
import com.shangcai.action.manager.common.IWorksAction;
import com.shangcai.entity.common.Works;
import com.shangcai.service.common.IWorksService;
import com.shangcai.view.common.WorksView;

@Controller("com.shangcai.action.manager.common.impl.WorksAction")
@Scope("prototype")
public class WorksAction extends BaseAction<Works, Integer> implements IWorksAction {
	private String name;
	private Integer status;
	private String reasonUnappr;
	@Autowired
	private IWorksService workservice;
  
	
	
	public String getReasonUnappr() {
		return reasonUnappr;
	}

	public void setReasonUnappr(String reasonUnappr) {
		this.reasonUnappr = reasonUnappr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void list() throws Exception {
		PageView<WorksView> list = workservice.list(getPkey(), status,  getStart(), getLimit(), name);
		write(list);
	}
   
	/**
	 * 作品下线
	 * @author GS
	 */
	@Override
	public void unappr() {
		Works works=new Works();
		works.setPkey(getPkey());
		works.setReasonUnappr(reasonUnappr);
		workservice.uappar(works);
		try {
		write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * 作品审核
	 * @author GS
	 */
	@Override
	public void appr() {
		Works works=new Works();
		works.setPkey(getPkey());
		workservice.appar(works);
		try {
		write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
