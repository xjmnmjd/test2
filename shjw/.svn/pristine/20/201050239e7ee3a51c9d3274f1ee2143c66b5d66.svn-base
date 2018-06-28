package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Station;

public interface StationMapper {

	void addStation(Station station);

	int getCountStation();

	List<Map<String, Object>> selectPageAll(int pageindex);

	Station selectOnestation(@Param("stationid") int stationid);

	List<Station> listStation();

	void updateStation(Station station);

	void deleteStation(int stationid);

	Station findStation(int station_id);

	List<Station> getAll();

	Map<String, Object> getStationWT(
			@Param("wt_car_number") String wt_car_number);
    //查询区域检测站
	List<Station> findCity(String station_province);

	List<Station> find_Address(String station_area);

}
