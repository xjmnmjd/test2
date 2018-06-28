package com.sojson.permission.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Role_menu;
import com.sojson.permission.service.Role_menuService;

/**
 * 角色菜单
 * 
 * @author zjf
 * @version 1.0,2017年9月12日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("role_menu")
public class MRole_menuController extends BaseController {

	@Resource
	Role_menuService role_menuService;

	/**
	 * 角色菜单查询
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "list_roleMenu")
	@ResponseBody
	public Map<String, Object> list_roleMenu(String roleid) {
		List<Role_menu> list_rm = role_menuService.list_roleMenu(Integer
				.parseInt(roleid));
		resultMap.put("list_rm", list_rm);
		return resultMap;
	}

	/**
	 * 添加角色菜单添加
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "addRole_menu")
	@ResponseBody
	public Map<String, Object> addRole_menu(String roleid, String checkArray) {
		// 删除roleid的所有菜单
		role_menuService.delete_Menu(Integer.parseInt(roleid));
		String[] array = checkArray.split("&");

		for (int i = 0; i < array.length; i++) {
			role_menuService.addRole_Menu(Integer.parseInt(roleid),
					Integer.parseInt(array[i]));
		}
		return resultMap;
	}

}
