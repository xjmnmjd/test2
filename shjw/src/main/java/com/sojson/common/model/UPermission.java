package com.sojson.common.model;

import java.io.Serializable;

import net.sf.json.JSONObject;
/**
 * 权限实体
 * @author xj
 * @version 1.0,2017年8月7日
 * 
 */
public class UPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 操作的url */
	private String url;
	/** 操作的名称 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}