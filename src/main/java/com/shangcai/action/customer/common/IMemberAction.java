package com.shangcai.action.customer.common;

import java.io.IOException;

public interface IMemberAction {
	
	/**
	 * 用户登录
	 * <p>保存用户的登录状态,设置过期时间,并返回自定义的登录状态,用于后续业务逻辑中前后端交互时识别用户身份
	 * @throws IOException
	 * @author Jianhua Ying
	 */
	public void signin() throws IOException;

	/**
	 * 用户注册
	 */
	public void signup() throws IOException;

	/**
	 * 我的-二维码
	 */
	public void qrcode() throws IOException;

	/**
	 * 设置我的地域信息
	 */
	public void updLocation() throws IOException;

	/**
	 * 获取我的地域信息
	 */
	public void getLocation() throws IOException;
	
	/**
	 * 关注用户
	 * @throws IOException
	 */
	public void follow() throws IOException;
	
	/**
	 * 取消关注
	 * @throws IOException
	 */
	public void unfollow() throws IOException;

}
