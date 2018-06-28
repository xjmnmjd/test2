package com.sojson.system.service;

import java.util.List;

import com.sojson.common.model.Auto;


public interface AutoService {

	List<Auto> findList(int page);

	void addAuto(Auto auto);

	int autoCount();

	void deleteAuto(int parseInt);

	Auto findOneAuto(int parseInt);

	void updateAuto(Auto auto);


}
