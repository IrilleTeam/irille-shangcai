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

public class Works extends Entity {

	public static final Table<Works> table = TableFactory.entity(Works.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		TYPE(ColumnFactory.opt(Type.design).showName("作品类型")),
		TITLE(ColumnTemplate.STR__50.showName("标题")),
		COVER_PIC(ColumnTemplate.IMG.nullable(false).showName("封面图片")),
		DESCRIPTION(ColumnTemplate.STR__1000_NULL.showName("说明")),
		PV_COUNT(ColumnTemplate.INT__11_ZERO.showName("浏览量")),
		LIKES_COUNT(ColumnTemplate.INT__11_ZERO.showName("点赞数")),
		COMMENT_COUNT(ColumnTemplate.INT__11_ZERO.showName("评论数")),
		STATUS(ColumnFactory.opt(Status.confirm).showName("审核状态")),
		REASON_UNAPPR(ColumnTemplate.STR__50_NULL.showName("下架原因")),
		MEMBER(ColumnFactory.manyToOne(Member.class).showName("用户")),
		MEMBER_NAME(ColumnTemplate.STR.length(25).showName("用户名字").comment("用户名字, 冗余字段")),
		CATEGROY(ColumnFactory.manyToOne(Category.T.PKEY).showName("所属分类")),
		CREATED_TIME(ColumnTemplate.TIME.showName("发布时间")),
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
		design(1,"模板"), article(2,"文章");
		public static final String NAME="作品类型";
		private EnumLine _line;
		private Type(int key, String name) {_line=new EnumLine(this,key,name);	}
		@Override
		public EnumLine getLine() {
			return _line;
		}
	}
	
	public enum Status implements IEnumOpt{
		confirm(1,"通过"), waiting(0,"待审核"), failure(-1,"失败");
		public static final String NAME="审核状态";
		private EnumLine _line;
		private Status(int key, String name) {_line=new EnumLine(this,key,name);	}
		@Override
		public EnumLine getLine() {
			return _line;
		}
	}
	
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private Byte type; // 作品类型<Type> TINYINT(4)
	// design:1,模板
	// article:2,文章
	private String title; // 标题 VARCHAR(50)
	private String coverPic; // 封面图片 VARCHAR(200)<null>
	private String description; // 说明 VARCHAR(1000)<null>
	private Integer pvCount; // 浏览量 INT(11)
	private Integer likesCount; // 点赞数 INT(11)
	private Integer commentCount; // 评论数 INT(11)
	private Byte status; // 审核状态<Status> TINYINT(4)
	// confirm:1,通过
	// waiting:0,待审核
	// failure:-1,失败
	private String reasonUnappr; // 下架原因 VARCHAR(50)<null>
	private Integer member; // 用户<表主键:Member> INT(11)
	private String memberName; // 用户名字 VARCHAR(25)
	private Integer categroy; // 所属分类<表主键:Category> INT(11)
	private Date createdTime; // 发布时间 DATETIME(0)

	@Override
	public Works init() {
		super.init();
		type = Type.design.getLine().getKey(); // 作品类型<Type> TINYINT(4)
		title = null; // 标题 VARCHAR(50)
		coverPic = null; // 封面图片 VARCHAR(200)
		description = null; // 说明 VARCHAR(1000)
		pvCount = 0; // 浏览量 INT(11)
		likesCount = 0; // 点赞数 INT(11)
		commentCount = 0; // 评论数 INT(11)
		status = Status.confirm.getLine().getKey(); // 审核状态<Status> TINYINT(4)
		reasonUnappr = null; // 下架原因 VARCHAR(50)
		member = null; // 用户 INT(11)
		memberName = null; // 用户名字 VARCHAR(25)
		categroy = null; // 所属分类 INT(11)
		createdTime = null; // 发布时间 DATETIME(0)
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
		return (Type)(Type.design.getLine().get(type));
	}
	public void stType(Type type) {
		this.type = type.getLine().getKey();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPvCount() {
		return pvCount;
	}
	public void setPvCount(Integer pvCount) {
		this.pvCount = pvCount;
	}
	public Integer getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(Integer likesCount) {
		this.likesCount = likesCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Status gtStatus() {
		return (Status)(Status.confirm.getLine().get(status));
	}
	public void stStatus(Status status) {
		this.status = status.getLine().getKey();
	}
	public String getReasonUnappr() {
		return reasonUnappr;
	}
	public void setReasonUnappr(String reasonUnappr) {
		this.reasonUnappr = reasonUnappr;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getCategroy() {
		return categroy;
	}
	public void setCategroy(Integer categroy) {
		this.categroy = categroy;
	}
	public Category gtCategroy() {
		return selectFrom(Category.class, getCategroy());
	}
	public void stCategroy(Category categroy) {
		this.categroy = categroy.getPkey();
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
