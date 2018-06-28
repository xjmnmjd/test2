package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Station;
import com.sojson.common.model.Station_nj;
import com.sojson.system.service.NjStationService;
import com.sojson.system.service.StationService;

@Controller
@Scope(value = "prototype")
@RequestMapping("nj")
public class NjStationController extends BaseController {
	@Resource
	NjStationService njStationService;
	@Resource
	StationService stationService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 年检收费list
	 * 
	 * @return
	 */
	@RequestMapping(value = "njlist")
	@ResponseBody
	public Map<String, Object> list(String stationid) {
		List<Station_nj> njlist = njStationService.njlist(Integer
				.parseInt(stationid));
		resultMap.put("njlist", njlist);
		return resultMap;
	}

	/**
	 * 添加年检收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "addNj")
	@ResponseBody
	public Map<String, Object> addNj(double nj_fee_a, double nj_fee_b,
			String nj_stationid, String njselect) {
		Station_nj nj = new Station_nj();
		nj.setNj_insert_time(insert_time);
		nj.setNj_fee_a(nj_fee_a);
		nj.setNj_fee_b((double) nj_fee_b);
		nj.setStation_id(Integer.parseInt(nj_stationid));
		nj.setNj_fee_c(200);
		nj.setNj_name(njselect);
		System.out.println("nj==" + nj.toString());
		njStationService.addNj(nj);
		resultMap.put("message", 1);

		/*
		 * List<Station> liststation=stationService.getAll(); List<Station_nj>
		 * njlist=njStationService.njlist(21); for(Station station:liststation){
		 * for(Station_nj nj:njlist){ if(station.getStationid()!=21){
		 * nj.setStation_id(station.getStationid());
		 * nj.setNj_insert_time(insert_time); njStationService.addNj(nj); } }
		 * 
		 * }
		 */
		return resultMap;
	}

	/**
	 * 添加年检收费导入
	 * 
	 * @return
	 */
	@Test
	public Map<String, Object> daoru() {
		List<Station> liststation = stationService.getAll();
		List<Station_nj> njlist = njStationService.njlist(21);
		for (Station station : liststation) {
			for (Station_nj nj : njlist) {
				nj.setStation_id(station.getStationid());
				nj.setNj_insert_time(insert_time);
				njStationService.addNj(nj);
			}

		}
		// njStationService.addNj(station_nj);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 查询单个年检收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectOneNj")
	@ResponseBody
	public Map<String, Object> selectOneNj(String njid) {
		Station_nj station_nj = njStationService.selectOneNj(Integer
				.parseInt(njid));
		resultMap.put("nj", station_nj);
		// System.out.println("nj=="+station_nj);
		return resultMap;
	}

	/**
	 * 修改年检收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateNj")
	@ResponseBody
	public Map<String, Object> updateNj(Station_nj station_nj, String njid) {
		station_nj.setNjid(Integer.parseInt(njid));
		station_nj.setNj_insert_time(insert_time);
		njStationService.updateNj(station_nj);
		resultMap.put("message", 1);

		return resultMap;
	}

	/**
	 * 删除年检收费
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteNj")
	@ResponseBody
	public Map<String, Object> deleteNj(String njid) {
		njStationService.deleteNj(Integer.parseInt(njid));
		resultMap.put("message", 1);
		return resultMap;
	}
}
