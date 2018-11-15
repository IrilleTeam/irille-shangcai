package com.shangcai.common.wx;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.irille.core.controller.JsonWriter;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;

import lombok.Getter;
import lombok.Setter;

/**
 * 对微信的密文进行解密
 * 
 * @author Jianhua Ying
 */
@Component
@PropertySource("classpath:wxconfig.properties")
public class WXBizDataCrypt {
	
	@PostConstruct 
	public void addProvider() {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	private static final Logger logger = LoggerFactory.getLogger(WXBizDataCrypt.class);
	
	public static final int OK = 0;
	public static final int IllegalAesKey = -41001;
	public static final int IllegalIv = -41002;
	public static final int IllegalBuffer = -41003;
	public static final int DecodeBase64Error = -41004;
	
	@Value("${wx.appid}")
	public String appid;
	
	/**
	 * 检验数据的真实性，并且获取解密后的明文.
	 * 
	 * @param encryptedData 加密的用户数据
	 * @param iv 与用户数据一同返回的初始向量
	 * @param data 解密后的原文
     *
	 * @return int 成功0，失败返回对应的错误码
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws NoSuchProviderException 
	 */
	public EncryptedData decryptData(String sessionKey, String encryptedData, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, JsonParseException, JsonMappingException, IOException, NoSuchProviderException {
		Decoder decoder = Base64.getDecoder();
		
		if (sessionKey.length() != 24) {
			logger.info("IllegalAesKey");
			throw new WebMessageException(ReturnCode.valid_illegal, "数据异常");
		}
		byte[] aesKey = decoder.decode(sessionKey);
        
		if (iv.length() != 24) {
			logger.info("IllegalIv");
			throw new WebMessageException(ReturnCode.valid_illegal, "数据异常");
		}
		
		byte[] aesIV = decoder.decode(iv);

		byte[] aesCipher = decoder.decode(encryptedData);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
		
		SecretKeySpec key = new SecretKeySpec(aesKey, "AES");
		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(aesIV));
		
		byte[] result = cipher.doFinal(aesCipher);
		
		logger.info("decryptData 解密数据: {}", new String(result));
		EncryptedData data = JsonWriter.oMapper.readValue(result, EncryptedData.class);
		
		if(data == null) {
			logger.info("IllegalBuffer");
			throw new WebMessageException(ReturnCode.valid_illegal, "数据异常");
		}
		
		if(!data.getWatermark().getAppid().equals(appid)) {
			logger.info("IllegalBuffer");
			throw new WebMessageException(ReturnCode.valid_illegal, "数据异常");
		}
		return data;
	}
	
	@Getter
	@Setter
	public static class EncryptedData {
		
		/**
		 * 用户昵称
		 */
		private String nickName; 
		/**
		 * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
		 */
		private String avatarUrl;
		/**
		 * 用户性别 
		 * 0 	未知
		 * 1 	男性
		 * 2 	女性
		 */
		private Byte gender;
		/**
		 * 用户所在国家
		 */
		private String country;
		/**
		 * 用户所在城市
		 */
		private String city;
		/**
		 * 用户所在省份
		 */
		private String province;
		/**
		 * 显示 country，province，city 所用的语言
		 * en 	英文
		 * zh_CN 	简体中文
		 * zh_TW 	繁体中文
		 */
		private String language;
		
		private String phoneNumber;
		private String purePhoneNumber;
		private String countryCode;
		private String openId;
		private String unionId;
		private Watermark watermark;
		
		@Getter
		@Setter
		public static class Watermark {
			private String appid;
			private Long timestamp;
		}
	}
}
