package com.sojson.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Station_hb;

public interface HbStationMapper {

	void addHb(Station_hb station_hb);

	List<Station_hb> hbList(@Param("stationid") int stationid);

	Station_hb selectOneHb(int hbid);

	void updateHb(Station_hb station_hb);

	void deleteHb(int hbid);

	void deleteHb_station(int stationid);

}
