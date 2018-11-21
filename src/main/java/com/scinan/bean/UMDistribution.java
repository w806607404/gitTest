package com.scinan.bean;

import java.util.List;

public class UMDistribution {
	private String name;
	private String type = "map";
	private String mapType = "china";
	private boolean roam = false;
	public String getName() {
		return name;
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
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	public boolean isRoam() {
		return roam;
	}
	public void setRoam(boolean roam) {
		this.roam = roam;
	}
	public ItemStyle getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(ItemStyle itemStyle) {
		this.itemStyle = itemStyle;
	}
	public List<AddressNumber> getData() {
		return data;
	}
	public void setData(List<AddressNumber> data) {
		this.data = data;
	}
	private ItemStyle itemStyle;
	private List<AddressNumber> data;
}
