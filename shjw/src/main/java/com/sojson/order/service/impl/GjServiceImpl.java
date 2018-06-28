package com.sojson.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.GjMapper;
import com.sojson.common.model.Gj;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.GjService;

@Service
public class GjServiceImpl extends BaseMybatisDao<GjMapper> implements
		GjService {
	@Autowired
	GjMapper gjMapper;

	/*
	 * 2017-12-7 添加订单操作轨迹
	 */
	@Override
	public void add_GJ(Gj gj) {
		gjMapper.add_GJ(gj);
	}

	@Override
	public List<Gj> findByOrderNo(String orderno) {
		return gjMapper.findByOrderNo(orderno);
	}

	@Override
	public List<Gj> selectGjList(Map<String, Object> map) {
		return gjMapper.selectGjList(map);
	}

	@Override
	public int countGj() {
		return gjMapper.countGj();
	}
}
