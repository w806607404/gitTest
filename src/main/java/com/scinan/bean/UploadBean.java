package com.scinan.bean;

public class UploadBean {
	
	private Integer status;
	
	private String linkUrl;
	
	private Long length;
	
	private String hashCode;
	
	public UploadBean(){}

	public UploadBean(Integer status,String linkUrl, Long length, String hashCode) {
		this.status = status;
		this.linkUrl = linkUrl;
		this.length = length;
		this.hashCode = hashCode;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
