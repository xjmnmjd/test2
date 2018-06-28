package com.sojson.system.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.VehicleNatureMapper;
import com.sojson.common.model.VehicleNature;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.VehicleNatureService;

@Service
public class VehicleNatureServiceImpl extends BaseMybatisDao<VehicleNatureMapper> implements VehicleNatureService{
    @Autowired
   	VehicleNatureMapper vehicleNatureMapper;

	@Override
	public void addNature(VehicleNature vNature) {
		vehicleNatureMapper.addnature(vNature);		
	}

	@Override
	public List<VehicleNature> listnature() {
		// TODO Auto-generated method stub
		return vehicleNatureMapper.listnature();
	}

	@Override
	public void updatenature(VehicleNature vNature) {
		
		vehicleNatureMapper.updatenature(vNature);;
	}

	@Override
	public VehicleNature selectonenature(int vnid2) {
		// TODO Auto-generated method stub
		return vehicleNatureMapper.selectonenature(vnid2);
	}

	@Override
	public void deletenature(int vnid2) {
		vehicleNatureMapper.deletenature(vnid2);
		
	}


	

}
