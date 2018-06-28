package com.sojson.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Station_nj;

public interface NjStationMapper {

	List<Station_nj> findnjlist(@Param("station_id") int stationid);

	void addNj(Station_nj station_nj);

	Station_nj selectOneNj(int njid);

	void updateNj(Station_nj station_nj);

	void deleteNj(int njid);

	void deleteNj_station(int stationid);

}
