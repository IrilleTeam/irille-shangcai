package com.shangcai.service.design.impl;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.controller.JsonWriter;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.common.wx.WXAPI;
import com.shangcai.common.wx.WXBizDataCrypt;
import com.shangcai.common.wx.WXBizDataCrypt.EncryptedData;
import com.shangcai.dao.common.ICityDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IProvinceDao;
import com.shangcai.dao.design.IDesignerDao;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Member.Type;
import com.shangcai.entity.design.Designer;
import com.shangcai.service.cache.ISessionService;
import com.shangcai.service.design.IDesignerService;
import com.shangcai.view.common.CityView;
import com.shangcai.view.common.ProvinceView;
import com.shangcai.view.design.DesignerView;
import com.shangcai.view.wx.SessionView;

@Service
public class DesignerService implements IDesignerService {

	private static final Logger logger = LoggerFactory.getLogger(DesignerService.class);
	
	@Autowired
	private ISessionService sessionService;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IDesignerDao designerDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IProvinceDao provinceDao;
	@Autowired
	private WXAPI wxApi;
	@Autowired
	private WXBizDataCrypt wxBizDataCrypt;
	
	@Override
	public DesignerView signup(String name, String code, String iv, String encryptedData) {
		SessionView sessionView = wxApi.code2session(code);
		EncryptedData ed = null;
		try {
			ed = wxBizDataCrypt.decryptData(sessionView.getSession_key(), encryptedData, iv);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| IOException | NoSuchProviderException e) {
			logger.error("解析错误", e);
			throw new WebMessageException(ReturnCode.third_unknow, "微信服务错误");
		}
		JsonWriter.toConsole(ed);
		Member member = new Member().init();
		member.stType(Type.designer);
		member.setName(name);
		member.setHeadPic(ed.getAvatarUrl());
		member.setWxOpenId(sessionView.getOpenid());
		member.setWxUnionId(sessionView.getUnionid());
		member.setCreatedTime(new Date());
		memberDao.add(member);
		member.setQrcode("");//TODO 二维码生成规则 尚未确定 暂时保存为空字符串
//		member.setQrcode(wxApi.getWXACodeUnlimit(wxApi.getAccessToken(), "member_id="+member.getPkey(), "pages/shop/shop"));//TODO 未测试
		memberDao.upd(member);
		Designer designer = new Designer().init();
		designer.setMember(member.getPkey());
		designerDao.add(designer);
		
		Session session = sessionService.add(member);
		
		DesignerView view = get(member);
		
		view.setPkey(member.getPkey());
		view.setType(member.getType());
		view.setHeadPic(member.getHeadPic());
		view.setToken(session.getToken());
		
		return view;
	}
	
	@Override
	public void upd(Integer memberId, String name, String contactNumber, Short age, Byte sex, Short workingYears, String profile) {
		if(name == null || name.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善设计师名字");
		if(age == null)
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善年龄信息");
		if(age > 200 || age < 1)
			throw new WebMessageException(ReturnCode.valid_illegal, "请填写正确的年龄");
		if(sex == null)
			throw new WebMessageException(ReturnCode.valid_notnull, "请完善性别信息");
		if(sex != 0 && sex != 1 && sex != 2)
			throw new WebMessageException(ReturnCode.valid_notnull, "请完善正确的性别");
		if(workingYears == null)
			throw new WebMessageException(ReturnCode.valid_notnull, "请完善工作年限信息");
		if(workingYears > 200 || workingYears < 0)
			throw new WebMessageException(ReturnCode.valid_notnull, "请填写正确的工作年限");
		if(profile == null || profile.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善个人简介信息");
		
		Member member = memberDao.get(memberId);
		if(member.gtType() != Type.designer) {
			throw new WebMessageException(ReturnCode.valid_illegal, "您不是设计师");
		}
		member.setContactNumber(contactNumber);
		memberDao.upd(member);
		
		Designer designer = designerDao.get(memberId);
		designer.setDesignerName(name);
		designer.setAge(age);
		designer.setSex(sex);
		designer.setWorkingYears(workingYears);
		designer.setPersonalProfile(profile);
		designerDao.upd(designer);
	}

	@Override
	public DesignerView get(Member member) {
		Designer designer = designerDao.get(member.getPkey());
		CityView city = cityDao.getView(member.getCity());
		ProvinceView province = provinceDao.getView(member.getProvince());
		DesignerView view = new DesignerView();
		view.setName(member.getName());
		view.setDesignerName(designer.getDesignerName());
		view.setContactNumber(member.getContactNumber());
		view.setAge(designer.getAge());
		view.setSex(designer.getSex());
		view.setWorkingYears(designer.getWorkingYears());
		view.setProfile(designer.getPersonalProfile());
		view.setCity(city);
		view.setProvince(province);
		view.setWorksCount(member.getWorksCount());
		view.setFollowerCount(member.getFollowerCount());
		view.setFollowingCount(member.getFollowingCount());
		// TODO 浏览量需要数据库数据加上缓存数据才是实时数据
		view.setPvCount(member.getPvCount());
		
		return view;
	}

	@Override
	public DesignerView get(Integer memberId) {
		return get(memberDao.get(memberId));
	}

}
