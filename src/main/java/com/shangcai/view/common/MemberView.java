package com.shangcai.view.common;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberView implements BaseView {

	private Integer pkey; // 主键
	private Byte type; //用户类型 1: 辅料商, 2: 设计师
	private String name; // 名字
	private String qrcode; // 二维码链接
	private String headPic; // 头像
	private String contactNumber; // 联系电话
	private ProvinceView province; // 省份
	private CityView city; // 城市
	private Integer pvCount; // 浏览量
	private Integer worksCount; // 作品数
	private Integer followingCount; // 关注数
	private Integer followerCount; // 粉丝数
	private String token;

}
