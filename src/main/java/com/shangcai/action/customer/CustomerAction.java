package com.shangcai.action.customer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.irille.core.repository.orm.Entity;
import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;
import com.shangcai.action.BaseAction;
import com.shangcai.common.OssUtil;
import com.shangcai.entity.common.Member;
import com.shangcai.interceptor.ItpWxLogin;
import com.shangcai.view.ImageView;

import irille.util.MD5Util;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAction<T extends Entity, R extends Serializable> extends BaseAction<T, R> {
	private final Logger logger = LoggerFactory.getLogger(CustomerAction.class);
	
	private File file;

	public Member curMember() {
		return ItpWxLogin.getMember();
	}

	public void setMember(Member member) {
		ItpWxLogin.setMember(member);
	}
	
	public void uploadImage() throws IOException {
		try {
			write(new ImageView() {{
				setUrl("http://"+(OssUtil.cname==null?(OssUtil.bucketName+"."+OssUtil.endpoint):OssUtil.cname)+OssUtil.upload(entityClazz(), MD5Util.getMD5(file)+".jpg", file));
			}});
		} catch (IOException e) {
			throw new WebMessageException(ReturnCode.service_unknow, "上传失败请稍后再试");
		}
	}
}
