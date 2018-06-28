package com.sojson.common.dao;

import java.util.List;

import com.sojson.common.model.M_role;

public interface MRoleMapper {
	/**
	 * 角色添加接口
	 */
	void addmRole(M_role mrole);
	/**
	 * 角色列表接口
	 */
	List<M_role> getAll_role();
	/**
	 * 角色添加接口
	 */
	void deletemRole(int roleid);
	/**
	 * 角色查询接口
	 */
	M_role findRole(int roleid);

}
