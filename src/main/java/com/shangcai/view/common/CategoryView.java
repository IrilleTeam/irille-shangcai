package com.shangcai.view.common;

import java.util.List;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryView implements BaseView {
	private Integer pkey;
	private String name;
	private Integer sort;
	private Integer parent;
	private List<CategoryView> subs;

}
