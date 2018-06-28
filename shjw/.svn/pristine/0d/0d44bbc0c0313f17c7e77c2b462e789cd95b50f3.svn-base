package com.sojson.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.BillMapper;
import com.sojson.common.model.Bill;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.wx.service.BillService;

@Service
public class BillServiceImpl extends BaseMybatisDao<BillMapper> implements
		BillService {

	@Autowired
	BillMapper billMapper;

	@Override
	public Bill getBillByOrderNo(String order_no) {
		return billMapper.getBillByOrderNo(order_no);
	}

	@Override
	public void insert(Bill bill) {
		billMapper.insert(bill);
	}

	@Override
	public void update(Bill bill) {
		billMapper.update(bill);
	}

}
