package com.sojson.system.service;

import java.util.List;

import com.sojson.common.model.VehicleType;

public interface VehicleTypeService {

	List<VehicleType> selectAllType();


	int deletetype(String vtid);

	VehicleType selectonetype(int vtid);

	void updatetype(VehicleType vehicletype);


	int addtype(VehicleType vt);

}
