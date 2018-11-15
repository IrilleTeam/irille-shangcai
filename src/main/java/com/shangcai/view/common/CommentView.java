package com.shangcai.view.common;

import java.util.Date;

import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentView implements BaseView {

	private String content;
	private MemberView member;
	private MemberView replyTo;
	private Date createdTime;

}
