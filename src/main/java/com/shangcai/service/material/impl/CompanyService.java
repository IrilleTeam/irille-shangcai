package com.shangcai.service.material.impl;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irille.core.controller.JsonWriter;
import com.irille.core.controller.PageView;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.common.wx.WXAPI;
import com.shangcai.common.wx.WXBizDataCrypt;
import com.shangcai.common.wx.WXBizDataCrypt.EncryptedData;
import com.shangcai.dao.common.ICityDao;
import com.shangcai.dao.common.IMemberDao;
import com.shangcai.dao.common.IProvinceDao;
import com.shangcai.dao.material.ICompanyDao;
import com.shangcai.entity.cache.Session;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.common.Member.Type;
import com.shangcai.entity.material.Company;
import com.shangcai.service.cache.ISessionService;
import com.shangcai.service.material.ICompanyService;
import com.shangcai.view.common.CityView;
import com.shangcai.view.common.ProvinceView;
import com.shangcai.view.material.CompanyView;
import com.shangcai.view.wx.SessionView;

@Service
public class CompanyService implements ICompanyService {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private ISessionService sessionService;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private IProvinceDao provinceDao;
	@Autowired
	private WXAPI wxApi;
	@Autowired
	private WXBizDataCrypt wxBizDataCrypt;

	@Override
	public CompanyView signup(String name, String code, String iv, String encryptedData) {
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
		member.stType(Type.company);
		member.setName(name);
		member.setHeadPic(ed.getAvatarUrl());
		member.setWxOpenId(sessionView.getOpenid());
		member.setWxUnionId(sessionView.getUnionid());
		member.setCreatedTime(new Date());
		memberDao.add(member);
		Company company = new Company().init();
		company.setMember(member.getPkey());
		companyDao.add(company);
		member.setQrcode("");//TODO 二维码生成规则 尚未确定 暂时保存为空字符串
//		member.setQrcode(wxApi.getWXACodeUnlimit(wxApi.getAccessToken(), "member_id="+member.getPkey(), "pages/shop/shop"));//TODO 未测试
		memberDao.upd(member);
		Session session = sessionService.add(member);
		
		CompanyView view = get(member);
		
		view.setPkey(member.getPkey());
		view.setType(member.getType());
		view.setHeadPic(member.getHeadPic());
		view.setToken(session.getToken());
		
		return view;
	}

	@Override
	public CompanyView get(Member member) {
		Company company = companyDao.get(member.getPkey());
		CityView city = cityDao.getView(member.getCity());
		ProvinceView province = provinceDao.getView(member.getProvince());
		CompanyView view = new CompanyView();
		view.setName(member.getName());
		view.setCompanyName(company.getCompanyName());
		view.setContact(company.getContact());
		view.setContactNumber(member.getContactNumber());
		view.setEstablishedTime(company.getEstablishedTime());
		view.setAddress(company.getAddress());
		view.setProfile(company.getProfile());
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
	public void upd(Integer memberId, String name, String contactNumber, Integer establishedTime, String address, String profile) {
		if(name == null || name.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善设计师名字");
		if(contactNumber == null || contactNumber.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善联系方式");
		if(establishedTime == null || establishedTime < 0)
			throw new WebMessageException(ReturnCode.valid_illegal, "请填写正确的成立时间");
		if(address == null)
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善地址信息");
		if(profile == null || profile.isEmpty())
			throw new WebMessageException(ReturnCode.valid_notempty, "请完善个人简介信息");
		
		Member member = memberDao.get(memberId);
		if(member.gtType() != Type.company) {
			throw new WebMessageException(ReturnCode.valid_illegal, "您不是辅料商");
		}
		
		member.setContactNumber(contactNumber);
		memberDao.upd(member);
		
		Company company = companyDao.get(memberId);
		company.setCompanyName(name);
		company.setEstablishedTime(establishedTime);
		company.setAddress(address);
		company.setProfile(profile);
		
		companyDao.upd(company);
	}
    /**
     * 获取企业列表
     */
	@Override
	public PageView<CompanyView> list(String name, Integer start, Integer limit) {
		List<CompanyView> companyViews=companyDao.list(name, start, limit);
		PageView<CompanyView> pageViews=new PageView<>(companyDao.getAll().size(), companyViews);
		
		return pageViews;
	}

	@Override
	public CompanyView get(Integer memberId) {
		return get(memberDao.get(memberId));
	}

}
