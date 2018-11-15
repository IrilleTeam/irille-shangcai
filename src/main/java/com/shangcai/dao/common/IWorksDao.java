package com.shangcai.dao.common;

import java.util.List;

import com.shangcai.entity.common.Works;
import com.shangcai.view.common.WorksView;

public interface IWorksDao {

	public List<WorksView> list(Integer pkey, Integer start, Integer limit, String name, Integer status);

	public void unappr(Works works);

	public void appr(Works works);

	public List<Works> worksList();

	/**
	 * 
	 * 查询作品列表
	 * 
	 * @param memberId 用户pkey
	 * @param collection 收藏用户的pkey
	 * @param likes 点赞用户的pkey
	 * @param status 作品状态, 若为空 ,表示查询所有状态
	 * @param start 开始记录数
	 * @param limit 每页记录数
	 * @return
	 */
	public List<Works> list(Integer memberId, Integer collection, Integer likes, Byte status, Integer start, Integer limit);

	public Works get(Integer worksId);

	public boolean exists(Integer worksId);
	
	public void add(Works work);
	
	public void upd(Works works);
	
	/**
	 * 增加作品的浏览量
	 */
	public void incrementPv(Integer worksId);

}
