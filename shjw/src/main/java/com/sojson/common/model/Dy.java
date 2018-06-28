package com.sojson.common.model;

import java.io.Serializable;

/**
 * 打印编号实体
 * 
 * @author zjf
 * @version 1.0,2017年11月10日
 * 
 */
public class Dy implements Serializable {
	private int id;
	private String insert_time;
	private int orderid;// 订单id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "Dy [id=" + id + ", insert_time=" + insert_time + ", orderid="
				+ orderid + "]";
	}

}
