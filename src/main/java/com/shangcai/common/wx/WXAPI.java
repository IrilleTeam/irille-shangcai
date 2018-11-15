package com.shangcai.common.wx;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.irille.core.controller.JsonWriter;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.common.FileDownload;
import com.shangcai.common.OssUtil;
import com.shangcai.common.http.HttpUtil;
import com.shangcai.entity.common.Member;
import com.shangcai.view.wx.AccessTokenView;
import com.shangcai.view.wx.SessionView;
import com.shangcai.view.wx.WxView;

import irille.util.MD5Util;
import lombok.Getter;

@Component
@PropertySource("classpath:wxconfig.properties")
@Getter
public class WXAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(WXAPI.class);
	
	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.secret}")
	private String secret;

	private static final String sns_jscode2session_url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code"; // GET 请求
	/**
	 * 获取小程序码，适用于需要的码数量极多的业务场景。通过该接口生成的小程序码，永久有效，数量暂无限制
	 */
	private static final String wxa_getwxacodeunlimit_url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN"; // POST 请求
	
	/**
	 * 获取小程序 access_token
	 */
	private static final String cgi_bin_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"; // GET 请求
	
	@Autowired
	private HttpUtil httpUtil;
	
	public SessionView code2session(String code) {
		return requestByGet(sns_jscode2session_url.replace("APPID", appid).replace("SECRET", secret).replace("JSCODE", code), SessionView.class);
	}
	
	/**
	 * 获取小程序码，适用于需要的码数量极多的业务场景
	 * 
 	 * @param access_token 接口调用凭证
	 * @param scene 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
	 * @param page 必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
	 * @param width 二维码的宽度，默认为 430px，最小 280px，最大 1280px
	 * @param auto_color 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
	 * @param line_color auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示，默认全 0
	 * @param is_hyaline 是否需要透明底色，为 true 时，生成透明底色的小程序码，默认 false
	 */
	public String getWXACodeUnlimit(String accessToken, String scene, String page) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("scene", scene);
			params.put("page", page);
			String s_params = JsonWriter.oMapper.writeValueAsString(params);
			StringEntity entity = new StringEntity(s_params);
			
			String url = wxa_getwxacodeunlimit_url.replace("ACCESS_TOKEN", accessToken);
			
			CloseableHttpClient httpClient = null;
			BufferedReader reader = null;
			DataInputStream dataInputStream = null;
			FileOutputStream fileOutputStream = null;
			try {
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(entity);
				logger.info("发起httpPost请求:{}", url);
				CloseableHttpResponse response = HttpClients.createDefault().execute(httpPost);
				logger.info("response.content-type:{}", response.getFirstHeader("content-type"));
				if(response.getFirstHeader("content-type").getValue().indexOf("json") == -1) {
					//成功
					File temp = File.createTempFile(FileDownload.default_prefix, "jpg");
					fileOutputStream = new FileOutputStream(temp);
					ByteArrayOutputStream output = new ByteArrayOutputStream();

					byte[] buffer = new byte[1024];
					int length;

					InputStream input = response.getEntity().getContent();
					dataInputStream = new DataInputStream(input);
					while ((length = dataInputStream.read(buffer)) > 0) {
						output.write(buffer, 0, length);
					}
					fileOutputStream.write(output.toByteArray());
					return OssUtil.upload(Member.class, MD5Util.getMD5(temp)+".jpg", temp);
				} else {
					//失败
					InputStream input = response.getEntity().getContent();
					reader = new BufferedReader(new InputStreamReader(input, Charset.forName("utf-8")));
					StringBuilder sb = new StringBuilder();
					String buffer = null;
					while ((buffer = reader.readLine()) != null) {
						sb.append(buffer);
					}
					logger.info("回传内容:{}", sb.toString());
					
					try {
						WxView view = JsonWriter.oMapper.readValue(sb.toString(), WxView.class);
						throw new WebMessageException(ReturnCode.third_unknow, view.getErrMsg());
					} catch (IOException e) {
						logger.error("微信api io异常", e);
						throw new WebMessageException(ReturnCode.third_unknow, "微信服务异常");
					}
				}
			} finally {
				if (httpClient != null)
					httpClient.close();
				if (reader != null)
					reader.close();
				if (fileOutputStream != null)
					fileOutputStream.close();
				if (dataInputStream != null)
					dataInputStream.close();
			}
		} catch (IOException e) {
			logger.error("微信api io异常", e);
			throw new WebMessageException(ReturnCode.third_unknow, "微信服务异常");
		}
	}
	
	public String getAccessToken() {
		AccessTokenView view = requestByGet(cgi_bin_token_url.replace("APPID", appid).replace("APPSECRET", secret), AccessTokenView.class);
		return view.getAccess_token();
	}
	
	private <T extends WxView> T requestByGet(String url, Class<T> viewClass) {
		try {
			String result = httpUtil.doGet(url);
			T view = JsonWriter.oMapper.readValue(result, viewClass);
			if (view.getErrcode() == null || view.getErrcode() == 0) {
				return view;
			} else
				throw new WebMessageException(ReturnCode.third_unknow, view.getErrMsg());
		} catch (IOException e) {
			logger.error("微信api io异常", e);
			throw new WebMessageException(ReturnCode.third_unknow, "微信服务异常");
		}
	}
	
}
