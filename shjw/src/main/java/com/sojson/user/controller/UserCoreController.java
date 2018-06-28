package com.sojson.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.M_menu;
import com.sojson.common.model.M_role;
import com.sojson.common.model.Role_menu;
import com.sojson.common.model.TypeView;
import com.sojson.common.model.UUser;
import com.sojson.common.utils.LoggerUtils;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.order.service.IOrderService;
import com.sojson.permission.service.MMenuService;
import com.sojson.permission.service.MRoleService;
import com.sojson.permission.service.Role_menuService;
import com.sojson.user.manager.UserManager;
import com.sojson.user.service.UUserService;

/**
 * 用户管理
 * 
 * @author xj
 * @version 1.0,2017年8月7日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("user")
public class UserCoreController extends BaseController {

	@Resource
	UUserService userService;
	@Resource
	IOrderService orderService;
	@Resource
	MRoleService mRoleService;
	@Resource
	Role_menuService role_menuService;
	@Resource
	MMenuService menuService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 个人资料
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView userIndex(ModelMap map) {
		Long id = TokenManager.getUserId();
		UUser user = userService.findOne_user(id);
		List<Role_menu> menu_roleList = role_menuService.list_roleMenu(user
				.getRoleid());
		List<M_menu> menulist = new ArrayList<>();
		menulist.clear();
		for (Role_menu role_menu : menu_roleList) {
			M_menu menu = menuService.findMenu(role_menu.getMenuid());
			menulist.add(menu);

		}
		// 使用googleguava包 对以获取的分类按照父类Id进行组装
		Multimap<Integer, M_menu> typeMultimap = ArrayListMultimap.create();
		for (int i = 0; i < menulist.size(); i++) {
			typeMultimap.put(menulist.get(i).getParentid(), menulist.get(i));
		}
		List<TypeView> list = new ArrayList<TypeView>();
		list = subType(0, typeMultimap, 0);// 递归调用 生成当前节点的子节点

		print(list, 0);

		map.put("types", list);
		return new ModelAndView("user/index");
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

	@RequestMapping(value = "sy")
	public ModelAndView sy(ModelMap map, String id) {
		if (id != null) {
			// UUser user=userService.findOne_user(Long.parseLong(id));

			UUser user = userService.selectByPrimaryKey(Long.parseLong(id));
			Map<String, Object> mapx = new HashMap<String, Object>();
			mapx.clear();
			mapx.put("userid", 0);
			// 已付款订单数量
			int count = 0;
			// 未付款
			int nopay_count = 0;
			// 已付款订单数量
			if (user.getPosition().equals("客服员")) {
				count = orderService.getCount_kfOrder(id);
				nopay_count = orderService.getCount_nopaykfOrder(id);
			} else {
				count = orderService.getCountOrder(mapx);
				nopay_count = orderService.no_pay_count(mapx);
			}
			// 未付款

			// 客服员
			List<UUser> kfyList = userService.user_kfy();
			map.put("count", count);
			map.put("nopay_count", nopay_count);
			map.put("user", user);
			map.put("kfyList", kfyList);
		}
		return new ModelAndView("user/sy");
	}

	@RequestMapping(value = "message")
	public ModelAndView message() {

		return new ModelAndView("user/message");
	}

	@RequestMapping(value = "car")
	public ModelAndView car() {

		return new ModelAndView("user/car");
	}

	/**
	 * 偷懒一下，通用页面跳转
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page") String page) {
		return new ModelAndView(String.format("user/%s", page));
	}

	/**
	 * 密码修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "updatePswd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePswd(String pswd, String newPswd) {
		// 根据当前登录的用户帐号 + 老密码，查询。
		String email = TokenManager.getToken().getEmail();
		pswd = UserManager.md5Pswd(email, pswd);
		UUser user = userService.login(email, pswd);

		/*
		 * if ("admin".equals(email)) { resultMap.put("status", 300);
		 * resultMap.put("message", "管理员不准修改密码。"); return resultMap; }
		 */
		if (null == user) {
			resultMap.put("status", 300);
			resultMap.put("message", "密码不正确！");
		} else {
			user.setPswd(newPswd);
			// 加工密码
			user = UserManager.md5Pswd(user);
			// 修改密码
			userService.updateByPrimaryKeySelective(user);
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
			// 重新登录一次
			TokenManager.login(user, Boolean.TRUE);
		}
		return resultMap;
	}

	/**
	 * 个人资料修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateSelf", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSelf(UUser entity) {
		try {
			/*
			 * System.out.println("entityz" + entity.getId() + "==" +
			 * entity.getEmail() + "===" + entity.getNickname());
			 */
			userService.updateByPrimaryKeySelective(entity);
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "修改失败!");
			LoggerUtils.fmtError(getClass(), e, "修改个人资料出错。[%s]", JSONObject
					.fromObject(entity).toString());
		}
		return resultMap;
	}

	@RequestMapping(value = "userlist")
	public ModelAndView userlist(ModelMap map, String pageIndex, String search,String s_status)
			throws UnsupportedEncodingException {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		String search_v = "";
		int apageindex = Integer.parseInt(pageIndex);
		int counts = userService.getCountUser();
		
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		if (search != null) {
			search_v = URLDecoder.decode(search, "UTF-8");
		}
		if ("".equals(s_status) || s_status == null) {
			s_status = "0";
		}
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("search_v", search_v);
		if("".equals(search_v)){
			map1.put("page", page);				
		}else{
			map1.put("page", -1);
			 s_status="1";
		}
		List<UUser> userlist = userService.userList(map1);

		map.put("page", userlist);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("search_v", search_v);
		map.put("s_status", Integer.parseInt(s_status));
		return new ModelAndView("user/user");
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "addUser")
	@ResponseBody
	public Map<String, Object> addUser(UUser entity, String sex) {
		entity.setSex(Integer.parseInt(sex));

		entity.setWork_status(0);
		entity.setPswd("123456");
		Date date = new Date();
		entity.setCreateTime(date);
		entity.setLastLoginTime(date);
		// 把密码md5
		entity = UserManager.md5Pswd(entity);
		// 设置有效
		entity.setStatus(UUser._1);
		M_role mrole = mRoleService.findRole(entity.getRoleid());

		entity.setPosition(mrole.getRole_name());
		userService.insert(entity);
		return resultMap;
	}

	/**
	 * 修改用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateUser")
	@ResponseBody
	public Map<String, Object> updateUser(UUser user, String sex, String id) {
		user.setSex(Integer.parseInt(sex));
		user.setId(Long.parseLong(id));
		M_role mrole = mRoleService.findRole(user.getRoleid());
		user.setPosition(mrole.getRole_name());
		userService.updateUser(user);
		return resultMap;
	}

	/**
	 * 单个查询用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "findOne_user")
	@ResponseBody
	public Map<String, Object> findOne_user(String id) {
		UUser user = userService.selectByPrimaryKey(Long.parseLong(id));
		resultMap.put("uuser", user);
		return resultMap;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete_User")
	@ResponseBody
	public Map<String, Object> delete_User(String id) {
		userService.delete_User(Long.parseLong(id));
		return resultMap;
	}

	/**
	 * 离职
	 * 
	 * @return
	 */
	@RequestMapping(value = "user_leave")
	@ResponseBody
	public Map<String, Object> user_leave(String id) {
		UUser user = userService.selectByPrimaryKey(Long.parseLong(id));
		user.setLeavedate(insert_time);
		user.setWork_status(1);
		userService.updateByPrimaryKeySelective(user);
		return resultMap;
	}

	/**
	 * 客服员
	 * 
	 * @return
	 */
	@RequestMapping(value = "user_kfy")
	@ResponseBody
	public Map<String, Object> user_kfy() {

		List<UUser> ulist = userService.user_kfy();
		resultMap.put("ulist", ulist);
		return resultMap;
	}
}
