package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.City;
import com.shangcai.view.common.CityView;

public interface ICityDao {

	/**
	 * @return
	 * @author Jianhua Ying
	 */
	public List<City> list();

	/**
	 * @param pkey
	 * @return
	 * @author Jianhua Ying
	 */
	public City get(Integer pkey);

	/**
	 * @param pkey
	 * @return
	 * @author Jianhua Ying
	 */
	public CityView getView(Integer pkey);
}
