package com.sojson.wx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.OrderMapper;
import com.sojson.common.model.Order;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.wx.service.OrderService;

/*
 * xj
 * 2017-8-24
 * 发票mapper接口
 */
@Service
public class OrderServiceImpl extends BaseMybatisDao<OrderMapper> implements
		OrderService {

	@Autowired
	OrderMapper orderMapper;

	@Override
	public void insertOrder(Order order) {
		orderMapper.insertOrder(order);
	}

	@Override
	public Order findByUUID(String uuid, String openid) {
		return orderMapper.findByUUID(uuid, openid);
	}

	@Override
	public List<Order> findByOpenId(String uuid, int orderstatus) {
		return orderMapper.findByOpenId(uuid, orderstatus);
	}

	@Override
	public void delOrderByUUID(String uuid) {
		orderMapper.delOrderByUUID(uuid);
	}

	@Override
	public void updateOrder(Order order) {
		orderMapper.updateOrder(order);
	}

	@Override
	public void updateOrderSXJC(Order order) {
		orderMapper.updateOrderSXJC(order);
	}

	@Override
	public void updateOrderPay(Order order) {
		orderMapper.updateOrderPay(order);
	}

	@Override
	public Map<String, Object> getStationNJ(Map<String, Object> map) {
		return orderMapper.getStationNJ(map);
	}

	@Override
	public Map<String, Object> getStationHB(Map<String, Object> map) {
		return orderMapper.getStationHB(map);
	}

	@Override
	public void updatePay(Order order) {
		orderMapper.updatePay(order);
	}

	@Override
	public void updateOrderState(Order order) {
		orderMapper.updateOrderState(order);
	}

	@Override
	public Order findByOrderNo(String orderno, String openid) {
		return orderMapper.findByOrderNo(orderno, openid);
	}

	@Override
	public void delOrderByOrderNo(String orderno) {
		orderMapper.delOrderByOrderNo(orderno);
	}

	@Override
	public Order findPayOkByOrderNo(String orderno, String openid) {
		return orderMapper.findPayOkByOrderNo(orderno, openid);
	}

	@Override
	public Order findPhoneByOrderNo(String orderno) {
		return orderMapper.findPhoneByOrderNo(orderno);
	}

}
