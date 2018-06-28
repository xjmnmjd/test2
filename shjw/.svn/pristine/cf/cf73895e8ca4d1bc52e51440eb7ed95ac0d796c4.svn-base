package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Auto;
import com.sojson.common.model.UUser;
import com.sojson.common.model.Wt;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.system.eutil.ExcelOperate;
import com.sojson.system.service.AutoService;
import com.sojson.system.service.WtService;
import com.sojson.user.service.UUserService;

@Controller
@Scope(value = "prototype")
@RequestMapping("auto")
public class AutoController extends BaseController {
	@Resource AutoService autoService;
	@Autowired
	UUserService userService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 监测站返回
	 * 
	 * @return
	 */
	@RequestMapping(value = "autoList")
	public ModelAndView autoList(ModelMap map, String pageIndex) {
		
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int page = (apageindex - 1) * 10;
           List<Auto> autolist=autoService.findList(page);
           
   		int counts = autoService.autoCount();
   		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
       	map.put("autolist", autolist);
       	map.put("total", total);
       	map.put("pageIndex", apageindex);
		return new ModelAndView("system/autolist");
	}

	/**
	 * 添加渠道商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "addAuto")
	@ResponseBody
	public Map<String, Object> addAuto(String auto_code,String auto_name) {
		Auto auto=new Auto();
		Long id = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(id);
		auto.setAuto_code(auto_code);
		auto.setAuto_name(auto_name);
		auto.setOperator(userx.getNickname());
		auto.setUserid(new Long(userx.getId()).intValue());
		auto.setUpdate_time(insert_time);
		autoService.addAuto(auto);
		resultMap.put("message", 1);
		return resultMap;
	}
	
	/**
	 * 删除渠道商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteAuto")
	@ResponseBody
	public Map<String, Object> deleteAuto(String id) {

		autoService.deleteAuto(Integer.parseInt(id));
		resultMap.put("message", 1);
		return resultMap;
	}
	
	/**
	 * 查找渠道商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findOneAuto")
	@ResponseBody
	public Map<String, Object> findOneAuto(String id) {

		Auto auto=autoService.findOneAuto(Integer.parseInt(id));
		resultMap.put("message", 1);
		resultMap.put("auto", auto);
		return resultMap;
	}
	
	/**
	 * 修改渠道商信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateAuto")
	@ResponseBody
	public Map<String, Object> updateAuto(String id,String auto_code,String auto_name) {
		Auto auto=new Auto();
		Long uid = TokenManager.getUserId();
		UUser userx = userService.selectByPrimaryKey(uid);
		auto.setId(Integer.parseInt(id));
		auto.setAuto_code(auto_code);
		auto.setAuto_name(auto_name);
		auto.setOperator(userx.getNickname());
		auto.setUserid(new Long(userx.getId()).intValue());
		auto.setUpdate_time(insert_time);
	    autoService.updateAuto(auto);
		resultMap.put("message", 1);
		resultMap.put("auto", auto);
		return resultMap;
	}

}
