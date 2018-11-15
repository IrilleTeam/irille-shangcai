package com.shangcai.service.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.controller.PageView;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.common.wx.WXAPI;
import com.shangcai.dao.common.ICityDao;
import com.shangcai.dao.common.IFollowRelationDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IProvinceDao;
import com.shangcai.dao.common.IWorksDao;
import com.shangcai.dao.design.IDesignerDao;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.City;
import com.shangcai.entity.common.FollowRelation;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Member.Type;
import com.shangcai.entity.common.Works;
import com.shangcai.service.cache.ISessionService;
import com.shangcai.service.common.IMemberService;
import com.shangcai.service.design.IDesignerService;
import com.shangcai.service.material.ICompanyService;
import com.shangcai.view.common.MemberVies;
import com.shangcai.view.common.MemberView;
import com.shangcai.view.wx.SessionView;

import irille.pub.Log;

@Service
public class MemberService implements IMemberService {
	
	public static final Log LOG = new Log(MemberService.class);
	
	@Autowired
	private ISessionService sessionService;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IProvinceDao provinceDao;
	@Autowired
	private IDesignerDao designerDao;
	@Autowired
	private IWorksDao worksDao;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDesignerService designerService;
	@Autowired
	private IFollowRelationDao followRelationDao;
	@Autowired
	private WXAPI wxApi;

	/**
	 * 更新用户的省份和城市信息
	 * 
	 * @param memberId 用户id
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @author Jianhua Ying
	 */
	@Override
	public void updLocation(Integer memberId, Integer provinceId, Integer cityId) {
		Member bean = memberDao.get(memberId);
		City city = cityDao.get(cityId);
		bean.setProvince(city.getProvince());
		bean.setCity(cityId);
		memberDao.upd(bean);
	}
	
	/**
	 * 获取用户的省份和城市信息
	 * 
	 * @param memberId 用户id
	 * @return MemberView 仅包含了省份和城市信息
	 * @author Jianhua Ying
	 */
	@Override
	public MemberView getLocation(Integer memberId) {
		Member member = memberDao.get(memberId);
		return new MemberView() {{
			setCity(cityDao.getView(member.getCity()));
			setProvince(provinceDao.getView(member.getProvince()));
		}};
	}
		
	/**
	 * 获取设计师列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public  PageView<MemberVies> getDesignerList(Integer start, Integer limit, String name) throws Exception {
		List<MemberVies> oldMemberViewList=designerDao.list(start, limit, name);
		List<MemberVies> memberViewList=new ArrayList<MemberVies>();
		List<Works> worksList=worksDao.worksList();
		int likes_count=0;
		int works_count=0;
		for (MemberVies view : oldMemberViewList) {
			for (Works works : worksList) {
				if(works.getMember()!=null&&works.getMember().intValue()==view.getPkey().intValue()) {
					likes_count=likes_count+works.getLikesCount().intValue();
					works_count=works_count+1;
				}
			}
			view.setLikes_count(likes_count);
			view.setWorks_count(works_count);
			memberViewList.add(view);
			likes_count=0;
			works_count=0;
		}
	    PageView<MemberVies> pageView= new PageView<>(designerDao.getAll().size(), memberViewList);
		return pageView;
	}
	
	@Override
	public MemberView qrcode(Integer memberId) {
		Member member = memberDao.get(memberId);
		return new MemberView() {{
			setQrcode(member.getQrcode());
		}};
	}

	@Override
	public Member getByToken(String token) {
		Session session = sessionService.get(token);
		if(session == null) {
			return null;
		}
		if(session.getTimeout() != -1) {
			if(session.getAccessdTime().getTime()+session.getTimeout()*60000<System.currentTimeMillis())
				return null;
		}
		session.setAccessdTime(new Date());
		sessionService.upd(session);
		return memberDao.get(session.getMember());
	}

	@Override
	public MemberView signIn(String code) {
		SessionView sessionView = wxApi.code2session(code);
		Member member = memberDao.findByOpenId(sessionView.getOpenid());
		if(member == null)
			throw new WebMessageException(ReturnCode.service_gone, "请先注册成为设计师或者辅料商");
		Session session  = sessionService.add(member);
		MemberView view = null;
		if(member.gtType() == Type.company) {
			view = companyService.get(member);
		} else if(member.gtType() == Type.designer) {
			view = designerService.get(member);
		}
		view.setPkey(member.getPkey());
		view.setType(member.getType());
		view.setHeadPic(member.getHeadPic());
		view.setToken(session.getToken());
		return view;
	}

	@Override
	public void follow(boolean follow, Member member, Integer memberId) {
		
		FollowRelation followRelation = new FollowRelation().init();
		if(memberId == null) {
			if(follow)
				throw new WebMessageException(ReturnCode.valid_illegal, "请指定要关注的用户");
			else
				throw new WebMessageException(ReturnCode.valid_illegal, "请指定要取关的用户");
		}
		if(memberDao.get(memberId) == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "该用户不存在");
		FollowRelation relation = followRelationDao.findByMemberFollower(memberId, member.getPkey());
		if(follow && relation != null)
			throw new WebMessageException(ReturnCode.valid_illegal, "您已关注该用户");
		if(!follow && relation == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "您尚未关注该用户");
		
		if(follow) {
			followRelation.setMember(memberId);
			followRelation.setFollower(member.getPkey());
			followRelation.setCreatedTime(new Date());
			
			followRelationDao.add(followRelation);
		} else {
			followRelationDao.del(relation.getPkey());
		}
	}
	
}
