package com.shangcai.view.wx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionView extends WxView {

	private String openid;
	private String session_key;
	private String unionid;
}
