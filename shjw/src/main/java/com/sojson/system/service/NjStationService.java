package com.sojson.system.service;

import java.util.List;

import com.sojson.common.model.Station_nj;

public interface NjStationService {

	List<Station_nj> njlist(int stationid);

	void addNj(Station_nj station_nj);

	Station_nj selectOneNj(int parseInt);

	void updateNj(Station_nj station_nj);

	void deleteNj(int parseInt);

	void deleteNJ_station(int a_stationid);

}
