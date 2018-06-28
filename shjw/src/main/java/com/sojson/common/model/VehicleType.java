package com.sojson.common.model;

import java.io.Serializable;

/**
 * 车辆类型实体
 * 
 * @author zjf
 * @version 1.0,2017年8月16日
 * 
 */
public class VehicleType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int vtid;
	private String vehicletype;// 车辆类型
	private String insert_time;
    private int sort;
	public int getVtid() {
		return vtid;
	}

	public void setVtid(int vtid) {
		this.vtid = vtid;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

}
