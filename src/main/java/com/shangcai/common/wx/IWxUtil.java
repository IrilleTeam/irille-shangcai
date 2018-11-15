package com.shangcai.common.wx;

public interface IWxUtil {

	/**
	 * 登录凭证校验接口
	 * 
	 * 通过 appid + appsecret + code
	 * 获取session_key + open_id 等信息
	 * @param code
	 * @return
	 * @author Jianhua Ying
	 */
	public String fetchSessionKey(String code);
	
	/**
	 * 通过解密算法在encryptedData中获取用户信息
	 * 
	 * @param encryptedData
	 * @param iv
	 * @param sessionKey
	 * @return
	 * @author Jianhua Ying
	 */
	public String decodeEncryptedData(String encryptedData, String iv, String sessionKey);
}
