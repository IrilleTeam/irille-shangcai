package com.shangcai.entity.material;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;
import com.shangcai.entity.common.Member;

public class Company extends Entity {

	public static final Table<Company> table = TableFactory.entity(Company.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		COMPANY_NAME(ColumnTemplate.STR__50_NULL.showName("企业名称")),
		CONTACT(ColumnTemplate.STR__100_NULL.showName("联系人")),
		ADDRESS(ColumnTemplate.STR__100_NULL.showName("企业地址")),
		ESTABLISHED_TIME(ColumnTemplate.INT__11_NULL.showName("企业成立年限")),
		PROFILE(ColumnTemplate.STR__200_NULL.showName("企业简介")),
		MEMBER(ColumnFactory.manyToOne(Member.T.PKEY).showName("用户")),
		;
		private Column column;

		T(IColumnTemplate template) {
			this.column = template.builder().create(this);
		}

		T(ColumnBuilder builder) {
			this.column = builder.create(this);
		}

		@Override
		public Column column() {
			return column;
		}

	}
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private String companyName; // 企业名称 VARCHAR(50)<null>
	private String contact; // 联系人 VARCHAR(100)<null>
	private String address; // 企业地址 VARCHAR(100)<null>
	private Integer establishedTime; // 企业成立年限 INT(11)<null>
	private String profile; // 企业简介 VARCHAR(200)<null>
	private Integer member; // 用户<表主键:Member> INT(11)

	@Override
	public Company init() {
		super.init();
		companyName = null; // 企业名称 VARCHAR(50)
		contact = null; // 联系人 VARCHAR(100)
		address = null; // 企业地址 VARCHAR(100)
		establishedTime = null; // 企业成立年限 INT(11)
		profile = null; // 企业简介 VARCHAR(200)
		member = null; // 用户 INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getEstablishedTime() {
		return establishedTime;
	}
	public void setEstablishedTime(Integer establishedTime) {
		this.establishedTime = establishedTime;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Integer getMember() {
		return member;
	}
	public void setMember(Integer member) {
		this.member = member;
	}
	public Member gtMember() {
		return selectFrom(Member.class, getMember());
	}
	public void stMember(Member member) {
		this.member = member.getPkey();
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
