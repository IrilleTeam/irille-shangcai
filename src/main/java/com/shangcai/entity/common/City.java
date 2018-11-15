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

public class City extends Entity {

	public static final Table<City> table = TableFactory.entity(City.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		NAME(ColumnTemplate.STR__20.showName("名字")),
		PROVINCE(ColumnFactory.manyToOne(Province.class).showName("所属省份")),
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
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>

	// 实例变量定义-----------------------------------------
	private Integer pkey; // 主键 INT(11)
	private String name; // 名字 VARCHAR(20)
	private Integer province; // 所属省份<表主键:Province> INT(11)
	private Integer sort; // 排序 INT(11)

	@Override
	public City init() {
		super.init();
		name = null; // 名字 VARCHAR(20)
		province = null; // 所属省份 INT(11)
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
