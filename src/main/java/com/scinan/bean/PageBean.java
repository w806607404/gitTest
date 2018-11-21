package com.scinan.bean;

import java.util.List;

/**
 * 返回分页结果封装类
 * @project datacenter
 * @class com.scinan.bean.PageBean
 * @copyright www.scinan.com
 * @author Jesse
 * @date 2016年7月7日
 * @description
 */
public class PageBean<T> {
	
	@Override
	public String toString() {
		return "PageBean [total=" + total + ", rows=" + rows + "]";
	}

	private Integer total;
	private List<T> rows;// 当前页数据

	public PageBean() {
	}
	
	public PageBean(Integer total,List<T> rows){
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
