package com.scinan.bean;

public class AddressNumber {
	private String name;
	private int value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	public AddressNumber(){
		
	}
	public AddressNumber(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
}
