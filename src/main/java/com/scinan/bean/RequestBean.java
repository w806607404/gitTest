package com.scinan.bean;

import java.io.Serializable;

import com.scinan.mybatis.support.sql.Conds;

/**
 * 
 * @project datacenter
 * @class com.scinan.bean.RequestBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月7日
 * @description
 */
public class RequestBean implements Serializable{

	private static final long serialVersionUID = -7109206263055739220L;

	private int limit;
	
	private int offset;
	
	private String order;
	
	private String other;
	
	private String search;
	
	private String sort;
	
	private Conds conds_;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Conds getConds_() {
		return conds_;
	}

	public void setConds_(Conds conds_) {
		this.conds_ = conds_;
	}

	
}
