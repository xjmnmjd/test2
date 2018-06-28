package com.sojson.common.model;

import java.io.Serializable;

/**
 * 快递实体
 * 
 * @author zjf
 * @version 1.0,2017年9月14日
 * 
 */
public class Kd implements Serializable {
	private int kdid;
	private String kd_no;
	private String kd_company;
	private int orderid;
	private String insert_time;

	public int getKdid() {
		return kdid;
	}

	public void setKdid(int kdid) {
		this.kdid = kdid;
	}

	public String getKd_no() {
		return kd_no;
	}

	public void setKd_no(String kd_no) {
		this.kd_no = kd_no;
	}

	public String getKd_company() {
		return kd_company;
	}

	public void setKd_company(String kd_company) {
		this.kd_company = kd_company;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	@Override
	public String toString() {
		return "Kd [kdid=" + kdid + ", kd_no=" + kd_no + ", kd_company="
				+ kd_company + ", orderid=" + orderid + ", insert_time="
				+ insert_time + "]";
	}

}
