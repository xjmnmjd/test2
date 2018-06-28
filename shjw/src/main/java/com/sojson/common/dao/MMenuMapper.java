package com.sojson.common.dao;

import java.util.List;

import com.sojson.common.model.M_menu;

public interface MMenuMapper {
   /*
    * 查询菜单
    * */
	List<M_menu> getAllMenu();
	   /*
	    * 添加菜单
	    * */
	void addMenu(M_menu menu);
	   /*
	    * 查询菜单
	    * */
	M_menu findMenu(int menuid);
	   /*
	    * 修改菜单菜单
	    * */
	void updateMenu(M_menu menu);
	   /*
	    * 删除菜单菜单
	    * */
	void deleteMenu(int menuid);
	   /*
	    * 菜单数量接口
	    * */
	int getCountMenu();	
	   /*
	    * 分页查询接口
	    * */
	List<M_menu> getAllMenu_fy(int pageIndex);

}
