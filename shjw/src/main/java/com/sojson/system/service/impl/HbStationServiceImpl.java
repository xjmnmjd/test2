package com.sojson.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.HbStationMapper;
import com.sojson.common.model.Station_hb;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.HbStationService;
@Service
public class HbStationServiceImpl extends BaseMybatisDao<HbStationMapper> implements HbStationService{
    @Autowired 
	HbStationMapper hbStationMapper;
	@Override
	public void addHb(Station_hb station_hb) {
		hbStationMapper.addHb(station_hb);
		
	}
	@Override
	public List<Station_hb> hbList(int stationid) {
		// TODO Auto-generated method stub
		return hbStationMapper.hbList(stationid);
	}
	@Override
	public Station_hb addHb(int hbid) {
		return hbStationMapper.selectOneHb(hbid);
	}
	@Override
	public void updateHb(Station_hb station_hb) {
		hbStationMapper.updateHb(station_hb);
		
	}
	@Override
	public void deleteHb(int hbid) {
		hbStationMapper.deleteHb(hbid);
		
	}
	@Override
	public void deleteHb_station(int stationid) {
		hbStationMapper.deleteHb_station(stationid);
	}

}
