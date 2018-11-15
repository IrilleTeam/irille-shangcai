package com.shangcai.entity.common;

import java.util.Date;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public class Member extends Entity {

	public static final Table<Member> table = TableFactory.entity(Member.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		TYPE(ColumnFactory.opt(Type.company).showName("用户类型")),
		NAME(ColumnTemplate.STR.length(25).showName("名字").comment("最少一个字符，最多25个字符；不能包含特殊字符。")),
		HEAD_PIC(ColumnTemplate.STR__200.nullable(true).showName("头像")),
		CONTACT_NUMBER(ColumnTemplate.STR__200_NULL.nullable(true).showName("联系电话")),
		QRCODE(ColumnTemplate.STR__200.nullable(true).showName("我的二维码")),
		PROVINCE(ColumnFactory.manyToOne(Province.T.PKEY).nullable(true).showName("省份")),
		CITY(ColumnFactory.manyToOne(City.T.PKEY).nullable(true).showName("城市")),
		PV_COUNT(ColumnTemplate.INT__11_ZERO.showName("浏览量").comment("浏览量,需要加上缓存数据才是实时数据")),
		WORKS_COUNT(ColumnTemplate.INT__11_ZERO.showName("作品数").comment("作品数,冗余字段")),
		FOLLOWING_COUNT(ColumnTemplate.INT__11_ZERO.showName("关注数").comment("关注数,冗余字段")),
		FOLLOWER_COUNT(ColumnTemplate.INT__11_ZERO.showName("粉丝数").comment("粉丝数,冗余字段")),
		
		WX_OPEN_ID(ColumnTemplate.STR__200_NULL.showName("微信openid")),
		WX_UNION_ID(ColumnTemplate.STR__200_NULL.showName("微信unionid")),
		
		CREATED_TIME(ColumnTemplate.TIME.showName("注册时间")),
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
	
	public enum Type implements IEnumOpt{
		company(1,"鞋企"), designer(2,"设计师");
		public static final String NAME="用户类型";
		private EnumLine _line;
		private Type(int key, String name) {_line=new EnumLine(this,key,name);	}
		@Override
		public EnumLine getLine() {
			return _line;
		}
	}
	
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private Byte type; // 用户类型<Type> TINYINT(4)
	// company:1,鞋企
	// designer:2,设计师
	private String name; // 名字 VARCHAR(25)
	private String headPic; // 头像 VARCHAR(200)<null>
	private String contactNumber; // 联系电话 VARCHAR(200)<null>
	private String qrcode; // 我的二维码 VARCHAR(200)<null>
	private Integer province; // 省份<表主键:Province> INT(11)<null>
	private Integer city; // 城市<表主键:City> INT(11)<null>
	private Integer pvCount; // 浏览量 INT(11)
	private Integer worksCount; // 作品数 INT(11)
	private Integer followingCount; // 关注数 INT(11)
	private Integer followerCount; // 粉丝数 INT(11)
	private String wxOpenId; // 微信openid VARCHAR(200)<null>
	private String wxUnionId; // 微信unionid VARCHAR(200)<null>
	private Date createdTime; // 注册时间 DATETIME(0)

	@Override
	public Member init() {
		super.init();
		type = Type.company.getLine().getKey(); // 用户类型<Type> TINYINT(4)
		name = null; // 名字 VARCHAR(25)
		headPic = null; // 头像 VARCHAR(200)
		contactNumber = null; // 联系电话 VARCHAR(200)
		qrcode = null; // 我的二维码 VARCHAR(200)
		province = null; // 省份 INT(11)
		city = null; // 城市 INT(11)
		pvCount = 0; // 浏览量 INT(11)
		worksCount = 0; // 作品数 INT(11)
		followingCount = 0; // 关注数 INT(11)
		followerCount = 0; // 粉丝数 INT(11)
		wxOpenId = null; // 微信openid VARCHAR(200)
		wxUnionId = null; // 微信unionid VARCHAR(200)
		createdTime = null; // 注册时间 DATETIME(0)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Type gtType() {
		return (Type)(Type.company.getLine().get(type));
	}
	public void stType(Type type) {
		this.type = type.getLine().getKey();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Province gtProvince() {
		return selectFrom(Province.class, getProvince());
	}
	public void stProvince(Province province) {
		this.province = province.getPkey();
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public City gtCity() {
		return selectFrom(City.class, getCity());
	}
	public void stCity(City city) {
		this.city = city.getPkey();
	}
	public Integer getPvCount() {
		return pvCount;
	}
	public void setPvCount(Integer pvCount) {
		this.pvCount = pvCount;
	}
	public Integer getWorksCount() {
		return worksCount;
	}
	public void setWorksCount(Integer worksCount) {
		this.worksCount = worksCount;
	}
	public Integer getFollowingCount() {
		return followingCount;
	}
	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}
	public Integer getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
	public String getWxOpenId() {
		return wxOpenId;
	}
	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}
	public String getWxUnionId() {
		return wxUnionId;
	}
	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
