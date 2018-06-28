package com.sojson.common.dao;

import java.util.List;

import com.sojson.common.model.Role_menu;

public interface Role_menuMapper {
    /*
     * 通过角色找菜单
     * */
	List<Role_menu> list_roleMenu(int roleid);
    /*
     * 删除roleid的所有菜单
     * */
	void delete_Menu(int roleid);
    /*
     * 添加角色菜单
     * */
	void addRole_Menu(int roleid, int menuid);

}
