package com.shangcai.entity.common;

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

public class Category extends Entity {

	public static final Table<Category> table = TableFactory.entity(Category.class).column(T.values()).create();
	
	public enum T implements IColumnField {		
		PKEY(ColumnTemplate.PKEY),
		NAME(ColumnTemplate.STR__100.showName("名字")),
		UP(ColumnFactory.manyToOne(Category.T.PKEY).nullable(true).showName("上级分类")),
		TYPE(ColumnFactory.opt(Type.company).showName("分类类型")),
		SORT(ColumnTemplate.INT__11.showName("排序")),
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
	private String name; // 名字 VARCHAR(100)
	private Integer up; // 上级分类<表主键:Category> INT(11)<null>
	private Byte type; // 分类类型<Type> TINYINT(4)
	// company:1,鞋企
	// designer:2,设计师
	private Integer sort; // 排序 INT(11)

	@Override
	public Category init() {
		super.init();
		name = null; // 名字 VARCHAR(100)
		up = null; // 上级分类 INT(11)
		type = Type.company.getLine().getKey(); // 分类类型<Type> TINYINT(4)
		sort = null; // 排序 INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUp() {
		return up;
	}
	public void setUp(Integer up) {
		this.up = up;
	}
	public Category gtUp() {
		return selectFrom(Category.class, getUp());
	}
	public void stUp(Category up) {
		this.up = up.getPkey();
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
