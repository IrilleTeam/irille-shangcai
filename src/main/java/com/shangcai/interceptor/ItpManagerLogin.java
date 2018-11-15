package com.shangcai.interceptor;

import java.util.Map;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangcai.view.common.ManagerView;

public class ItpManagerLogin extends AbstractInterceptor {

	private static final long serialVersionUID = -7822445355454027571L;

	public static final String session_key_manager_login = "session_key_manager_login";

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		ManagerView manager = (ManagerView) session.get(session_key_manager_login);
		if (manager == null)
			throw new WebMessageException(ReturnCode.service_timeout, "登录超时,请重新登录");
		return actionInvocation.invoke();
	}

}
