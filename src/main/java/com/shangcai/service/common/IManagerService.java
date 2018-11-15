package com.shangcai.service.common;

import com.shangcai.entity.common.Manager;
import com.shangcai.view.common.ManagerView;

public interface IManagerService {  
	
	
	  public ManagerView loginCheck(String login_name,String password);
	  
	  /**
	   * 将manager对象转换成view
	   * 
	   * @param manager
	   * @return
	   */
	  public ManagerView toView(Manager manager);

}
