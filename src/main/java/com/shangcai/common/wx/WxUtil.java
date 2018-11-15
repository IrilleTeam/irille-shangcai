package com.shangcai.common.wx;

public class WxUtil implements IWxUtil {

	@Override
	public String fetchSessionKey(String code) {
		// TODO 登录凭证校验接口
		return null;
	}

	@Override
	public String decodeEncryptedData(String encryptedData, String iv, String sessionKey) {
		// TODO 通过解密算法在encryptedData中获取用户信息
		return null;
	}

}
