package com.shangcai.view.material;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeView implements BaseView {

	private String name; // 主题名称 VARCHAR(20)<null>
	private String content; // 内容 VARCHAR(100)<null>
	private String pictures; // 图片地址 VARCHAR(2000)<null>
	private String video; // 视频地址 VARCHAR(200)<null>
}
