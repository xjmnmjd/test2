package com.sojson.common.model;

import java.io.Serializable;

/**
 * 车辆性质实体
 * 
 * @author zjf
 * @version 1.0,2017年8月16日
 * 
 */
public class VehicleNature implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int vnid;
	private String vehiclenature_name;// 名称
	private String insert_time;// 时间
	private int vehiclenature_value;
    private int sort;//排序
	public int getVnid() {
		return vnid;
	}

	public void setVnid(int vnid) {
		this.vnid = vnid;
	}

	public String getVehiclenature_name() {
		return vehiclenature_name;
	}

	public void setVehiclenature_name(String vehiclenature_name) {
		this.vehiclenature_name = vehiclenature_name;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public int getVehiclenature_value() {
		return vehiclenature_value;
	}

	public void setVehiclenature_value(int vehiclenature_value) {
		this.vehiclenature_value = vehiclenature_value;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
