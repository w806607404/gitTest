package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息实体类
 * 
 * @project datacenter
 * @class com.scinan.iot.s1000.dao.domain.ArticleInfoBean
 * @copyright www.scinan.com
 * @author Zola
 * @date 2016年9月1日
 * @description
 */
public class ArticleInfoBean implements Serializable{

	private static final long serialVersionUID = 7124991488950527669L;
	
	private Long id;
	
	private Integer article_type;
	
	private String source;
	
	private String title;
	
	private String thumb_url;
	
	private String detail_url;
	
	private String content;
	
	private Integer state;
	
	private Integer share_count;
	
	private String company_id;
	
	private Integer device_type;
	
	private Long app_key;
	
	private Integer user_id;
	
	private Date create_time;
	
	private Date update_time;
	
	/*===================业务属性====================*/
	
	private String app_name;
	
	private AppInfoBean appInfoBean;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getArticle_type() {
		return article_type;
	}

	public void setArticle_type(Integer article_type) {
		this.article_type = article_type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getShare_count() {
		return share_count;
	}

	public void setShare_count(Integer share_count) {
		this.share_count = share_count;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public Long getApp_key() {
		return app_key;
	}

	public void setApp_key(Long app_key) {
		this.app_key = app_key;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public AppInfoBean getAppInfoBean() {
		return appInfoBean;
	}

	public void setAppInfoBean(AppInfoBean appInfoBean) {
		this.appInfoBean = appInfoBean;
	}

	@Override
	public String toString() {
		return "ArticleInfoBean [id=" + id + ", article_type=" + article_type
				+ ", source=" + source + ", title=" + title + ", thumb_url="
				+ thumb_url + ", detail_url=" + detail_url + ", content="
				+ content + ", state=" + state + ", share_count=" + share_count
				+ ", company_id=" + company_id + ", device_type=" + device_type
				+ ", app_key=" + app_key + ", user_id=" + user_id
				+ ", create_time=" + create_time + ", update_time="
				+ update_time + ", app_name=" + app_name + ", appInfoBean="
				+ appInfoBean + "]";
	}
	
}