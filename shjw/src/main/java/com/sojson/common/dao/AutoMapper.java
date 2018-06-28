package com.sojson.common.dao;

import java.util.List;

import com.sojson.common.model.Auto;

public interface AutoMapper {

	List<Auto> findList(int page);

	void addAuto(Auto auto);

	int autoCount();

	void deleteAuto(int id);

	Auto findOneAuto(int id);

	void updateAuto(Auto auto);

}
