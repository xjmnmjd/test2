package com.sojson.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.WtMapper;
import com.sojson.common.model.Wt;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.WtService;
@Service
public class WtServiceImpl extends BaseMybatisDao<WtMapper> implements WtService{
    @Autowired
	WtMapper wtMapper;

	@Override
	public void addWt(Wt wt) {
		System.out.println("aa"+wt.toString());
		wtMapper.addwt(wt);
		
	}

	@Override
	public int getCountWt() {
		// TODO Auto-generated method stub
		return wtMapper.getCountWt();
	}

	@Override
	public List<Map<String, Object>> selectWtPage(int i) {
		// TODO Auto-generated method stub
		return wtMapper.selectWtPage(i);
	}

	@Override
	public Wt selectOneWt(int wtid) {
		// TODO Auto-generated method stub
		return wtMapper.seletOneWt(wtid);
	}

	@Override
	public void updateWt(Wt wt) {
		
		wtMapper.updateWt(wt);
	}

	@Override
	public void deleteWt(int wtid) {
		wtMapper.deleteWt(wtid);
		
	}
   }
