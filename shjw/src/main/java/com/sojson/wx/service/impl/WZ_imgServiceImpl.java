package com.sojson.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.WZ_imgMapper;
import com.sojson.common.model.WZ_img;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.wx.service.WZ_imgService;

@Service
public class WZ_imgServiceImpl extends BaseMybatisDao<WZ_imgMapper> implements
		WZ_imgService {

	@Autowired
	WZ_imgMapper wz_imgMapper;

	@Override
	public List<WZ_img> getWZ_imgByOrderNo(String order_no) {
		return wz_imgMapper.getWZ_imgByOrderNo(order_no);
	}

}
