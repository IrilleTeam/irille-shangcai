package com.shangcai.view.common;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVies implements BaseView {

	private Integer pkey;
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh",timezone="GMT+8")
	private Date created_time;
	private Integer works_count;
	private Integer likes_count;
	private Byte sex;
	private Short age;
	private Integer total;

}
