package com.sojson.common.model;
/**
 * 年检到期三天以上时间实体
 * 
 * @author zjf
 * @version 1.0,2017年12月7日
 * 
 */
public class Up_date {
   private int oid;
   private String update_time;//修改时间
   private String orderno;
public int getOid() {
	return oid;
}
public void setOid(int oid) {
	this.oid = oid;
}
public String getUpdate_time() {
	return update_time;
}
public void setUpdate_time(String update_time) {
	this.update_time = update_time;
}
public String getOrderno() {
	return orderno;
}
public void setOrderno(String orderno) {
	this.orderno = orderno;
}

public Up_date() {
	super();
}
public Up_date(String update_time, String orderno) {
	super();
	this.update_time = update_time;
	this.orderno = orderno;
}
   
   
}
