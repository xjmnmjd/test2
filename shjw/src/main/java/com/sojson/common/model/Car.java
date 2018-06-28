package com.sojson.common.model;

import java.io.Serializable;

/**
 * 车辆实体
 * 
 * @author zjf
 * @version 1.0,2017年9月19日
 * 
 */
public class Car implements Serializable {
	private int carid;
	private String car_owner;// 车主
	private String car_phone;// 手机号
	private String car_plate_number;// 车牌
	private String car_type;// 车辆类型
	private String car_property;// 使用性质
	private String car_regdate;// 注册日期
	private String car_vin_no;// 车架号
	private String car_engine_No; // 发动机号
	private String car_brand_model;// 品牌型号
	private String car_station;// 检测站

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getCar_owner() {
		return car_owner;
	}

	public void setCar_owner(String car_owner) {
		this.car_owner = car_owner;
	}

	public String getCar_phone() {
		return car_phone;
	}

	public void setCar_phone(String car_phone) {
		this.car_phone = car_phone;
	}

	public String getCar_plate_number() {
		return car_plate_number;
	}

	public void setCar_plate_number(String car_plate_number) {
		this.car_plate_number = car_plate_number;
	}

	public String getCar_type() {
		return car_type;
	}

	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}

	public String getCar_property() {
		return car_property;
	}

	public void setCar_property(String car_property) {
		this.car_property = car_property;
	}

	public String getCar_regdate() {
		return car_regdate;
	}

	public void setCar_regdate(String car_regdate) {
		this.car_regdate = car_regdate;
	}

	public String getCar_vin_no() {
		return car_vin_no;
	}

	public void setCar_vin_no(String car_vin_no) {
		this.car_vin_no = car_vin_no;
	}

	public String getCar_engine_No() {
		return car_engine_No;
	}

	public void setCar_engine_No(String car_engine_No) {
		this.car_engine_No = car_engine_No;
	}

	public String getCar_brand_model() {
		return car_brand_model;
	}

	public void setCar_brand_model(String car_brand_model) {
		this.car_brand_model = car_brand_model;
	}

	public String getCar_station() {
		return car_station;
	}

	public void setCar_station(String car_station) {
		this.car_station = car_station;
	}

}
