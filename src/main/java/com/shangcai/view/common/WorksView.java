package com.shangcai.view.common;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorksView implements BaseView {
    private Integer pkey;
	private String publisher;
	private String worksName;
	private String category;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale="zh",timezone="GMT+8")
	private Date createdTime;
	private Integer likeRelation;
	private Integer browsingVolume;
	private Byte status;
	private Byte worksStatus;
	private BigDecimal samplingPrice;

}
