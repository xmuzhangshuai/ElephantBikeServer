package com.xxn.entity;

public class Announce {
	private int id;
	private String helpcontent;
	private String protocolcontent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHelpcontent() {
		return helpcontent;
	}
	public void setHelpcontent(String helpcontent) {
		this.helpcontent = helpcontent;
	}
	public String getProtocolcontent() {
		return protocolcontent;
	}
	public void setProtocolcontent(String protocolcontent) {
		this.protocolcontent = protocolcontent;
	}
	public Announce(String helpcontent, String protocolcontent) {
		super();
		this.helpcontent = helpcontent;
		this.protocolcontent = protocolcontent;
	}
}
