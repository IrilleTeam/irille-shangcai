package com.shangcai.action.customer.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.common.ICommentAction;
import com.shangcai.entity.common.Comment;
import com.shangcai.service.common.ICommentService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.common.CommentAction")
@Scope("prototype")
public class CommentAction extends CustomerAction<Comment, Integer> implements ICommentAction {

	@Autowired
	private ICommentService commentService;

	private Integer works_pkey;
	private String content;
	private Integer reply_to;

	@Override
	public void add() throws IOException {
		commentService.add(curMember().getPkey(), works_pkey, content, reply_to);
		write();
	}

	@Override
	public void list() throws IOException {
		write(commentService.list(works_pkey, start, limit));
	}

}
