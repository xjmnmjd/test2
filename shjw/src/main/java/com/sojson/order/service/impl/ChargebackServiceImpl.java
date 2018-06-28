package com.sojson.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.ChargebackMapper;
import com.sojson.common.model.Chargeback;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.ChargebackService;
@Service
public class ChargebackServiceImpl extends BaseMybatisDao<ChargebackMapper> implements ChargebackService{
	@Autowired
	ChargebackMapper chargebackMapper;

	@Override
	public void add_chargeback(Chargeback cb) {
		chargebackMapper.add_chargeback(cb);	
	}
}
