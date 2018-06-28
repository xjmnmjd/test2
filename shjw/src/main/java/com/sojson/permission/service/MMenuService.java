package com.sojson.permission.service;

import java.util.List;

import com.sojson.common.model.M_menu;

public interface MMenuService {
    /*
     * 查询菜单
     * */
	List<M_menu> getAllMenu();
    /*
     * 添加菜单菜单
     * */
	void addMenu(M_menu menu);
    /*
     *查询菜单
     * */
	M_menu findMenu(int menuid);
	 /*
     *修改菜单接口
     * */
	void updateMenu(M_menu menu);
	 /*
     *删除菜单接口
     * */
	void deleteMenu(int menuid);
	 /*
     *菜单数量接口
     * */
	int getCountMenu();
	 /*
     *菜单分页接口
     * */
	List<M_menu> getAllMenu_fy(int pageIndex);
}
