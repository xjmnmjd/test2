package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Station;
import com.sojson.system.service.HbStationService;
import com.sojson.system.service.NjStationService;
import com.sojson.system.service.StationService;

@Controller
@Scope(value = "prototype")
@RequestMapping("station")
public class StationController extends BaseController {
	@Resource
	StationService stationService;
	@Resource
	NjStationService njStationService;
	@Resource
	HbStationService hbStationService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 监测站返回
	 * 
	 * @return
	 */
	@RequestMapping(value = "stationlist")
	public ModelAndView list(ModelMap map, String pageIndex) {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int counts = stationService.getCountStation();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		List<Map<String, Object>> list = stationService
				.selectPageAll((apageindex - 1) * 10);
		map.put("stationlist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		return new ModelAndView("system/station");
	}

	/**
	 * 添加检测站
	 * 
	 * @return
	 */
	@RequestMapping(value = "addStation")
	@ResponseBody
	public Map<String, Object> addStation(String station_no, Station station) {
		station.setInsert_time(insert_time);
		stationService.addStation(station);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 查询单个检测站
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectOneStation")
	@ResponseBody
	public Map<String, Object> selectOneStation(String stationid) {
		int a_stationid = Integer.parseInt(stationid);
		Station station = stationService.selectOneStation(a_stationid);
		resultMap.put("message", station);
		return resultMap;
	}

	/**
	 * 修改检测站
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateStation")
	@ResponseBody
	public Map<String, Object> updateStation(Station station, String stationid) {

		station.setInsert_time(insert_time);
		station.setStationid(Integer.parseInt(stationid));
		stationService.updateStation(station);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 删除检测站
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteStation")
	@ResponseBody
	public Map<String, Object> deleteStation(String stationid) {
		int a_stationid = Integer.parseInt(stationid);
		stationService.deleteStation(a_stationid);
		njStationService.deleteNJ_station(a_stationid);
		hbStationService.deleteHb_station(a_stationid);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 查询城市
	 * 
	 * @return
	 */
	@RequestMapping(value = "find_city")
	@ResponseBody
	public Map<String, Object> find_city(String province) {
		String station_province = province;
		List<Station> cityList = stationService.findCity(station_province);
		List<String> citys=new ArrayList<String>();
		for(int i=0;i<cityList.size();i++){			
		    citys.add(cityList.get(i).getStation_area());
			}
		 Set<String> set = new  HashSet<String>(); 
         List<String> newList = new  ArrayList<String>(); 
         set.addAll(citys);
         newList.addAll(set);
		resultMap.put("newList", newList);
		return resultMap;
	}

	/**
	 * 查询地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "find_address")
	@ResponseBody
	public Map<String, Object> find_address(String area) {
		String station_area = area;
		List<Station> addressList = stationService.find_Address(station_area);
		resultMap.put("addressList", addressList);
		return resultMap;
	}
}
