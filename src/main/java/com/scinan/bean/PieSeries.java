package com.scinan.bean;

import java.util.List;

public class PieSeries {
	
	private String name;
	
	private String type;
	
	private String radius;
	
	private List<String> center;

	private List<AddressNumber> data;
	
	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public List<String> getCenter() {
		return center;
	}

	public void setCenter(List<String> center) {
		this.center = center;
	}

	public String getName() {
		return name;
	}

	public List<AddressNumber> getData() {
		return data;
	}

	public void setData(List<AddressNumber> data) {
		this.data = data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	class Datas{
		
		private String name;
		
		private Integer value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	
	
	
}
 