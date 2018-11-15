package com.shangcai.view.design;

import com.shangcai.view.common.MemberView;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerView extends MemberView implements BaseView {

	private String designerName;
	private Short age;
	private Byte sex;
	private Short workingYears;
	private String profile;

}
