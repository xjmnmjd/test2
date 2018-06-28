package com.sojson.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.Role_menuMapper;
import com.sojson.common.model.Role_menu;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.permission.service.Role_menuService;
@Service
public class Role_menuServiceImpl extends BaseMybatisDao<Role_menuMapper> implements Role_menuService{
    @Autowired
    Role_menuMapper role_menuMapper;
	@Override
	public List<Role_menu> list_roleMenu(int roleid) {
		return role_menuMapper.list_roleMenu(roleid);
	}
	@Override
	public void delete_Menu(int roleid) {
		role_menuMapper.delete_Menu(roleid);		
	}
	@Override
	public void addRole_Menu(int roleid, int menuid) {
		role_menuMapper.addRole_Menu(roleid,menuid);		
	}

}
