package com.sojson.permission.service;

import java.util.List;

import com.sojson.common.model.Role_menu;

public interface Role_menuService {

	List<Role_menu> list_roleMenu(int roleid);
    /*
     * 删除roleid的菜单
     * */
	void delete_Menu(int roleid);
	
    /*
     * 添加角色菜单
     * */
	void addRole_Menu(int parseInt, int string);

}
