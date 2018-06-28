package com.sojson.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.DrivingLicenseMapper;
import com.sojson.common.model.DrivingLicense;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.wx.service.DrivingLicenseService;

@Service
public class DrivingLicenseServiceImpl extends
		BaseMybatisDao<DrivingLicenseMapper> implements DrivingLicenseService {

	@Autowired
	DrivingLicenseMapper drivingLicenseMapper;

	@Override
	public void insert(DrivingLicense drivingLicense) {
		drivingLicenseMapper.insert(drivingLicense);
	}

}
