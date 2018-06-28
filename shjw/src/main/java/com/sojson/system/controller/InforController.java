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
import com.sojson.common.model.Infor;
import com.sojson.common.model.UUser;
import com.sojson.common.model.VehicleType;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.system.service.MyInforService;
import com.sojson.user.service.UUserService;
@Controller
@Scope(value = "prototype")
@RequestMapping("info")
public class InforController extends BaseController{
	@Resource
	MyInforService myInforService;
	@Autowired
	UUserService userService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	.format(new Date());
	/**
	 * 浏览版信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "inforlist")
	public ModelAndView inforlist(ModelMap map) {
		List<Infor> inforlist = myInforService.find_inforList();
		map.put("inforlist", inforlist);
		return new ModelAndView("system/lyan");
	}
	
	
	/**
	 * 处理留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_ly")
	@ResponseBody
	public Map<String, Object> update_ly(String id,String state) {
		
		Long userid = TokenManager.getUserId();
		UUser user = userService.selectByPrimaryKey(userid);
		
		Infor infor=new Infor();
		infor.setId(Integer.parseInt(id));
		infor.setState(Integer.parseInt(state));
		infor.setOperator(user.getNickname());
		infor.setUpdate_time(insert_time);
		myInforService.update_ly(infor);		
		return resultMap;
	}
	/**
	 * 删除留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteInfor")
	@ResponseBody
	public Map<String, Object> deleteInfor(String id) {
		myInforService.deleteInfor(Integer.parseInt(id));		
		return resultMap;
	}
}
