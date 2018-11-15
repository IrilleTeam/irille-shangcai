package com.shangcai.dao.material;

import java.util.List;

import com.shangcai.entity.material.Design;
import com.shangcai.view.common.WorksView;

public interface IDesignDao {

	public List<WorksView> list(Integer pkey, Integer status, Integer start, Integer limit, String name);

	public Design findByWorksId(Integer worksId);
	
	public void add(Design design);
	
	public List<Design> getDesignList();
}
