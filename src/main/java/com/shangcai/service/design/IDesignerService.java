package com.shangcai.service.design;

import com.shangcai.entity.common.Member;
import com.shangcai.view.design.DesignerView;

public interface IDesignerService {

	public DesignerView signup(String name, String code, String iv, String encryptedData);

	public void upd(Integer memberId, String name, String contactNumber, Short age, Byte sex, Short workingYears, String profile);

	/**
	 * 将用户转换为 DesignerView
	 * 
	 * @param member
	 * @return
	 */
	public DesignerView get(Member member);
	
	public DesignerView get(Integer memberId);
	
}
