package com.sojson.common.model;
/**
 * 订单轨迹实体
 * 
 * @author zjf
 * @version 1.0,2017年12月7日
 * 
 */
public class Gj {
  private int  gjid;
  private String orderno;//订单号
  private String update_time;//变更时间
  private int status_1;//变更前状态
  private int status_2;//变更后状态
  private String openid;
  private String operator;//操作者
  private String licenseplate;//车牌号
  
public Gj() {
	super();
}
public Gj(String orderno, String update_time, int status_1, int status_2,
		String operator) {
	super();
	this.orderno = orderno;
	this.update_time = update_time;
	this.status_1 = status_1;
	this.status_2 = status_2;
	this.operator = operator;
}
public int getGjid() {
	return gjid;
}
public void setGjid(int gjid) {
	this.gjid = gjid;
}
public String getOrderno() {
	return orderno;
}
public void setOrderno(String orderno) {
	this.orderno = orderno;
}
public String getUpdate_time() {
	return update_time;
}
public void setUpdate_time(String update_time) {
	this.update_time = update_time;
}
public int getStatus_1() {
	return status_1;
}
public void setStatus_1(int status_1) {
	this.status_1 = status_1;
}
public int getStatus_2() {
	return status_2;
}
public void setStatus_2(int status_2) {
	this.status_2 = status_2;
}
public String getOpenid() {
	return openid;
}
public void setOpenid(String openid) {
	this.openid = openid;
}
public String getOperator() {
	return operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}
public String getLicenseplate() {
	return licenseplate;
}
public void setLicenseplate(String licenseplate) {
	this.licenseplate = licenseplate;
}
  
  
  
  
}
