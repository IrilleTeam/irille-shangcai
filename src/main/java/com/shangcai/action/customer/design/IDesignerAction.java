package com.shangcai.action.customer.design;

import java.io.IOException;

public interface IDesignerAction {

	/**
	 * 设置个人信息-设计师
	 */
	public void upd() throws IOException;

	/**
	 * 获取个人信息-设计师
	 */
	public void get() throws IOException;
}
