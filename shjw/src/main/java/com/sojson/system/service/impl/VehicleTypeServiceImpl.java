package com.sojson.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.VehicleTypeMapper;
import com.sojson.common.model.VehicleType;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.VehicleTypeService;

@Service
public class VehicleTypeServiceImpl extends BaseMybatisDao<VehicleTypeMapper> implements VehicleTypeService{
    @Autowired
	VehicleTypeMapper vehicleTypeMapper;

	@Override
	public List<VehicleType> selectAllType() {
		   
		return vehicleTypeMapper.selectAllType();
	}

	@Override
	public int addtype(VehicleType vt) {
		
		return vehicleTypeMapper.addtype(vt);
	}

	@Override
	public int deletetype(String vtid) {
		return vehicleTypeMapper.deletetype(vtid);
	}

	@Override
	public VehicleType selectonetype(int vtid) {
		// TODO Auto-generated method stub
		return vehicleTypeMapper.selectonetype(vtid);
	}

	@Override
	public void updatetype(VehicleType vt) {
		// TODO Auto-generated method stub
		vehicleTypeMapper.updatetype(vt);
	}
}
