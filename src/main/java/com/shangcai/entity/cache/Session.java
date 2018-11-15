package com.shangcai.entity.cache;

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
import com.shangcai.entity.common.Member;

public class Session extends Entity {

	public static final Table<Session> table = TableFactory.entity(Session.class).column(T.values()).create();

	public enum T implements IColumnField {
		TOKEN(ColumnTemplate.STR__200.primary(true).showName("token")),
		MEMBER(ColumnFactory.manyToOne(Member.class).showName("用户")),
		ACCESSD_TIME(ColumnTemplate.TIME.showName("最新访问时间")),
		TIMEOUT(ColumnTemplate.INT__11.showName("超时时间(分钟)").comment("超时时间(分钟) -1:永不超时")),
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
	private String token; // token VARCHAR(200)
	private Integer member; // 用户<表主键:Member> INT(11)
	private Date accessdTime; // 最新访问时间 DATETIME(0)
	private Integer timeout; // 超时时间(分钟) INT(11)

	@Override
	public Session init() {
		super.init();
		token = null; // token VARCHAR(200)
		member = null; // 用户 INT(11)
		accessdTime = null; // 最新访问时间 DATETIME(0)
		timeout = null; // 超时时间(分钟) INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public Date getAccessdTime() {
		return accessdTime;
	}
	public void setAccessdTime(Date accessdTime) {
		this.accessdTime = accessdTime;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
