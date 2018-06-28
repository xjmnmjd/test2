package com.sojson.permission.service;

import java.util.List;

import com.sojson.common.model.M_role;

public interface MRoleService {
	/**
	 * 角色添加接口
	 */
	void addmRole(M_role mrole);
	/**
	 * 角色列表接口
	 */
	List<M_role> getAll_role();
	/**
	 * 角色删除接口
	 */
	void deletemRole(int parseInt);
	/**
	 * 查询角色接口
	 */
	M_role findRole(int roleid);

}
