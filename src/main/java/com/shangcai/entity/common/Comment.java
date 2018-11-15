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

public class Comment extends Entity {

	public static final Table<Comment> table = TableFactory.entity(Comment.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		WORKS(ColumnFactory.manyToOne(Works.class).showName("作品")),
		REPLY_TO(ColumnFactory.manyToOne(Member.class).nullable(true).showName("被评论人")),
		REPLY_TO_NAME(ColumnTemplate.STR.length(25).nullable(true).showName("被评论人名称").comment("被评论人名称 冗余字段")),
		MEMBER(ColumnFactory.manyToOne(Member.class).showName("评论人")),
		MEMBER_NAME(ColumnTemplate.STR.length(25).showName("评论人名称").comment("评论人名称 冗余字段")),
		MEMBER_HEAD_PIC(ColumnTemplate.STR__200.nullable(true).showName("评论人头像").comment("评论人头像 冗余字段")),
		CONTENTS(ColumnTemplate.STR__500.showName("评论内容")),
		CREATED_TIME(ColumnTemplate.TIME.showName("评论时间")),
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
	private Integer works; // 作品<表主键:Works> INT(11)
	private Integer replyTo; // 被评论人<表主键:Member> INT(11)<null>
	private String replyToName; // 被评论人名称 VARCHAR(25)<null>
	private Integer member; // 评论人<表主键:Member> INT(11)
	private String memberName; // 评论人名称 VARCHAR(25)
	private String memberHeadPic; // 评论人头像 VARCHAR(200)<null>
	private String contents; // 评论内容 VARCHAR(500)
	private Date createdTime; // 评论时间 DATETIME(0)

	@Override
	public Comment init() {
		super.init();
		works = null; // 作品 INT(11)
		replyTo = null; // 被评论人 INT(11)
		replyToName = null; // 被评论人名称 VARCHAR(25)
		member = null; // 评论人 INT(11)
		memberName = null; // 评论人名称 VARCHAR(25)
		memberHeadPic = null; // 评论人头像 VARCHAR(200)
		contents = null; // 评论内容 VARCHAR(500)
		createdTime = null; // 评论时间 DATETIME(0)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public Integer getWorks() {
		return works;
	}
	public void setWorks(Integer works) {
		this.works = works;
	}
	public Works gtWorks() {
		return selectFrom(Works.class, getWorks());
	}
	public void stWorks(Works works) {
		this.works = works.getPkey();
	}
	public Integer getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(Integer replyTo) {
		this.replyTo = replyTo;
	}
	public Member gtReplyTo() {
		return selectFrom(Member.class, getReplyTo());
	}
	public void stReplyTo(Member replyTo) {
		this.replyTo = replyTo.getPkey();
	}
	public String getReplyToName() {
		return replyToName;
	}
	public void setReplyToName(String replyToName) {
		this.replyToName = replyToName;
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
	public String getMemberHeadPic() {
		return memberHeadPic;
	}
	public void setMemberHeadPic(String memberHeadPic) {
		this.memberHeadPic = memberHeadPic;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
