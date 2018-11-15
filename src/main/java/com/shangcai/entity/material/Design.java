package com.shangcai.entity.material;

import java.math.BigDecimal;

import com.irille.core.repository.orm.Column;
import com.irille.core.repository.orm.ColumnBuilder;
import com.irille.core.repository.orm.ColumnFactory;
import com.irille.core.repository.orm.ColumnTemplate;
import com.irille.core.repository.orm.Entity;
import com.irille.core.repository.orm.IColumnField;
import com.irille.core.repository.orm.IColumnTemplate;
import com.irille.core.repository.orm.Table;
import com.irille.core.repository.orm.TableFactory;
import com.shangcai.entity.common.Works;

public class Design extends Entity {

	public static final Table<Design> table = TableFactory.entity(Design.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		SAMPLING_PRICE(ColumnTemplate.AMT.showName("取样价")),
		THEMES(ColumnTemplate.JSON.showName("主题")),
		WORKS(ColumnFactory.manyToOne(Works.T.PKEY).showName("所属作品")),
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
	private BigDecimal samplingPrice; // 取样价 DECIMAL(0)
	private String themes; // 主题 JSON(0)
	private Integer works; // 所属作品<表主键:Works> INT(11)

	@Override
	public Design init() {
		super.init();
		samplingPrice = new BigDecimal(1); // 取样价 DECIMAL(0)
		themes = null; // 主题 JSON(0)
		works = null; // 所属作品 INT(11)
		return this;
	}

	// 方法------------------------------------------------
	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public BigDecimal getSamplingPrice() {
		return samplingPrice;
	}
	public void setSamplingPrice(BigDecimal samplingPrice) {
		this.samplingPrice = samplingPrice;
	}
	public String getThemes() {
		return themes;
	}
	public void setThemes(String themes) {
		this.themes = themes;
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

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
