package com.scinan.iot.s1000.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class AppUpdateBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String ctype;
	private String os; 
	private String show_version;
	private String version;
	private String content;
	private String url;
	private String md5;
	private Long file_size;
	private Integer utype;
	private Integer status;
	private String user_id;
	private Date update_time;
	private Long app_key;
	private String account_name;
	
	private String factory_name;
	
	private String app_name;
	
    /**
     * @return 返回 id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param 对id进行赋值
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return 返回 ctype
     */
    public String getCtype() {
        return ctype;
    }
    /**
     * @param 对ctype进行赋值
     */
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
    /**
     * @return 返回 os
     */
    public String getOs() {
        return os;
    }
    /**
     * @param 对os进行赋值
     */
    public void setOs(String os) {
        this.os = os;
    }
    /**
     * @return 返回 show_version
     */
    public String getShow_version() {
        return show_version;
    }
    /**
     * @param 对show_version进行赋值
     */
    public void setShow_version(String show_version) {
        this.show_version = show_version;
    }
    /**
     * @return 返回 version
     */
    public String getVersion() {
        return version;
    }
    /**
     * @param 对version进行赋值
     */
    public void setVersion(String version) {
        this.version = version;
    }
    /**
     * @return 返回 content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param 对content进行赋值
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return 返回 url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return 返回 md5
     */
    public String getMd5() {
        return md5;
    }
    /**
     * @param 对md5进行赋值
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    /**
     * @return 返回 file_size
     */
    public Long getFile_size() {
        return file_size;
    }
    /**
     * @param 对file_size进行赋值
     */
    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }
    /**
     * @return 返回 utype
     */
    public Integer getUtype() {
        return utype;
    }
    /**
     * @param 对utype进行赋值
     */
    public void setUtype(Integer utype) {
        this.utype = utype;
    }
    /**
     * @return 返回 status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param 对status进行赋值
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return 返回 user_id
     */
    public String getUser_id() {
        return user_id;
    }
    /**
     * @param 对user_id进行赋值
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    /**
     * @return 返回 update_time
     */
    public Date getUpdate_time() {
        return update_time;
    }
    /**
     * @param 对update_time进行赋值
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
    public Long getApp_key() {
        return app_key;
    }
    public void setApp_key(Long app_key) {
        this.app_key = app_key;
    }

    public String getFactory_name() {
		return factory_name;
	}
	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	@Override
	public String toString() {
		return "AppUpdateBean [id=" + id + ", ctype=" + ctype + ", os=" + os
				+ ", show_version=" + show_version + ", version=" + version
				+ ", content=" + content + ", url=" + url + ", md5=" + md5
				+ ", file_size=" + file_size + ", utype=" + utype + ", status="
				+ status + ", user_id=" + user_id + ", update_time="
				+ update_time + ", app_key=" + app_key + ", account_name="
				+ account_name + ", factory_name=" + factory_name
				+ ", app_name=" + app_name + "]";
	}
	
}
