package com.sojson.wx.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Order;

/*
 * xj
 * 订单处理接口
 * 2017-8-14
 */
public interface OrderService {
	// =============== author xj ================================
	/*
	 * 2017-8-15 添加订单
	 */
	void insertOrder(Order order);

	/*
	 * 2017-9-8 xj 修改订单支付状态
	 */
	void updateOrderState(Order order);

	/*
	 * 2017-9-8 xj 根据订单号查询订单
	 */
	Order findByOrderNo(@Param("orderno") String orderno, String openid);

	/*
	 * 2017-12-1 xj 根据订单号查询支付成功订单
	 */
	Order findPayOkByOrderNo(@Param("orderno") String orderno,
			@Param("openid") String openid);

	/*
	 * 2017-12-5 xj 根据订单号查询支付成功手机号
	 */
	Order findPhoneByOrderNo(@Param("orderno") String orderno);

	/*
	 * 删除订单
	 */
	void delOrderByOrderNo(@Param("orderno") String orderno);

	/*
	 * 2017-8-16 根据uuid获取订单
	 */
	Order findByUUID(@Param("uuid") String uuid, String openid);

	/*
	 * 2017-8-16 根据uuid删除订单
	 */
	void delOrderByUUID(@Param("uuid") String uuid);

	/*
	 * 2017-8-16 根据openid获取订单列表
	 */
	List<Order> findByOpenId(@Param("openid") String openid,
			@Param("orderstatus") int orderstatus);

	/*
	 * 2017-8-17 修改订单
	 */
	void updateOrder(Order order);

	/*
	 * 2017-8-17 修改订单上线检车信息
	 */
	void updateOrderSXJC(Order order);

	/*
	 * 2017-8-18 修改订单费用信息
	 */
	void updatePay(Order order);

	/*
	 * 2017-8-18 修改订单定金、发票等信息
	 */
	void updateOrderPay(Order order);

	/*
	 * 2017-8-23 获取年检费用
	 */
	Map<String, Object> getStationNJ(Map<String, Object> map);

	/*
	 * 2017-8-23 获取环保费用
	 */
	Map<String, Object> getStationHB(Map<String, Object> map);
	// ===========author zjf(以下为朱洁芳添加)================
}
