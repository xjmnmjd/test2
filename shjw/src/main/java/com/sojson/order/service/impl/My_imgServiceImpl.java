package com.sojson.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.WZ_imgMapper;
import com.sojson.common.model.WZ_img;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.MyWz_imgService;

@Service
public class My_imgServiceImpl extends BaseMybatisDao<WZ_imgMapper> implements
		MyWz_imgService {

	@Autowired
	WZ_imgMapper wz_imgMapper;

	@Override
	public void insert_img(WZ_img wz_img) {
		wz_imgMapper.insert_img(wz_img);
	}

	@Override
	public List<WZ_img> find_wz_img(String orderno) {
		return wz_imgMapper.find_wz_img(orderno);
	}

}
