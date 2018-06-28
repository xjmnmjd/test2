package com.sojson.common.model;

import java.io.Serializable;

public class Pb implements Serializable {
	private int pbid;
	private String customer;
	private int userid;
	private String pb_time;
	private String insert_time;

	public int getPbid() {
		return pbid;
	}

	public void setPbid(int pbid) {
		this.pbid = pbid;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPb_time() {
		return pb_time;
	}

	public void setPb_time(String pb_time) {
		this.pb_time = pb_time;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	@Override
	public String toString() {
		return "Pb [pbid=" + pbid + ", customer=" + customer + ", userid="
				+ userid + ", pb_time=" + pb_time + ", insert_time="
				+ insert_time + "]";
	}

}
