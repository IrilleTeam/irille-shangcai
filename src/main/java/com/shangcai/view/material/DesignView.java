package com.shangcai.view.material;

import java.math.BigDecimal;
import java.util.List;

import com.shangcai.view.common.WorksView2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignView extends WorksView2 {

	private BigDecimal samplingPrice;
	private List<ThemeView> theme;
}
