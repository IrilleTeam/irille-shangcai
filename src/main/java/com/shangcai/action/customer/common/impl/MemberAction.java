package com.shangcai.action.customer.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.common.IMemberAction;
import com.shangcai.entity.common.Member;
import com.shangcai.service.common.IMemberService;
import com.shangcai.service.design.IDesignerService;
import com.shangcai.service.material.ICompanyService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.common.MemberAction")
@Scope("prototype")
public class MemberAction extends CustomerAction<Member, Integer> implements IMemberAction {

	@Autowired
	private IMemberService memberService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDesignerService designerService;

	private Byte type;
	private String name;
	private String code;
	private String iv;
	private String encryptedData;

	@Override
	public void signup() throws IOException {
		if (type.equals(Member.Type.company.getLine().getKey())) {
			write(companyService.signup(name, code, iv, encryptedData));
		} else if (type.equals(Member.Type.designer.getLine().getKey())) {
			write(designerService.signup(name, code, iv, encryptedData));
		} else {
			throw new WebMessageException(ReturnCode.valid_illegal, "不支持的用户类型");
		}
	}

	@Override
	public void qrcode() throws IOException {
		write(memberService.qrcode(curMember().getPkey()));
	}

	private Integer province;
	private Integer city;

	@Override
	public void updLocation() throws IOException {
		memberService.updLocation(curMember().getPkey(), province, city);
		write();
	}

	@Override
	public void getLocation() throws IOException {
		write(memberService.getLocation(curMember().getPkey()));  
	}

	@Override
	public void signin() throws IOException {
		write(memberService.signIn(code));
	}

	private Integer member_id;
	
	@Override
	public void follow() throws IOException {
		memberService.follow(true, curMember(), member_id);
		write();
	}

	@Override
	public void unfollow() throws IOException {
		memberService.follow(false, curMember(), member_id);
		write();
	}

}
