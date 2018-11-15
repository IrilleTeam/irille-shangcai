package com.shangcai.service.common;

import com.irille.core.controller.PageView;
import com.shangcai.entity.common.Member;
import com.shangcai.view.common.MemberVies;
import com.shangcai.view.common.MemberView;

public interface IMemberService {
	
	
	
	/**
	 * 登录
	 * 
	 * @param code 临时登录凭证
	 * @author Jianhua Ying 
	 */
	public MemberView signIn(String code);
	/**
	 * 通过token获取用户
	 * 
	 * <p>若token 没有过期,返回token关联的用户对象,并更新token的最新访问时间
	 * <p>否则返回空
	 * 
	 * @param token
	 * @return
	 * @author Jianhua Ying 
	 */
	public Member getByToken(String token);
	/**
	 * 更新用户的省份和城市信息
	 * 
	 * @param memberId 用户id
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @author Jianhua Ying
	 */
	public void updLocation(Integer memberId, Integer provinceId, Integer cityId);
	
	/**
	 * 获取用户的省份和城市信息
	 * 
	 * @param memberId 用户id
	 * @return MemberView 仅包含了省份和城市信息
	 * @author Jianhua Ying
	 */
	public MemberView getLocation(Integer memberId);
	
	/**
	 * 获取用户的二维码链接
	 * 
	 * @param memberId 用户id
	 * @return MemberView 仅包含了二维码链接
	 * @author Jianhua Ying
	 */
	public MemberView qrcode(Integer memberId);
	
	/**
	 * 获取设计师列表
	 * @return
	 * @throws Exception
	 */
	public  PageView<MemberVies> getDesignerList(Integer start,Integer limit,String name) throws Exception;
	
	/**
	 * 关注用户&取消关注
	 * 
	 * @param follow true: 关注, false: 取消关注
	 * @param member 当前登录用户
	 * @param memberId 关注用户的pkey
	 */
	public void follow(boolean follow, Member member, Integer memberId);
	
}
