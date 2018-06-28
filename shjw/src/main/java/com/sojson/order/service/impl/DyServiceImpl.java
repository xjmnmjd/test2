package com.sojson.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.DyMapper;
import com.sojson.common.model.Dy;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.DyService;
@Service
public class DyServiceImpl extends BaseMybatisDao<DyMapper> implements DyService{
	@Autowired
	DyMapper dyMapper;

	@Override
	public Dy find_dy(int orderid) {
		return dyMapper.find_dy(orderid);
	}

	@Override
	public void add_dy(Dy dy) {
		dyMapper.add_dy(dy);
	}

	@Override
	public void delete_dy(Dy dy) {
		dyMapper.delete_dy(dy);
	}
}
