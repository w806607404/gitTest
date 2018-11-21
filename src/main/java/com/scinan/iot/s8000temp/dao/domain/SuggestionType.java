package com.scinan.iot.s8000temp.dao.domain;


import java.io.Serializable;

/**
 * SuggestionType对象.
 * @author yangkun
 *
 */
public class SuggestionType implements Serializable{
	private static final long serialVersionUID = 3389679668343911483L;

    
    /**
     * 意见类型ID.
     */
    private Integer id;
    
    /**
     * 意见类型名称.
     */
    private String name;
    
    /**
     * 英文反馈类型.
     */
    private String name_en;


    
    public Integer getId() {
        return this.id;
    }	
  
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }	
  
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName_en() {
        return this.name_en;
    }	
  
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

}
