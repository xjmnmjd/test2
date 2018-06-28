package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.VehicleType;
import com.sojson.system.service.VehicleTypeService;

@Controller
@Scope(value = "prototype")
@RequestMapping("type")
public class VehicleTypeController extends BaseController {
	@Resource
	VehicleTypeService vehicleTypeService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 车辆类型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "vtypelist")
	public ModelAndView vtypelist(ModelMap map) {
		// System.out.println("ss==");
		List<VehicleType> vtlist = vehicleTypeService.selectAllType();
		map.put("vtlist", vtlist);
		return new ModelAndView("system/vtypelist");
	}

	/**
	 * 车辆类型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "typelist")
	@ResponseBody
	public Map<String, Object> typelist() {
		List<VehicleType> vtlist = vehicleTypeService.selectAllType();
		resultMap.put("vtlist", vtlist);
		return resultMap;
	}

	/**
	 * 偷懒一下，通用页面跳转
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public ModelAndView toPage(@PathVariable("page") String page) {
		return new ModelAndView(String.format("system/%s", page));
	}

	/**
	 * 添加车辆类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "addtype")
	@ResponseBody
	public Map<String, Object> addtype(String vehicletype, String sort) {
		VehicleType vt = new VehicleType();
		vt.setVehicletype(vehicletype);
		vt.setSort(Integer.parseInt(sort));
		vt.setInsert_time(insert_time);
		int a = vehicleTypeService.addtype(vt);
		if (a > 0) {
			resultMap.put("message", 1);
		} else {
			resultMap.put("message", 0);
		}
		return resultMap;
	}

	/**
	 * 删除车辆类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "deletetype")
	@ResponseBody
	public Map<String, Object> deletetype(String vtid) {
		int a = vehicleTypeService.deletetype(vtid);
		if (a > 0) {
			resultMap.put("message", 1);
		} else {
			resultMap.put("message", 0);
		}
		return resultMap;
	}

	/**
	 * 单个车辆类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectonetype")
	@ResponseBody
	public Map<String, Object> selectonetype(String vtid) {
		int vtd = Integer.parseInt(vtid);
		VehicleType vehicletype = vehicleTypeService.selectonetype(vtd);
		resultMap.put("message", vehicletype);

		return resultMap;
	}

	/**
	 * 修改车辆类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "updatetype")
	@ResponseBody
	public Map<String, Object> updatetype(String vtid, String vehicletype,
			String sort) {
		int vtd = Integer.parseInt(vtid);
		VehicleType vt = new VehicleType();
		vt.setVtid(vtd);
		vt.setVehicletype(vehicletype);
		vt.setSort(Integer.parseInt(sort));
		vt.setInsert_time(insert_time);
		vehicleTypeService.updatetype(vt);
		resultMap.put("message", 1);

		return resultMap;
	}
}
