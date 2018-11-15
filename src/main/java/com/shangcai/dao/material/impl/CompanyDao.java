package com.shangcai.dao.material.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangcai.dao.BaseDao;
import com.shangcai.dao.material.ICompanyDao;
import com.shangcai.entity.common.Member;
import com.shangcai.entity.material.Company;
import com.shangcai.entity.material.Company.T;
import com.shangcai.view.material.CompanyView;

@Repository
public class CompanyDao extends BaseDao<Company>   implements ICompanyDao {
	
	/**
	 * 获取鞋企业列表
	 * @return
	 */
	@Override
	public   List<CompanyView>  list(String name,Integer start,Integer  limit){
		List<CompanyView> companyViewList=new ArrayList<CompanyView>();
		if(null!=name)
			companyViewList=select(Member.T.PKEY.as("pkey"),Company.T.CONTACT.as("contact"),Member.T.NAME.as("name"),Member.T.CONTACT_NUMBER.as("contactNumber"),Company.T.ADDRESS.as("address")).from(Company.class).leftJoin(Member.class, Member.T.PKEY,Company.T.MEMBER).where(Member.T.NAME.eq(name)).limit(start, limit).queryList(CompanyView.class);
		else
			companyViewList=select(Member.T.PKEY.as("pkey"),Company.T.CONTACT.as("contact"),Member.T.NAME.as("name"),Member.T.CONTACT_NUMBER.as("contactNumber"),Company.T.ADDRESS.as("address")).from(Company.class).leftJoin(Member.class, Member.T.PKEY,Company.T.MEMBER).limit(start, limit).queryList(CompanyView.class);
		return companyViewList;
	}

	@Override
	public Company get(Integer memberId) {
		return selectFrom(Company.class).where(T.MEMBER.eq(memberId)).query();
	}

	@Override
	public void upd(Company company) {
		company.upd();
	}

	@Override
	public List<Company> getAll() {
		return selectFrom(Company.class).queryList();
	}

}
