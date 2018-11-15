package com.shangcai.action.manager;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.irille.core.repository.orm.Entity;
import com.shangcai.action.BaseAction;
import com.shangcai.interceptor.ItpManagerLogin;
import com.shangcai.view.common.ManagerView;

public class ManagerAction<T extends Entity, R extends Serializable> extends BaseAction<T, R> implements SessionAware {

	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	protected void setLoginManager(ManagerView manager) {
		session.put(ItpManagerLogin.session_key_manager_login, manager);
	}
	protected ManagerView getLoginManager() {
		return (ManagerView)session.get(ItpManagerLogin.session_key_manager_login);
	}
}
