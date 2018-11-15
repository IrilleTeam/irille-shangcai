package com.shangcai.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangcai.dao.common.ICityDao;
import com.shangcai.dao.common.IProvinceDao;
import com.shangcai.entity.common.City;
import com.shangcai.entity.common.Province;
import com.shangcai.service.common.IProvinceService;
import com.shangcai.view.common.CityView;
import com.shangcai.view.common.ProvinceView;

@Service
public class ProvinceService implements IProvinceService {
	
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IProvinceDao provinceDao;

	public List<ProvinceView> listLocation() {
		
		List<City> citys = cityDao.list();
		List<Province> provinces = provinceDao.list();
		
		Map<Integer, List<City>> map = new HashMap<>();
		for (City city : citys) {
			if (!map.containsKey(city.getProvince()))
				map.put(city.getProvince(), new ArrayList<>());
			map.get(city.getProvince()).add(city);
		}
		
		List<ProvinceView> view_provinces = new ArrayList<>();
		for (Province province : provinces) {
			view_provinces.add(new ProvinceView() {
				{
					setPkey(province.getPkey());
					setName(province.getName());
					List<CityView> view_citys = new ArrayList<>();
					List<City> cs = map.get(province.getPkey());
					if(cs != null)
						for(City city:cs) {
							view_citys.add(new CityView() {{
								setPkey(city.getPkey());
								setName(city.getName());
							}});
						}
					setCitys(view_citys);
				}
			});
		}
		return view_provinces;
	}

}
