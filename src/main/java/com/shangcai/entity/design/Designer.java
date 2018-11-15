package com.shangcai.entity.design;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.ColumnTemplate.OSex;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;
import com.shangcai.entity.common.Member;

public class Designer extends Entity {

	public static final Table<Designer> table = TableFactory.entity(Designer.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		DESIGNER_NAME(ColumnTemplate.STR__50_NULL.showName("设计师名称")),
		SEX(ColumnTemplate.SEX),
		AGE(ColumnTemplate.SHORT_NULL.showName("年龄")),
		WORKING_YEARS(ColumnTemplate.SHORT_NULL.showName("工作年限")),
		PERSONAL_PROFILE(ColumnTemplate.STR__200_NULL.showName("个人简介")),
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
	private String designerName; // 设计师名称 VARCHAR(50)<null>
	private Byte sex; // 性别<OSex> TINYINT(4)
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
	private Short age; // 年龄 SMALLINT(6)<null>
	private Short workingYears; // 工作年限 SMALLINT(6)<null>
	private String personalProfile; // 个人简介 VARCHAR(200)<null>
	private Integer member; // 用户<表主键:Member> INT(11)

	@Override
	public Designer init() {
		super.init();
		designerName = null; // 设计师名称 VARCHAR(50)
		sex = OSex.UNKNOW.getLine().getKey(); // 性别<OSex> TINYINT(4)
		age = null; // 年龄 SMALLINT(6)
		workingYears = null; // 工作年限 SMALLINT(6)
		personalProfile = null; // 个人简介 VARCHAR(200)
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
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public Byte getSex() {
		return sex;
	}
	public void setSex(Byte sex) {
		this.sex = sex;
	}
	public OSex gtSex() {
		return (OSex)(OSex.UNKNOW.getLine().get(sex));
	}
	public void stSex(OSex sex) {
		this.sex = sex.getLine().getKey();
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public Short getWorkingYears() {
		return workingYears;
	}
	public void setWorkingYears(Short workingYears) {
		this.workingYears = workingYears;
	}
	public String getPersonalProfile() {
		return personalProfile;
	}
	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
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
