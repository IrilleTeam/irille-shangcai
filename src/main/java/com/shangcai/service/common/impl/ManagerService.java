package com.shangcai.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangcai.dao.common.IManagerDao;
import com.shangcai.entity.common.Manager;
import com.shangcai.service.common.IManagerService;
import com.shangcai.view.common.ManagerView;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	private IManagerDao managerDao;

	/**
	 * 校驗用戶鄧錄
	 */
	@Override
	public ManagerView loginCheck(String login_name, String password) {
		Manager manager = managerDao.loginCheck(login_name, password);
		ManagerView managerView = new ManagerView();
		if (null == manager) {
			return managerView;
		} else {
			managerView.setName(manager.getName());
			List<String> roles = new ArrayList<>();
			roles.add("admin");
			managerView.setRoles(roles);
			return managerView;
		}

	}

	@Override
	public ManagerView toView(Manager manager) {
		ManagerView view = new ManagerView();
		view.setName(manager.getName());
		return view;
	}

}
