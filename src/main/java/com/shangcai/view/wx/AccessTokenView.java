package com.shangcai.view.wx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenView extends WxView {

	private String access_token;
	private Long expires_in;
}
