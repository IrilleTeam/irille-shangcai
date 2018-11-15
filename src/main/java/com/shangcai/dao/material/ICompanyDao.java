package com.shangcai.dao.material;

import java.util.List;

import com.shangcai.entity.material.Company;
import com.shangcai.view.material.CompanyView;

public interface ICompanyDao {
	
	public void add(Company company);
	
	public   List<CompanyView>  list(String name,Integer start,Integer  limit);

	public Company get(Integer memberId);

	public void upd(Company company);
	
	public  List<Company> getAll();
}
