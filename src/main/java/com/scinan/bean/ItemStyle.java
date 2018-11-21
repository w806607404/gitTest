package com.scinan.bean;

public class ItemStyle {
	private Normal normal;
	private Emphasis emphasis;

	public Emphasis getEmphasis() {
		return emphasis;
	}

	public void setEmphasis(Emphasis emphasis) {
		this.emphasis = emphasis;
	}

	public Normal getNormal() {
		return normal;
	}

	public void setNormal(Normal normal) {
		this.normal = normal;
	}
	
	public ItemStyle(){
		
	}

	public ItemStyle(Normal normal, Emphasis emphasis) {
		this.normal = normal;
		this.emphasis = emphasis;
	}
	
}
