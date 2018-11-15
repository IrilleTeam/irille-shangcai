package com.shangcai.action.manager.common.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.action.manager.common.IManagerAction;
import com.shangcai.entity.common.Manager;
import com.shangcai.service.common.IManagerService;
import com.shangcai.view.common.ManagerView;

import irille.pub.Str;
import irille.pub.verify.RandomImageServlet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.manager.common.impl.ManagerAction")
@Scope("prototype")
public class ManagerAction extends com.shangcai.action.manager.ManagerAction<Manager, Integer> implements IManagerAction {

	private static final Logger logger = LoggerFactory.getLogger(ManagerAction.class);

	@Autowired
	private IManagerService managerService;

	private String mmOld; // 原密码
	private String mmNew; // 新密码
	private String mmCheck; // 新密码确认
	private String checkCode; // 验证码
	private String password; // 原密码
	private String loginName;

	// 取Session中的验证码
	private String verifyCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession ssn = request.getSession(false);
		if (ssn == null)
			return null;
		return (String) ssn.getAttribute(RandomImageServlet.RANDOM_LOGIN_KEY);
	}
	
	

	@Override
	public void login() throws Exception {
		logger.debug("loginName:{}", loginName);
		logger.debug("password:{}", password);
		logger.debug("checkCode:{}", checkCode);
		String verifyCode = verifyCode();
		logger.debug("verifyCode:{}", verifyCode);

		if (Str.isEmpty(verifyCode) || Str.isEmpty(checkCode) || verifyCode.equals(checkCode) == false)
			throw new WebMessageException(ReturnCode.failure, "验证码错误");
		ManagerView manager = managerService.loginCheck(loginName, password);
		if (manager == null)
			throw new WebMessageException(ReturnCode.service_gone, "用户不存在或密码不匹配");
		setLoginManager(manager);
		write(manager);
	}

	@Override
	public void info() throws IOException {
		write(getLoginManager());
	}

	@Override
	public void signout() throws IOException {
		setLoginManager(null);
		write();
	}
	
}
