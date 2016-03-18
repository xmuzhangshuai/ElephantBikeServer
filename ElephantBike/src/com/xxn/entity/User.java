package com.xxn.entity;

public class User {
	
	private int id;
	private String phone;
	private String name;
	//wxid字段作为学号
	private String wxid;
	private String idcardaddr;
	private String stucardaddr;
	private String userstate;
	private String college;
	private String registerdate;
	private String vip;
	private String vipdate;
	private float balance;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWxid() {
		return wxid;
	}
	public void setWxid(String wxid) {
		this.wxid = wxid;
	}
	public String getIdcardaddr() {
		return idcardaddr;
	}
	public void setIdcardaddr(String idcardaddr) {
		this.idcardaddr = idcardaddr;
	}
	public String getStucardaddr() {
		return stucardaddr;
	}
	public void setStucardaddr(String stucardaddr) {
		this.stucardaddr = stucardaddr;
	}
	public String getUserstate() {
		return userstate;
	}
	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getVipdate() {
		return vipdate;
	}
	public void setVipdate(String vipdate) {
		this.vipdate = vipdate;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String phone, String userstate) {
		super();
		this.phone = phone;
		this.userstate = userstate;
	}
	public User(String phone, String userstate, String registerdate) {
		super();
		this.phone = phone;
		this.userstate = userstate;
		this.registerdate = registerdate;
	}
	public User(String phone, String wxid, String idcardaddr,
			String stucardaddr, String userstate, String college,
			String registerdate) {
		super();
		this.phone = phone;
		this.wxid = wxid;
		this.idcardaddr = idcardaddr;
		this.stucardaddr = stucardaddr;
		this.userstate = userstate;
		this.college = college;
		this.registerdate = registerdate;
	}
	public User(int id, String phone, String wxid, String idcardaddr,
			String stucardaddr, String userstate, String college,
			String registerdate, String vip, String vipdate, float balance) {
		super();
		this.id = id;
		this.phone = phone;
		this.wxid = wxid;
		this.idcardaddr = idcardaddr;
		this.stucardaddr = stucardaddr;
		this.userstate = userstate;
		this.college = college;
		this.registerdate = registerdate;
		this.vip = vip;
		this.vipdate = vipdate;
		this.balance = balance;
	}
	
}
