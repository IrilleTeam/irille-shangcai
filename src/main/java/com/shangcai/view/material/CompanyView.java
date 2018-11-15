package com.shangcai.view.material;

import com.shangcai.view.common.MemberView;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyView extends MemberView implements BaseView {

	private String companyName;
	private Integer establishedTime;
	private String contact;
	private String address;
	private String profile;

}
