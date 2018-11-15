package com.shangcai.action.customer.common.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.shangcai.action.customer.CustomerAction;
import com.shangcai.action.customer.common.IWorksAction;
import com.shangcai.entity.common.Works;
import com.shangcai.service.common.IWorksService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller("com.shangcai.action.customer.common.WorksAction")
@Scope("prototype")
public class WorksAction extends CustomerAction<Works, Integer> implements IWorksAction {

	@Autowired
	private IWorksService worksService;

	private boolean is_private = false;
	private boolean is_collection = false;
	private boolean is_likes = false;
	private Integer member_id;
	
	@Override
	public void list() throws IOException {
		write(worksService.list(curMember(), member_id, is_private, is_collection, is_likes, start, limit));
	}

	@Override
	public void get() throws IOException {
		write(worksService.get(curMember().getPkey(), getPkey()));
	}

	@Override
	public void like() throws IOException {
		worksService.like(true, getPkey(), curMember().getPkey());
		write();
	}

	@Override
	public void unlike() throws IOException {
		worksService.like(false, getPkey(), curMember().getPkey());
		write();
	}

	@Override
	public void collect() throws IOException {
		worksService.collect(true, getPkey(), curMember().getPkey());
		write();
	}

	@Override
	public void uncollect() throws IOException {
		worksService.collect(false, getPkey(), curMember().getPkey());
		write();
	}

	public boolean isIs_private() {
		return is_private;
	}

	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}

	public boolean isIs_collection() {
		return is_collection;
	}

	public void setIs_collection(boolean is_collection) {
		this.is_collection = is_collection;
	}

	public boolean isIs_likes() {
		return is_likes;
	}

	public void setIs_likes(boolean is_likes) {
		this.is_likes = is_likes;
	}

}
