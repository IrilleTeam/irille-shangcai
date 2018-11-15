package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.Province;
import com.shangcai.view.common.ProvinceView;

public interface IProvinceDao {

	public List<Province> list();

	public ProvinceView getView(Integer pkey);
}
