package com.scinan.iot.s8000temp.dao.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * SuggestionData对象.
 * @author yangkun
 *
 */
public class SuggestionData implements Serializable{
	private static final long serialVersionUID = -6077988800839788506L;

    
    /**
     * .
     */
    private Long id;
    
    /**
     * 类型ID(t_suggestion_type:id).
     */
    private Integer type_id;
    
    /**
     * 厂商ID.
     */
    private String company_id;
    
    /**
     * 商品ID.
     */
    private String product_id;
    
    /**
     * 反馈用户ID.
     */
    private Long user_id;
    
    /**
     * 联系电话.
     */
    private String mobile;
    
    /**
     * 电子邮箱.
     */
    private String email;
    
    
    /**
     * 联系方式
     */
    private String contact;
    
    /**
     * 反馈意见.
     */
    private String content;
    
    /**
     * .
     */
    private Date create_time;


    
    public Long getId() {
        return this.id;
    }	
  
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getType_id() {
        return this.type_id;
    }	
  
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }
    
    public String getCompany_id() {
        return this.company_id;
    }	
  
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    
    public String getProduct_id() {
        return this.product_id;
    }	
  
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    public Long getUser_id() {
        return this.user_id;
    }	
  
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    
    public String getMobile() {
        return this.mobile;
    }	
  
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return this.email;
    }	
  
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContent() {
        return this.content;
    }	
  
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }	
  
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
