package com.shangcai.service.material;

import com.irille.core.controller.PageView;
import com.shangcai.entity.common.Member;
import com.shangcai.view.material.CompanyView;

public interface ICompanyService {

	public CompanyView signup(String name, String code, String iv, String encryptedData);

	/**
	 * 将用户转换为 CompanyView
	 * 
	 * @param member
	 * @return
	 */
	public CompanyView get(Member member);

	public CompanyView get(Integer memberId);
	
	public void upd(Integer memberId, String name, String contact_number, Integer established_time, String address, String profile);
	
	public PageView<CompanyView> list(String name, Integer start, Integer limit);
}
