package com.xxn.entity;

public class College {
	private int id;
	private String latlng;
	private String name;
	private String collegeid;
	
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
	public String getCollegeid() {
		return collegeid;
	}
	public void setCollegeid(String collegeid) {
		this.collegeid = collegeid;
	}
	public College(String latlng, String name) {
		super();
		this.latlng = latlng;
		this.name = name;
	}
	public College(String latlng, String name, String collegeid) {
		super();
		this.latlng = latlng;
		this.name = name;
		this.collegeid = collegeid;
	}
	
}
