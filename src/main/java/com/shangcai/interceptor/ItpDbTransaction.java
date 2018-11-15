package com.shangcai.interceptor;

import com.irille.core.repository.db.ConnectionManager;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpDbTransaction extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		String rtn = null;
		String path = actionInvocation.getProxy().getActionName();
		
		try {
			rtn = actionInvocation.invoke();
			String[] ps = path.split("\\_");
			if (ps[ps.length - 1].equals("list") == false) // 查询不处理事务提交
				ConnectionManager.commitConnection();
		} catch (Exception e) {
			ConnectionManager.rollbackConnection();
			throw e;
		} finally {
			ConnectionManager.releaseConnection();
		}
		return rtn;
	}
}
