package com.shangcai.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.IProvinceDao;
import com.shangcai.entity.common.Province;
import com.shangcai.entity.common.Province.T;
import com.shangcai.view.common.ProvinceView;

@Repository
public class ProvinceDao extends BaseDao<Province> implements IProvinceDao {

	public List<Province> list() {
		return selectFrom(Province.class).queryList();
	}

	public ProvinceView getView(Integer pkey) {
		return select(T.PKEY, T.NAME).from(Province.class).where(T.PKEY.eq(pkey)).query(ProvinceView.class);
	}

}
