package com.sojson.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.MRoleMapper;
import com.sojson.common.model.M_role;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.permission.service.MRoleService;

@Service
public class MRoleServiceImpl extends BaseMybatisDao<MRoleMapper> implements MRoleService{
	@Autowired
	MRoleMapper mRoleMapper;
	/**
	 * 角色添加
	 */
	@Override
	public void addmRole(M_role mrole) {
		mRoleMapper.addmRole(mrole);
	}
	/**
	 * 角色列表
	 */
	@Override
	public List<M_role> getAll_role() {
		return mRoleMapper.getAll_role();
	}
	@Override
	public void deletemRole(int roleid) {
		mRoleMapper.deletemRole(roleid);		
	}
	@Override
	public M_role findRole(int roleid) {
		return mRoleMapper.findRole(roleid);
	}
}
