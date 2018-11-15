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

public class FollowRelation extends Entity {

	public static final Table<FollowRelation> table = TableFactory.entity(FollowRelation.class).column(T.values()).index(true, T.MEMBER, T.FOLLOWER).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		MEMBER(ColumnFactory.manyToOne(Member.class).showName("用户")),
		FOLLOWER(ColumnFactory.manyToOne(Member.class).showName("粉丝")),
		CREATED_TIME(ColumnTemplate.TIME.showName("关注时间")),
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
	private Integer member; // 用户<表主键:Member> INT(11)
	private Integer follower; // 粉丝<表主键:Member> INT(11)
	private Date createdTime; // 关注时间 DATETIME(0)

	@Override
	public FollowRelation init() {
		super.init();
		member = null; // 用户 INT(11)
		follower = null; // 粉丝 INT(11)
		createdTime = null; // 关注时间 DATETIME(0)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
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
	public Integer getFollower() {
		return follower;
	}
	public void setFollower(Integer follower) {
		this.follower = follower;
	}
	public Member gtFollower() {
		return selectFrom(Member.class, getFollower());
	}
	public void stFollower(Member follower) {
		this.follower = follower.getPkey();
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
