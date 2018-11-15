package com.shangcai.view.common;

import java.util.Date;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorksView2 implements BaseView {

	private Integer pkey;
	private Byte type;
	private String picture;
	private String title;
	private String description;
	private String category;
	private Integer pvCount;
	private Integer commentCount;
	private Integer likesCount;
	private Boolean collected;
	private Boolean likes;
	private Date createdTime;
	private String name;
	private String headPic;
	private Byte status;
	private MemberView member;

}
