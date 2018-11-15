package com.shangcai.interceptor;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irille.core.controller.JsonWriter;
import com.irille.core.controller.Writeable;
import com.irille.core.web.exception.WebMessage;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangcai.action.BaseAction;
import com.shangcai.entity.common.Member;
import com.shangcai.service.common.IMemberService;

@Component
public class ItpWxLogin extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ItpWxLogin.class);

	@Autowired
	private IMemberService memberService;

	private static ThreadLocal<Member> thread_local_member = new ThreadLocal<>();

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String token = ServletActionContext.getRequest().getParameter("token");
		logger.info("token:\r\n{}", token);

		Writeable writer = null;
		if(invocation.getAction() instanceof BaseAction)
			writer = (Writeable)invocation.getAction();
		else
			writer = new Writeable() {{}};

		Member member = null;
		if(token == null || (member = memberService.getByToken(token))==null) {
			writer.write(WebMessage.timeout);
			return null;
		}
		logger.info("member:\r\n{}", JsonWriter.asString(member));
		setMember(member);
		String result = invocation.invoke();
		removeMember();
		return result;
	}

	public static Member getMember() {
		return thread_local_member.get();
	}

	public static void setMember(Member member) {
		thread_local_member.set(member);
	}

	public static void removeMember() {
		thread_local_member.remove();
	}
}
