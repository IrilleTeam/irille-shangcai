package com.shangcai.view.common;

import java.util.List;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerView implements BaseView {

	private String name;
	private List<String> roles;

}
