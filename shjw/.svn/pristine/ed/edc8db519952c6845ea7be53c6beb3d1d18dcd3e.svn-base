package com.sojson.system.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Station;

/*
 * zjf
 * 检测站接口
 * 2017-8-21
 */
public interface StationService {
	/*
	 * 添加检测站
	 */
	void addStation(Station station);

	/*
	 * 查询检测站数据总条数
	 */
	int getCountStation();

	/*
	 * 分页查询检测站
	 */
	List<Map<String, Object>> selectPageAll(int apageindex);

	/*
	 * 单个查询检测站
	 */
	Station selectOneStation(int a_stationid);

	/*
	 * 修改检测站
	 */
	void updateStation(Station station);
	List<Station> listStation();

	/*
	 * 删除检测站
	 */
	void deleteStation(int stationid);

	/*
	 * xj 2017-8-22 获取全部检测站
	 */
	List<Station> getAll();

	/*
	 * xj 2017-8-24 获取检测站委托费用
	 */
	Map<String, Object> getStationWT(
			@Param("wt_car_number") String wt_car_number);
	/*
	 * zjf 2017-9-21 查询区域检测站
	 */
	List<Station> findCity(String station_province);
	/*
	 * zjf 2017-9-21 查询地址检测站
	 */
	List<Station> find_Address(String station_area);

	
}
