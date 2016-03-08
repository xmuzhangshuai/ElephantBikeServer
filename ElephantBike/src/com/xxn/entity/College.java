package com.xxn.entity;

public class College {
	private int id;
	private String latlng;
	private String name;
	
	public String getlatlng() {
		return latlng;
	}
	public void setlatlng(String latlng) {
		this.latlng = latlng;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public College(String latlng, String name) {
		super();
		this.latlng = latlng;
		this.name = name;
	}
}
