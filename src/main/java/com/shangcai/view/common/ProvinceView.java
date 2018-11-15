package com.shangcai.view.common;

import java.util.List;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinceView implements BaseView {

	private Integer pkey;
	private String name;
	private List<CityView> citys;

}
