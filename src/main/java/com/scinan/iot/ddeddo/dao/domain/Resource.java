package com.scinan.iot.ddeddo.dao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体类
 * @project vtldatacenter
 * @class com.scinan.iot.vitalong.dao.domain.VtlResource
 * @copyright www.scinan.com
 * @author Kim
 * @date 2016年12月07日
 * @description
 */
public class Resource implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = -4029501174812832303L;
	/**
      * 主键id
      */
	private Long res_id;
	/**
	 * 菜单名称
	 */
	private String res_name;
	
	/**
	 * 国际化Key
	 */
	private String res_key;
	
	/**
	 * 上级ID
	 */
	private Integer parent_id;
	
	
	/**
	 * 类型(0:一级菜单;1:二级菜单;2:按钮)
	 */
	private Integer res_type;
	
	/**
	 * 跳转URL
	 */
	private String res_url;
	
	/**
	 * 图标
	 */
	private String icon;
	
	
	/**
	 * 描述
	 */
	private String description;
	
	
	private List<Resource> nodes = new ArrayList<Resource>();
	
	private List<String> tags = new ArrayList<String>();
	
	public Resource(){
		tags.add("0");
	}

	public Long getRes_id() {
		return res_id;
	}

	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getRes_key() {
		return res_key;
	}

	public void setRes_key(String res_key) {
		this.res_key = res_key;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getRes_type() {
		return res_type;
	}

	public void setRes_type(Integer res_type) {
		this.res_type = res_type;
	}

	public String getRes_url() {
		return res_url;
	}

	public void setRes_url(String res_url) {
		this.res_url = res_url;
	}

	public List<Resource> getNodes() {
		return nodes;
	}

	public void setNodes(List<Resource> nodes) {
		this.nodes = nodes;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}