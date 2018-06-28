package com.sojson.common.model;

import java.io.Serializable;

/**
 * 订单实体
 * 
 * @author zjf
 * @version 1.0,2017年8月11日
 * 
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderid;
	private String submittime; // 提交时间
	private String owner; // 车主
	private String licenseplate;// 车牌
	private String cartype; // 车辆类型
	private String phonenumber;// 手机号
	private double totalorder; // 订单总额
	private double copetotal; // 应付总额
	private double onlinepayment;// 暂时不需要
	private double offlinepayment;// 线下支付总和
	private double pay; // 支出总和
	private int ispayment; // 是否缴费 0未缴费 1：已经缴费
	private String businesstype; // 业务类型
	private String appointmenttime; // 预约时间
	private String station; // 预约监测站
	private String customer; // 客服员

	private Long userid; // 用户id
	private int orderstatus; // 订单状态 0新订单 1是待支付 2支付成功 3支付失败
	private int suditstatus; // 审核状态
	// 0:待分配 1：待接收 2：违章查询中 3:违章处理中 4资料收集中 5：委托办理中 6：资料已寄等待中
	// 7:缴费确认中 8 预约年检中 9 待年检 10 年检通过 11 年检完成  12：未付款已接受   13超权限审批中(未付款)  15 超权限审批(缴费) 14潜在客户
	private String ordersource; // 订单来源
	private String vehicle; // 车辆性质
	private String rigisterdate; // 注册日期
	private String identificationno; // 证件号码
	private String insurancedate; // 交强险到期日期
	private String identificationpath; // 省份证路径
	private String carpath; // 执照路径
	private String bill_a;
	private int is_kd;// 是否已经添加快递 0 否 1 是
	private String fk_time;// 付款时间

	private String models;// 车型
	private String vin_no;// 车架号
	private String engine_number;// 发动机号
	/**
	 * @author zjf 2017-12-15  订单警示判断
	 */
	private int wz_status;// 0： 正常    1：离年检到期小于3天数    2 ：离年检到期小于3天
	/**
	 * @author zjf 2018-1-22  订单备注（材料）
	 */
	private String orderRemark;
	/**
	 * @author zjf 2018-6-10  潜在客户状态
	 */
	private int qz_status; //1退单车辆 2 正常流程
	/**
	 * @author xj 2017-8-14增加 唯一标识码
	 */
	private String uuid;
	private String openid;// 用户唯一标识码
	private String update_time;// 修改时间
	private int is_collect_materials;// 是否上门收集材料 1代表是 0代表否
	private String collect_materials_time;// 上门收集材料时间
	private String collect_materials_address;// 上门收集材料地址
	private int is_collect_car;// 是否上门接送车 1代表是 0代表否
	private String collect_car_time;// 上门接送车时间
	private String collect_car_address;// 上门接送车地址
	private int injury_accident_last_year;// 上年是否发生人伤事故 1代表是 0代表否
	private int station_id;// 检测站主键id
	private double nj_fee;// 年检费
	private double hb_nj_fee;// 环保年检费
	private double wt_fee;// 委托费
	private String orderno;// 订单号
	private int is_bill;// 是否payment_amount开具发票 0不需要 1需要
	private int is_deposit_payment;// 是否支付定金 0否 1是
	private double payment_amount;// 实际支付金额
	private String time_end;// 微信支付时间
	private String cash_fee;// 微信支付金额
	private String result_code;// 微信支付业务结果
	private String transaction_id;// 微信支付订单号
	private String err_code_des;// 错误代码描述
	private int is_del;// 0表示未删除 1表示删除
	/**
	 * @author xj 2017-11-11
	 */
	private double wq_fee;// 尾气费
	/**
	 * @author xj 2017-12-05
	 */
	private String njdq_date;// 年检到期时间
	/**
	 * @author xj 2017-12-14寄件地址
	 */
	private String send_material_address;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getSubmittime() {
		return submittime;
	}

	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public double getCopetotal() {
		return copetotal;
	}

	public void setCopetotal(double copetotal) {
		this.copetotal = copetotal;
	}

	public double getOnlinepayment() {
		return onlinepayment;
	}

	public void setOnlinepayment(double onlinepayment) {
		this.onlinepayment = onlinepayment;
	}

	public double getOfflinepayment() {
		return offlinepayment;
	}

	public void setOfflinepayment(double offlinepayment) {
		this.offlinepayment = offlinepayment;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public int getIspayment() {
		return ispayment;
	}

	public void setIspayment(int ispayment) {
		this.ispayment = ispayment;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getAppointmenttime() {
		return appointmenttime;
	}

	public void setAppointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	public int getSuditstatus() {
		return suditstatus;
	}

	public void setSuditstatus(int suditstatus) {
		this.suditstatus = suditstatus;
	}

	public String getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(String ordersource) {
		this.ordersource = ordersource;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getRigisterdate() {
		return rigisterdate;
	}

	public void setRigisterdate(String rigisterdate) {
		this.rigisterdate = rigisterdate;
	}

	public String getIdentificationno() {
		return identificationno;
	}

	public void setIdentificationno(String identificationno) {
		this.identificationno = identificationno;
	}

	public String getInsurancedate() {
		return insurancedate;
	}

	public void setInsurancedate(String insurancedate) {
		this.insurancedate = insurancedate;
	}

	public String getIdentificationpath() {
		return identificationpath;
	}

	public void setIdentificationpath(String identificationpath) {
		this.identificationpath = identificationpath;
	}

	public String getCarpath() {
		return carpath;
	}

	public void setCarpath(String carpath) {
		this.carpath = carpath;
	}

	public double getTotalorder() {
		return totalorder;
	}

	public void setTotalorder(double totalorder) {
		this.totalorder = totalorder;
	}

	public String getBill_a() {
		return bill_a;
	}

	public void setBill_a(String bill_a) {
		this.bill_a = bill_a;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public int getIs_collect_materials() {
		return is_collect_materials;
	}

	public void setIs_collect_materials(int is_collect_materials) {
		this.is_collect_materials = is_collect_materials;
	}

	public String getCollect_materials_time() {
		return collect_materials_time;
	}

	public void setCollect_materials_time(String collect_materials_time) {
		this.collect_materials_time = collect_materials_time;
	}

	public String getCollect_materials_address() {
		return collect_materials_address;
	}

	public void setCollect_materials_address(String collect_materials_address) {
		this.collect_materials_address = collect_materials_address;
	}

	public int getIs_collect_car() {
		return is_collect_car;
	}

	public void setIs_collect_car(int is_collect_car) {
		this.is_collect_car = is_collect_car;
	}

	public String getCollect_car_time() {
		return collect_car_time;
	}

	public void setCollect_car_time(String collect_car_time) {
		this.collect_car_time = collect_car_time;
	}

	public String getCollect_car_address() {
		return collect_car_address;
	}

	public void setCollect_car_address(String collect_car_address) {
		this.collect_car_address = collect_car_address;
	}

	public int getInjury_accident_last_year() {
		return injury_accident_last_year;
	}

	public void setInjury_accident_last_year(int injury_accident_last_year) {
		this.injury_accident_last_year = injury_accident_last_year;
	}

	public int getStation_id() {
		return station_id;
	}

	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}

	public double getNj_fee() {
		return nj_fee;
	}

	public void setNj_fee(double nj_fee) {
		this.nj_fee = nj_fee;
	}

	public double getHb_nj_fee() {
		return hb_nj_fee;
	}

	public void setHb_nj_fee(double hb_nj_fee) {
		this.hb_nj_fee = hb_nj_fee;
	}

	public double getWt_fee() {
		return wt_fee;
	}

	public void setWt_fee(double wt_fee) {
		this.wt_fee = wt_fee;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getIs_bill() {
		return is_bill;
	}

	public void setIs_bill(int is_bill) {
		this.is_bill = is_bill;
	}

	public int getIs_deposit_payment() {
		return is_deposit_payment;
	}

	public void setIs_deposit_payment(int is_deposit_payment) {
		this.is_deposit_payment = is_deposit_payment;
	}

	public double getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public int getIs_kd() {
		return is_kd;
	}

	public void setIs_kd(int is_kd) {
		this.is_kd = is_kd;
	}

	public String getFk_time() {
		return fk_time;
	}

	public void setFk_time(String fk_time) {
		this.fk_time = fk_time;
	}

	public double getWq_fee() {
		return wq_fee;
	}

	public void setWq_fee(double wq_fee) {
		this.wq_fee = wq_fee;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getVin_no() {
		return vin_no;
	}

	public void setVin_no(String vin_no) {
		this.vin_no = vin_no;
	}

	public String getEngine_number() {
		return engine_number;
	}

	public void setEngine_number(String engine_number) {
		this.engine_number = engine_number;
	}

	public String getNjdq_date() {
		return njdq_date;
	}

	public void setNjdq_date(String njdq_date) {
		this.njdq_date = njdq_date;
	}

	public int getWz_status() {
		return wz_status;
	}

	public void setWz_status(int wz_status) {
		this.wz_status = wz_status;
	}

	public String getSend_material_address() {
		return send_material_address;
	}

	public void setSend_material_address(String send_material_address) {
		this.send_material_address = send_material_address;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public int getQz_status() {
		return qz_status;
	}

	public void setQz_status(int qz_status) {
		this.qz_status = qz_status;
	}




}
