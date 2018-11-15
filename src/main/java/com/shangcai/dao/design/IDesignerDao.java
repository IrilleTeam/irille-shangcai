package com.shangcai.dao.design;

import java.util.List;

import com.shangcai.entity.design.Designer;
import com.shangcai.view.common.MemberVies;

public interface IDesignerDao {
	
	public void add(Designer designer);

	public List<MemberVies> list(Integer start, Integer limit, String name);

	public Designer get(Integer memberId);

	public void upd(Designer designer);
	
	public List<Designer> getAll();
}
