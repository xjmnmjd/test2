package com.sojson.permission.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.M_menu;
import com.sojson.permission.service.MMenuService;

/**
 * 用户菜单管理
 * 
 * @author zjf
 * @version 1.0,2017年9月11日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("menu")
public class MMenuController extends BaseController {
	@Resource
	MMenuService menuService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 菜单查询
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "listMenu")
	@ResponseBody
	public Map<String, Object> listMenu() {
		List<M_menu> menulist = menuService.getAllMenu();
		resultMap.put("menulist", menulist);
		return resultMap;
	}

	/**
	 * 添加菜单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(M_menu menu) {
		menu.setInsert_time(insert_time);
		menuService.addMenu(menu);
		return resultMap;
	}

	/**
	 * 修改菜单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "updateMenu")
	@ResponseBody
	public Map<String, Object> updateMenu(M_menu menu, String menuid) {
		menu.setMenuid(Integer.parseInt(menuid));
		menuService.updateMenu(menu);
		return resultMap;
	}

	/**
	 * 删除菜单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "deleteMenu")
	@ResponseBody
	public Map<String, Object> deleteMenu(String menuid) {
		menuService.deleteMenu(Integer.parseInt(menuid));
		return resultMap;
	}

	/**
	 * 单个查询菜单
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "findOneMenu")
	@ResponseBody
	public Map<String, Object> findOneMenu(String menuid) {
		M_menu menu = menuService.findMenu(Integer.parseInt(menuid));
		resultMap.put("menu", menu);
		return resultMap;
	}

	@RequestMapping(value = "menulist")
	public ModelAndView menulist(ModelMap map, String pageIndex) {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int counts = menuService.getCountMenu();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		List<M_menu> menulist = menuService
				.getAllMenu_fy((apageindex - 1) * 10);

		map.put("menulist", menulist);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		return new ModelAndView("menu/menulist");
	}
}
