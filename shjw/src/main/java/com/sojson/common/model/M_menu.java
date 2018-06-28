package com.sojson.common.model;

import java.io.Serializable;

public class M_menu implements Serializable {
	private int menuid;
	private String menu_name;
	private int parentid;
	private String insert_time;
	private String menu_url;// 菜单url
	private String menu_pid;// 菜单选择器id
	private int sort;// 排序
	private String menu_iclass;

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_pid() {
		return menu_pid;
	}

	public void setMenu_pid(String menu_pid) {
		this.menu_pid = menu_pid;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getMenu_iclass() {
		return menu_iclass;
	}

	public void setMenu_iclass(String menu_iclass) {
		this.menu_iclass = menu_iclass;
	}

}
