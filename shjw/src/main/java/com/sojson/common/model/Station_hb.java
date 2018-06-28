package com.sojson.common.model;

import java.io.Serializable;

/**
 * 环保年检实体
 * 
 * @author zjf
 * @version 1.0,2017年8月21日
 * 
 */
public class Station_hb implements Serializable {
	private int hbid;
	private String hb_name; // 环保车辆性质名称
	private double hb_time_a;// 收费时间1-6年
	private double hb_time_b; // 收费时间7-10年
	private double hb_time_c; // 收费时间11-13年
	private double hb_time_d; // 收费时间13年以上
	private int stationid;

	public int getHbid() {
		return hbid;
	}

	public void setHbid(int hbid) {
		this.hbid = hbid;
	}

	public String getHb_name() {
		return hb_name;
	}

	public void setHb_name(String hb_name) {
		this.hb_name = hb_name;
	}

	public double getHb_time_a() {
		return hb_time_a;
	}

	public void setHb_time_a(double hb_time_a) {
		this.hb_time_a = hb_time_a;
	}

	public double getHb_time_b() {
		return hb_time_b;
	}

	public void setHb_time_b(double hb_time_b) {
		this.hb_time_b = hb_time_b;
	}

	public double getHb_time_c() {
		return hb_time_c;
	}

	public void setHb_time_c(double hb_time_c) {
		this.hb_time_c = hb_time_c;
	}

	public double getHb_time_d() {
		return hb_time_d;
	}

	public void setHb_time_d(double hb_time_d) {
		this.hb_time_d = hb_time_d;
	}

	public int getStationid() {
		return stationid;
	}

	public void setStationid(int stationid) {
		this.stationid = stationid;
	}

	@Override
	public String toString() {
		return "Station_hb [hbid=" + hbid + ", hb_name=" + hb_name
				+ ", hb_time_a=" + hb_time_a + ", hb_time_b=" + hb_time_b
				+ ", hb_time_c=" + hb_time_c + ", hb_time_d=" + hb_time_d
				+ ", stationid=" + stationid + "]";
	}

}
