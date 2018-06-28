package com.sojson.common.model;

import java.io.Serializable;

/**
 * 回访实体
 * 
 * @author zjf
 * @version 1.0,2017年8月29日
 * 
 */
public class Hf implements Serializable {
	private int hfid;
	private String hf_time;// 回访时间
	private String hf_content;// 回访内容
	private int orderid;// 订单id

	public int getHfid() {
		return hfid;
	}

	public void setHfid(int hfid) {
		this.hfid = hfid;
	}

	public String getHf_time() {
		return hf_time;
	}

	public void setHf_time(String hf_time) {
		this.hf_time = hf_time;
	}

	public String getHf_content() {
		return hf_content;
	}

	public void setHf_content(String hf_content) {
		this.hf_content = hf_content;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

}
