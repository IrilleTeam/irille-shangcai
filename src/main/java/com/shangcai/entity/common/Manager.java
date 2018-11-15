package com.shangcai.entity.common;
import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;

public class Manager extends Entity{
	public static final Table<Manager> table = TableFactory.entity(Manager.class).column(T.values()).index(true, Manager.T.LOGIN_NAME).create();
	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		LOGIN_NAME(ColumnTemplate.STR__20.showName("登录账号")),
		NAME(ColumnTemplate.STR__20.showName("名字")),
		PASSWORD(ColumnTemplate.STR__100.showName("登录密码")),;
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
	private String loginName; // 登录账号 VARCHAR(20)
	private String name; // 名字 VARCHAR(20)
	private String password; // 登录密码 VARCHAR(100)

	@Override
	public Manager init() {
		super.init();
		loginName = null; // 登录账号 VARCHAR(20)
		name = null; // 名字 VARCHAR(20)
		password = null; // 登录密码 VARCHAR(100)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<

}
