package com.shangcai.common;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.irille.core.web.exception.ReturnCode;
import com.irille.core.web.exception.WebMessageException;

public class FileDownload {
	
//	private static final String default_image_suffix = "jpg";
	
	public static final String default_prefix = "shangcai";
	
	private static final String[] image_suffix_list = { "jpg", "jpeg", "png", "bmp", "gif" };

	public static File toTemp(String url) throws IOException {
		DataInputStream dataInputStream = null;
		FileOutputStream fileOutputStream = null;
		String suffix = getSuffix(url);
		if(suffix == null)
			throw new WebMessageException(ReturnCode.valid_illegal, "不支持的图片格式");
		File temp = File.createTempFile(default_prefix, suffix);
		try {
			dataInputStream = new DataInputStream(new URL(url).openStream());

			fileOutputStream = new FileOutputStream(temp);
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			fileOutputStream.write(output.toByteArray());
		} finally {
			dataInputStream.close();
			fileOutputStream.close();
		}
		return temp;
	}

	public static String getSuffix(String url) {
		if (url.lastIndexOf(".") != -1) {
			String suffix = url.substring(url.lastIndexOf(".") + 1, url.length());
			for (String s : image_suffix_list) {
				if (suffix.equalsIgnoreCase(s))
					return s;
			}
		}
		return null;
	}
}
