package com.sojson.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.Up_dateMapper;
import com.sojson.common.model.Up_date;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.Up_dateService;
@Service
public class Up_dateServiceImpl extends BaseMybatisDao<Up_dateMapper> implements Up_dateService{
	@Autowired
	Up_dateMapper up_dateMapper;
	/*
	 * 查找时间
	 * */
	@Override
	public Up_date findUp_date(String orderno) {
		return up_dateMapper.findUp_date(orderno);
	}
	/*
	 * 添加时间
	 * */
	@Override
	public void addUp_date(Up_date up_date) {
		up_dateMapper.addUp_date(up_date);
	}
	@Override
	public void update_up_date(Up_date up_date) {
		up_dateMapper.update_up_date(up_date);

	}
}
