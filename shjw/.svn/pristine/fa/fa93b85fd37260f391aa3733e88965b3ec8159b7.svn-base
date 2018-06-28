package com.sojson.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.AutoMapper;
import com.sojson.common.model.Auto;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.AutoService;
@Service
public class AutoServiceImpl extends BaseMybatisDao<AutoMapper> implements AutoService{
    @Autowired
    AutoMapper autoMapper;

	@Override
	public List<Auto> findList(int page) {
		return autoMapper.findList(page);
	}

	@Override
	public void addAuto(Auto auto) {
		autoMapper.addAuto(auto);
	}

	@Override
	public int autoCount() {
		return autoMapper.autoCount();
	}

	@Override
	public void deleteAuto(int id) {
		autoMapper.deleteAuto(id);
	}

	@Override
	public Auto findOneAuto(int id) {
		return autoMapper.findOneAuto(id);
	}

	@Override
	public void updateAuto(Auto auto) {
		autoMapper.updateAuto(auto);
	}

   }
