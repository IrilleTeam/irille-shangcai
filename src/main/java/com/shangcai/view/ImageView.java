package com.shangcai.view;

import irille.view.BaseView;

public class ImageView implements BaseView{
	
	private String state;//default SUCCESS
	private String name;//eg. 23156121212158163121.jpg
	private String originalName;//eg. image.jpg
	private Long size;
	private String url;
	private String type;//eg. jpg
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
