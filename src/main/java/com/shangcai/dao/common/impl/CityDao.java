package com.shangcai.dao.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.shangcai.dao.BaseDao;
import com.shangcai.dao.common.ICityDao;
import com.shangcai.entity.common.City;
import com.shangcai.entity.common.City.T;
import com.shangcai.view.common.CityView;

@Repository
public class CityDao extends BaseDao<City> implements ICityDao {

	/**
	 * @return
	 * @author Jianhua Ying
	 */
	public List<City> list() {
		return selectFrom(City.class).queryList();
	}

	/**
	 * @param pkey
	 * @return
	 * @author Jianhua Ying
	 */
	public City get(Integer pkey) {
		return selectFrom(City.class, pkey);
	}

	/**
	 * @param pkey
	 * @return
	 * @author Jianhua Ying
	 */
	public CityView getView(Integer pkey) {
		return select(T.PKEY, T.NAME).from(City.class).where(T.PKEY.eq(pkey)).query(CityView.class);
	}
}
