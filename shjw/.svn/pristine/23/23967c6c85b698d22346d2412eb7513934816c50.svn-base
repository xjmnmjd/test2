package com.sojson.permission.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.M_menu;
import com.sojson.common.model.M_role;
import com.sojson.common.model.TypeView;
import com.sojson.permission.service.MMenuService;
import com.sojson.permission.service.MRoleService;

/**
 * 用户角色管理
 * 
 * @author zjf
 * @version 1.0,2017年9月8日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("mrole")
public class MRoleController extends BaseController {
	@Resource
	MRoleService mRoleService;
	@Resource
	MMenuService mMenuService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 角色添加
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "addmRole")
	@ResponseBody
	public Map<String, Object> addmRole(String role_name) {
		M_role mrole = new M_role();
		mrole.setRole_name(role_name);
		mrole.setInsert_time(insert_time);
		mRoleService.addmRole(mrole);
		return resultMap;
	}

	/**
	 * 角色列表
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "rolelist")
	@ResponseBody
	public Map<String, Object> rolelist() {
		List<M_role> rolelist = mRoleService.getAll_role();
		resultMap.put("rolelist", rolelist);
		return resultMap;
	}

	@RequestMapping(value = "qx")
	public ModelAndView qx(ModelMap map) {
		List<M_role> lmrole = mRoleService.getAll_role();
		List<M_menu> menuL = mMenuService.getAllMenu();
		Multimap<Integer, M_menu> typeMultimap = ArrayListMultimap.create(); // 使用googleguava包
																				// 对以获取的分类按照父类Id进行组装
		for (int i = 0; i < menuL.size(); i++) {
			typeMultimap.put(menuL.get(i).getParentid(), menuL.get(i));
		}
		List<TypeView> list = new ArrayList<TypeView>();
		list = subType(0, typeMultimap, 0);// 递归调用 生成当前节点的子节点
		print(list, 0);
		map.put("types", list);
		map.put("lmrole", lmrole);
		return new ModelAndView("role/qx");
	}

	/**
	 * 递归处理多级分类问题
	 * 
	 * @param parentId
	 *            父类Id
	 * @param maps
	 *            所有分类的按照父类ID组装后容器
	 * @param level
	 *            分类的级别 0：根
	 * @return 返回 parentId 节点的子分类节点【可能是多个】
	 */
	public List<TypeView> subType(Integer parentId,
			Multimap<Integer, M_menu> maps, int level) {
		List<TypeView> list = new ArrayList<TypeView>();
		Collection<M_menu> trList = maps.get(parentId);
		for (Iterator<M_menu> iterator = trList.iterator(); iterator.hasNext();) {
			M_menu typeTemp = iterator.next();
			TypeView typeView = new TypeView();
			typeView.setM_menu(typeTemp);
			typeView.setLevel(level);
			list.add(typeView);
			typeView.setChildren(subType(typeTemp.getMenuid(), maps, level + 1));
		}

		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	// 打印当前归类好的分类
	private void print(List<TypeView> list, int level) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (Iterator<TypeView> iterator = list.iterator(); iterator.hasNext();) {
			TypeView typeView = iterator.next();
			M_menu menu = typeView.getM_menu();
			// System.out.println("目录等级:"+typeView.getLevel()+"----"+menu.getMenu_name());
			print(typeView.getChildren(), level + 1);

		}
	}

	/**
	 * 角色删除
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "deletemRole")
	@ResponseBody
	public Map<String, Object> deletemRole(String roleid) {
		mRoleService.deletemRole(Integer.parseInt(roleid));
		return resultMap;
	}
}
