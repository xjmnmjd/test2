package com.sojson.common.model;

import java.io.Serializable;

/*
 * 发票实体
 * xj
 * 2017-8-24 16:33
 */
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String bill_type;// 发票抬头类型
	private String bill_head;// 发票抬头
	private String taxpayer_identification_number;// 纳税人识别号
	private String company_address;// 公司地址
	private String company_phone;// 公司电话
	private String opening_bank;// 开户行
	private String bank_no;// 银行账号
	private String mailing_address;// 寄送地址
	private String contact_number;// 联系电话
	private String order_no;// 订单号
	private String insert_time;// 添加时间
	private String update_time;// 修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
	}

	public String getBill_head() {
		return bill_head;
	}

	public void setBill_head(String bill_head) {
		this.bill_head = bill_head;
	}

	public String getTaxpayer_identification_number() {
		return taxpayer_identification_number;
	}

	public void setTaxpayer_identification_number(
			String taxpayer_identification_number) {
		this.taxpayer_identification_number = taxpayer_identification_number;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getCompany_phone() {
		return company_phone;
	}

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}

	public String getOpening_bank() {
		return opening_bank;
	}

	public void setOpening_bank(String opening_bank) {
		this.opening_bank = opening_bank;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getMailing_address() {
		return mailing_address;
	}

	public void setMailing_address(String mailing_address) {
		this.mailing_address = mailing_address;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
