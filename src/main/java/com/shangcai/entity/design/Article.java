package com.shangcai.entity.design;

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

public class Article extends Entity {

	public static final Table<Article> table = TableFactory.entity(Article.class).column(T.values()).create();

	public enum T implements IColumnField {
		PKEY(ColumnTemplate.PKEY),
		PICTURES(ColumnTemplate.STR__2000_NULL.showName("图片地址")),
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
	private String pictures; // 图片地址 VARCHAR(2000)<null>
	private Integer works; // 所属作品<表主键:Works> INT(11)

	@Override
	public Article init() {
		super.init();
		pictures = null; // 图片地址 VARCHAR(2000)
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
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
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
