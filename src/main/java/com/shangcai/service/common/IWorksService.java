package com.shangcai.service.common;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irille.core.controller.PageView;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Works;
import com.shangcai.view.common.WorksView;
import com.shangcai.view.common.WorksView2;

public interface IWorksService {
	
	public PageView<WorksView> list(Integer pkey, Integer status, Integer start, Integer limit, String name) throws Exception;
	
	/**
	 * 微信端用户查询作品列表
	 * 
	 * @param member 用户 当前操作的用户
	 * @param memberId 用户pkey 列表的筛选条件
	 * @param isPrivate 是否是我的作品
	 * @param isCollection 是否是我收藏的作品
	 * @param isLikes 是否是我点赞的作品
	 * @return
	 * @author Jianhua Ying
	 */
	public List<WorksView2> list(Member member, Integer memberId, boolean isPrivate, boolean isCollection, boolean isLikes, Integer start, Integer limit);
	
	/**
	 * 微信端查询作品详情
	 * 
	 * <p>当用户不为作品作者时,只会查询到审核状态为通过的作品
	 * <p>其它用户查看才作品时, 该作品的浏览量+1
	 * 
	 * @param memberId 用户pkey
	 * @param worksId 作品pkey
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public WorksView2 get(Integer memberId, Integer worksId) throws JsonParseException, JsonMappingException, IOException;
    
	
	public void appar(Works works);
	public void uappar(Works works);
	
	/**
	 * 给作品点赞或者取消点赞
	 * 
	 * @param isLike true:点赞, false:取消收藏
	 * @param worksId 作品pkey
	 * @param memberId 用户pkey
	 */
	public void like(boolean isLike, Integer worksId, Integer memberId);
	
	/**
	 * 收藏作品或者取消收藏
	 * 
	 * @param isCollect true:收藏, false:取消收藏
	 * @param worksId 作品pkey
	 * @param memberId 用户pkey
	 */
	public void collect(boolean isCollect, Integer worksId, Integer memberId);
	
}
