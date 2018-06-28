package com.sojson.common.model;

import java.io.Serializable;

/**
 * 角色
 * 
 * @author zjf
 * @version 1.0,2017年9月7日
 * 
 */
public class M_role implements Serializable {
	private int roleid;
	private String role_name;// 角色名字
	private String insert_time;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

}
