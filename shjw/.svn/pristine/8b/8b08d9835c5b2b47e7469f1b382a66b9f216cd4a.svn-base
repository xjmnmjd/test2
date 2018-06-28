package com.sojson.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.NjStationMapper;
import com.sojson.common.model.Station_nj;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.NjStationService;
@Service
public class NjStationServiceImpl extends BaseMybatisDao<NjStationMapper> implements NjStationService{
	    @Autowired
		NjStationMapper njStationMapper;
       
		@Override
		public List<Station_nj> njlist(int stationid) {
			return njStationMapper.findnjlist(stationid);
		}

		@Override
		public void addNj(Station_nj station_nj) {
			
			njStationMapper.addNj(station_nj);
		}

		@Override
		public Station_nj selectOneNj(int njid) {
			return njStationMapper.selectOneNj(njid);
		}

		@Override
		public void updateNj(Station_nj station_nj) {
			
			njStationMapper.updateNj(station_nj);
		}

		@Override
		public void deleteNj(int njid) {
			njStationMapper.deleteNj(njid);
		}

		@Override
		public void deleteNJ_station(int stationid) {
			njStationMapper.deleteNj_station(stationid);
			
		}
}
