package com.shangcai.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.core.controller.Writeable;
import com.irille.core.web.exception.WebException;
import com.irille.core.web.exception.WebMessage;
import com.irille.core.web.exception.WebMessageException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangcai.action.BaseAction;

import irille.pub.Exp;

public class ItpExceptionHandler extends AbstractInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(ItpExceptionHandler.class);

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Long date1 = System.currentTimeMillis();
		try {
			return invocation.invoke();
		} catch (Exception e) {
			if(!(e instanceof Exp) && !(e instanceof WebException)) {
				logger.error("未知错误", e);
			}
			if(e instanceof Exp) {
				logger.error("Exp错误", e);
			}
			ActionProxy proxy = invocation.getProxy();
			Method method = proxy.getAction().getClass().getMethod(proxy.getMethod());
			if(method.getReturnType().equals(void.class)){
				Writeable writer = null;
				if(invocation.getAction() instanceof BaseAction)
					writer = (Writeable)invocation.getAction();
				else
					writer = new Writeable() {{}};
				if(e instanceof WebMessageException) {
					WebMessageException we = (WebMessageException)e;
					writer.write(we.getCode(), we.getMessage());
				} else 
					writer.write(WebMessage.third_error);
			}
		} finally {
			logger.info("后台处理【{}】共消耗【{}】毫秒", getJumpUrl(ServletActionContext.getRequest()), System.currentTimeMillis()-date1);
		}
		return null;
	}

	private String getJumpUrl(HttpServletRequest request) {
		String params = getParameters(request);
		if("".equals(params)) {
			return getServletUrl(request);
		} else {
			return getServletUrl(request)+"?"+params;
		}
	}
	private String getParameters(HttpServletRequest request) {
		String params = "";
		Enumeration<?> names = request.getParameterNames();
		int i=0;
		while(names.hasMoreElements()) {
			Object element  = names.nextElement();
			if(i!=0)
				params += "&";
			params += (element+"="+request.getParameter(element.toString()));
			i++;
		}
		return params;
	}
	private String getServletUrl(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
