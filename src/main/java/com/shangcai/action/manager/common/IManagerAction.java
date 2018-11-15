package com.shangcai.action.manager.common;

import java.io.IOException;

public interface IManagerAction {
	
	public  void  login() throws Exception;
	
	/**
	 * 注销登录
	 * 
	 * @throws IOException
	 * @author yjh
	 */
	public void signout() throws IOException;
	
	/**
	 * 获取登录用户信息
	 * 
	 * @throws IOException
	 * @author yjh
	 */
	public void info() throws IOException;

}
