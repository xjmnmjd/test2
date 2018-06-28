package com.sojson.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.PayMapper;
import com.sojson.common.model.Pay;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.PayService;
@Service
public class PayServiceImpl extends BaseMybatisDao<PayMapper> implements PayService{
	@Autowired
	PayMapper payMapper;

	@Override
	public void add_pay(Pay pay) {
		payMapper.add_pay(pay);		
	}

	@Override
	public Pay findone_pay(int id) {
		// TODO Auto-generated method stub
		return payMapper.findone_pay(id);
	}
	@Override
	public void update_pay(Pay pay) {
		payMapper.update_pay(pay);		
	}
	@Override
	public List<Pay> getOrder_xx(int orderid) {
		return payMapper.getOrder_xx(orderid);
	}
	@Override
	public List<Pay> getOrder_zf(int orderid) {
		return payMapper.getOrder_zf(orderid);
	}

	@Override
	public List<Pay> find_pay_time(Map<String, Object> map) {
		return payMapper.find_pay_time(map);
	}

	@Override
	public List<Pay> getOrder_yf(int orderid) {
		return payMapper.getOrder_yf(orderid);
	}

	@Override
	public void deletePay(int id) {
		payMapper.deletePay(id);	
	}
	 //当天的线下支付
	@Override
	public List<Pay> find_day_xxlist() {
		return payMapper.find_day_xxlist();
	}
	 //当天的支出
	@Override
	public List<Pay> find_day_zflist() {
		return payMapper.find_day_zflist();
	}
	//当月的线下支付
	@Override
	public List<Pay> find_month_xxlist() {
		return payMapper.find_month_xxlist();
	}

	@Override
	public List<Pay> find_month_zflist() {
		return payMapper.find_month_zflist();
	}

	@Override
	public void deleteByOrderid(int orderid) {
		payMapper.deleteByOrderid(orderid);
	}
}
