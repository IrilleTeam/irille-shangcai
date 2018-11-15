package com.shangcai.dao.common.impl;

import org.springframework.stereotype.Repository;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.IManagerDao;
import com.shangcai.entity.common.Manager;

import irille.pub.DateTools;
import irille.pub.Str;

@Repository
public class ManagerDao extends BaseDao<Manager>  implements IManagerDao{
    
	/**
	 * 验证登录
	 */
	@Override
	public Manager loginCheck(String login_name, String password) {
		if (Str.isEmpty(login_name))
			throw new WebMessageException(ReturnCode.failure, "请输入用户名");
		if (password == null || Str.isEmpty(password))
			throw new WebMessageException(ReturnCode.failure, "请输入密码");
		Manager manager =selectFrom(Manager.class).where(Manager.T.LOGIN_NAME.eq(login_name)).query();
		if (manager == null){
			throw new WebMessageException(ReturnCode.failure, "您输入的账户名和密码不匹配，请重新输入");
		}
		if (chkPwd(manager.getPkey(), password) == false){
			throw new WebMessageException(ReturnCode.failure, "您输入的账户名和密码不匹配，请重新输入");
		}
		return manager;
	}
	
	
	public static boolean chkPwd(Integer key, String mm) {
		Manager login = selectFrom(Manager.class,key);
		String pwd = key + mm;
		return login.getPassword().compareTo(inCode(pwd)) == 0;
	}
   
	public static String inCode(String str) {
		return DateTools.getDigest(str.toLowerCase());
	}

}
