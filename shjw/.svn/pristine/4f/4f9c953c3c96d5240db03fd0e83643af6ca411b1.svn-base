package com.sojson.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.StationMapper;
import com.sojson.common.model.Station;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.StationService;

@Service
public class StationServieImpl extends BaseMybatisDao<StationMapper> implements
		StationService {
	@Autowired
	StationMapper stationMapper;

	@Override
	public void addStation(Station station) {
		stationMapper.addStation(station);

	}

	@Override
	public int getCountStation() {
		return stationMapper.getCountStation();
	}

	@Override
	public List<Map<String, Object>> selectPageAll(int apageindex) {
		return stationMapper.selectPageAll(apageindex);
	}

	@Override
	public Station selectOneStation(int a_stationid) {
		return stationMapper.selectOnestation(a_stationid);
	}

	@Override
	public void updateStation(Station station) {
		stationMapper.updateStation(station);

	}

	@Override
	public void deleteStation(int stationid) {
		stationMapper.deleteStation(stationid);
	}

	@Override
	public List<Station> listStation() {
		return stationMapper.listStation();
	}

	@Override
	public List<Station> getAll() {
		return stationMapper.getAll();
	}

	@Override
	public Map<String, Object> getStationWT(String wt_car_number) {
		return stationMapper.getStationWT(wt_car_number);
	}

	@Override
	public List<Station> findCity(String station_province) {
		return stationMapper.findCity(station_province);
	}

	@Override
	public List<Station> find_Address(String station_area) {
		return stationMapper.find_Address(station_area);
	}

}
