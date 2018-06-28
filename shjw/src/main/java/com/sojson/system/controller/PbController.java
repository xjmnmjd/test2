package com.sojson.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Pb;
import com.sojson.common.model.UUser;
import com.sojson.system.service.PbService;
import com.sojson.user.service.UUserService;

/**
 * 排班管理
 * 
 * @author zjf
 * @version 1.0,2017年9月14日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("pb")
public class PbController extends BaseController {
	@Resource
	PbService pbService;
	@Resource
	UUserService userService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "pblist")
	public ModelAndView pblist(ModelMap map) throws ParseException {
		List<UUser> kfy_list = userService.user_kfy();
		map.put("kfy_list", kfy_list);
		return new ModelAndView("user/pb");
	}

	/**
	 * 保存排班
	 * 
	 * @return
	 */
	@RequestMapping(value = "bj_pb")
	@ResponseBody
	public Map<String, Object> bj_pb(String jsonStr, String start_time,
			String end_time) {
		// 删除本周的排班消息
		pbService.deletePb(start_time, end_time);
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		List<Pb> list = (List) JSONArray.toCollection(jsonArray, Pb.class);
		for (Pb b : list) {
			b.setInsert_time(insert_time);
			pbService.addPb(b);
		}
		return resultMap;
	}

	/**
	 * 排班查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "pb_search")
	@ResponseBody
	public Map<String, Object> pb_search(String start_time, String end_time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		List<Pb> pbList = pbService.find_pb(map);
		resultMap.put("pbList", pbList);
		return resultMap;
	}

}
