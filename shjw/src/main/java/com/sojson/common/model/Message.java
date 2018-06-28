package com.sojson.common.model;

import java.io.Serializable;

/**
 * 短信实体
 * 
 * @author zjf
 * @version 1.0,2017年9月20日
 * 
 */
public class Message implements Serializable {
	private int messid;
	private String message_time;// 发送时间
	private String message_phone;// 号码
	private String message_owner;// 车主
	private String message_plate;// 车牌
	private String message_content;// 内容
	private  int message_status;// 状态  0 未发送 1：已发送
    public int getMessage_status() {
		return message_status;
	}

	public void setMessage_status(int message_status) {
		this.message_status = message_status;
	}

	private String message_operator;//操作者
	public int getMessid() {
		return messid;
	}

	public void setMessid(int messid) {
		this.messid = messid;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	public String getMessage_phone() {
		return message_phone;
	}

	public void setMessage_phone(String message_phone) {
		this.message_phone = message_phone;
	}

	public String getMessage_owner() {
		return message_owner;
	}

	public void setMessage_owner(String message_owner) {
		this.message_owner = message_owner;
	}

	public String getMessage_plate() {
		return message_plate;
	}

	public void setMessage_plate(String message_plate) {
		this.message_plate = message_plate;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}



	public String getMessage_operator() {
		return message_operator;
	}

	public void setMessage_operator(String message_operator) {
		this.message_operator = message_operator;
	}

}
