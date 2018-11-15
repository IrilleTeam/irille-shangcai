package com.shangcai.dao.common;

import com.shangcai.entity.common.Manager;

public interface IManagerDao {
	
	  public  Manager  loginCheck(String login_name,String password);

}
