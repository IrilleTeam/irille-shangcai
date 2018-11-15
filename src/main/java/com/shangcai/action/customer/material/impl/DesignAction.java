package com.shangcai.action.customer.material.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.material.IDesignAction;
import com.shangcai.entity.material.Design;
import com.shangcai.service.material.IDesignService;
import com.shangcai.view.material.ThemeView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.material.impl.DesignAction")
@Scope("prototype")
public class DesignAction extends CustomerAction<Design, Integer> implements IDesignAction {

	@Autowired
	private IDesignService designService;

	private Integer category;
	private String title;
	private BigDecimal samplingPrice;
	private List<ThemeView> theme;

	@Override
	public void publish() throws IOException {
		designService.publish(curMember().getPkey(), category, title, samplingPrice, theme);
		write();
	}

}
