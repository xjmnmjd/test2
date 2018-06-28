package com.sojson.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Station_hb;
import com.sojson.system.service.HbStationService;
import com.sojson.system.service.StationService;

@Controller
@Scope(value = "prototype")
@RequestMapping("hb")
public class HbStationController extends BaseController {
	@Resource
	HbStationService hbStationService;
	@Resource
	StationService stationService;

	/**
	 * 环保收费list
	 * 
	 * @return
	 */
	@RequestMapping(value = "hbList")
	@ResponseBody
	public Map<String, Object> hbList(String stationid) {
		List<Station_hb> hbList = hbStationService.hbList(Integer
				.parseInt(stationid));
		resultMap.put("hbList", hbList);
		return resultMap;
	}

	/**
	 * 添加环保收费收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "addHb")
	@ResponseBody
	public Map<String, Object> addHb(Station_hb station_hb) {
		hbStationService.addHb(station_hb);
		/*
		 * List<Station> liststation=stationService.getAll(); List<Station_hb>
		 * hbList=hbStationService.hbList(21); for(Station station:liststation){
		 * for(Station_hb hb:hbList){ hb.setStationid(station.getStationid());
		 * hbStationService.addHb(hb); }
		 * 
		 * 
		 * }
		 */

		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 单个查询环保收费收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectOneHb")
	@ResponseBody
	public Map<String, Object> selectOneHb(String hbid) {
		Station_hb station_hb = hbStationService.addHb(Integer.parseInt(hbid));
		resultMap.put("hb", station_hb);
		return resultMap;
	}

	/**
	 * 修改环保收费收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateHb")
	@ResponseBody
	public Map<String, Object> updateHb(Station_hb station_hb, String hbid) {
		station_hb.setHbid(Integer.parseInt(hbid));
		hbStationService.updateHb(station_hb);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 删除环保收费收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteHb")
	@ResponseBody
	public Map<String, Object> deleteHb(String hbid) {
		hbStationService.deleteHb(Integer.parseInt(hbid));
		resultMap.put("message", 1);
		return resultMap;
	}

}
