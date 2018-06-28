package com.sojson.system.controller;

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
import com.sojson.common.model.VehicleNature;
import com.sojson.system.service.VehicleNatureService;

@Controller
@Scope(value = "prototype")
@RequestMapping("nature")
public class VehicleNatureController extends BaseController {
	@Resource
	VehicleNatureService vehicleNatureService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 车辆性质列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "naturelist")
	public ModelAndView list(ModelMap map) {
		List<VehicleNature> nlist = vehicleNatureService.listnature();
		map.put("naturelist", nlist);
		return new ModelAndView("system/naturelist");
	}

	@RequestMapping(value = "nlist")
	@ResponseBody
	public Map<String, Object> nlist() {
		List<VehicleNature> nlist = vehicleNatureService.listnature();
		resultMap.put("nlist", nlist);
		return resultMap;
	}

	/**
	 * 添加车辆类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "addNature")
	@ResponseBody
	public Map<String, Object> addNature(String vehiclenature, String sort) {
		VehicleNature vNature = new VehicleNature();
		vNature.setVehiclenature_name(vehiclenature);
		vNature.setInsert_time(insert_time);
		vNature.setSort(Integer.parseInt(sort));
		vNature.setVehiclenature_value(2);
		vehicleNatureService.addNature(vNature);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 单个查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectonenature")
	@ResponseBody
	public Map<String, Object> selectonenature(String vnid) {
		int vnid2 = Integer.parseInt(vnid);

		VehicleNature vehicleNature = vehicleNatureService
				.selectonenature(vnid2);
		resultMap.put("message", vehicleNature);
		return resultMap;
	}

	/**
	 * 修改车辆性质
	 * 
	 * @return
	 */
	@RequestMapping(value = "updatenature")
	@ResponseBody
	public Map<String, Object> updatenature(String vnid, String vehiclenature,
			String sort) {
		int vnid2 = Integer.parseInt(vnid);
		VehicleNature vNature = new VehicleNature();
		vNature.setVnid(vnid2);
		vNature.setVehiclenature_name(vehiclenature);
		vNature.setSort(Integer.parseInt(sort));
		vNature.setInsert_time(insert_time);
		vNature.setVehiclenature_value(2);
		vehicleNatureService.updatenature(vNature);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 修改车辆性质
	 * 
	 * @return
	 */
	@RequestMapping(value = "deletenature")
	@ResponseBody
	public Map<String, Object> deletenature(String vnid) {
		int vnid2 = Integer.parseInt(vnid);
		vehicleNatureService.deletenature(vnid2);
		resultMap.put("message", 1);
		return resultMap;
	}

}
