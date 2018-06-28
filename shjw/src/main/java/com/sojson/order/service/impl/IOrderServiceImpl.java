package com.sojson.order.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.OrderMapper;
import com.sojson.common.model.Order;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.IOrderService;

@Service
public class IOrderServiceImpl extends BaseMybatisDao<OrderMapper> implements
		IOrderService {
	@Autowired
	OrderMapper orderMapper;

	/*
	 * 2017-8-24 订单数量查询
	 */
	@Override
	public int getCountOrder(Map<String, Object> map) {
		return orderMapper.getCountOrder(map);
	}

	/*
	 * 2017-8-24 全部订单分页查询
	 */
	@Override
	public List<Map<String, Object>> orderListPage(Map<String, Object> map1) {

		return orderMapper.orderListPage(map1);
	}

	/*
	 * 2017-8-24 待分配订单分页查询
	 */
	@Override
	public List<Map<String, Object>> order_nfp_Page(Map<String, Object> map1) {
		return orderMapper.order_nfp_Page(map1);
	}

	/*
	 * 2017-8-25待接收订单分页查询
	 */
	@Override
	public List<Map<String, Object>> order_njs_Page(Map<String, Object> map) {
		return orderMapper.order_njs_Page(map);
	}

	/*
	 * 2017-8-25待接收订单数量
	 */
	@Override
	public int njsCountOrder(Map<String, Object> mapx) {
		return orderMapper.njscountOrder(mapx);
	}

	/*
	 * 2017-8-25 违章查询订单数量
	 */
	@Override
	public int wzCountOrder(Map<String, Object> mapx) {
		return orderMapper.wzcountOrder(mapx);
	}

	/*
	 * 2017-8-25 违章查询订单分页查询
	 */
	@Override
	public List<Map<String, Object>> order_wzcx_Page(Map<String, Object> map1) {
		return orderMapper.order_wzcx_Page(map1);
	}

	/*
	 * 2017-8-25 待分配数量查询
	 */
	@Override
	public int nfpCountOrder(Map<String, Object> mapx) {
		return orderMapper.nfpCount(mapx);
	}

	/*
	 * 2017-8-25 违章修改数据
	 */
	@Override
	public void update_ywz(int orderid) {
		orderMapper.update_ywz(orderid);

	}

	/*
	 * 2017-8-25 无违章进入资料收集
	 */
	@Override
	public void update_wwz(Order order) {
		orderMapper.update_wwz(order);

	}

	/*
	 * 2017-8-25 资料收集接口
	 */
	@Override
	public int zlCountOrder(Map<String, Object> mapx) {
		return orderMapper.zlsj_Count(mapx);
	}

	/*
	 * 2017-8-25 资料收集接口
	 */
	@Override
	public List<Map<String, Object>> order_zlsj_Page(Map<String, Object> map1) {
		return orderMapper.order_zlsj_Page(map1);
	}

	/*
	 * 2017-8-25 已收集资料接口
	 */
	@Override
	public void update_ysj(int orderid) {
		orderMapper.update_ysj(orderid);
	}

	/*
	 * 2017-8-25 资料收集违章
	 */
	@Override
	public void order_wz_th(int orderid) {
		orderMapper.order_wz_th(orderid);

	}

	/*
	 * 2017-8-25 委托办理订单数量
	 */
	@Override
	public int wtCountOrder(Map<String, Object> mapx) {
		return orderMapper.wtCountOrder(mapx);
	}

	/*
	 * 2017-8-25 委托办理订单
	 */
	@Override
	public List<Map<String, Object>> order_wtbl_Page(Map<String, Object> map1) {
		return orderMapper.order_wtbl_Page(map1);
	}

	/*
	 * 2017-8-25 委托办理订单资料已寄等待中修改
	 */
	@Override
	public void update_zlyj(int orderid) {
		orderMapper.update_zlyj(orderid);

	}

	/*
	 * 2017-8-25 委托办理订单资料收到进入缴费
	 */
	@Override
	public void update_zlsd(int orderid) {
		orderMapper.update_zlsd(orderid);
	}

	/*
	 * 2017-8-25 缴费确认订单接口
	 */
	@Override
	public int jfCountOrder(Map<String, Object> mapx) {
		return orderMapper.jfCountOrder(mapx);
	}

	/*
	 * 2017-8-25 缴费确认订单接口
	 */
	@Override
	public List<Map<String, Object>> order_jfqr_Page(Map<String, Object> map1) {
		return orderMapper.order_jfqr_Page(map1);
	}

	/*
	 * 2017-8-25 缴费确认接口
	 */
	@Override
	public void update_jf(Order order) {
		orderMapper.update_jf(order);

	}

	/*
	 * 2017-8-28 预约年检数量
	 */
	@Override
	public int yyCountOrder(Map<String, Object> mapx) {
		return orderMapper.yyCountOrder(mapx);
	}

	/*
	 * 2017-8-28 预约年检
	 */
	@Override
	public List<Map<String, Object>> order_yynj_Page(Map<String, Object> map1) {
		return orderMapper.order_yynj_Page(map1);
	}

	/*
	 * 2017-8-28预约年检时间
	 */
	@Override
	public void update_yynj(String appointmenttime, String station, int orderid) {
		Order order = new Order();
		order.setAppointmenttime(appointmenttime);
		order.setStation(station);
		order.setOrderid(orderid);
		orderMapper.update_yynj(order);

	}

	/*
	 * 2017-8-28 年检订单数量
	 */
	@Override
	public int njCountOrder(Map<String, Object> mapx) {
		return orderMapper.njCountOrder(mapx);
	}

	/*
	 * 2017-8-28 年检订单
	 */
	@Override
	public List<Map<String, Object>> order_nj_Page(Map<String, Object> map1) {
		return orderMapper.order_nj_Page(map1);
	}

	/*
	 * 2017-8-28 年检通过
	 */
	@Override
	public void update_nj_tg(int orderid) {
		orderMapper.update_nj_tg(orderid);
	}

	/*
	 * 2017-8-28 年检重新预约
	 */
	@Override
	public void update_nj_tg(int orderid, String appointmenttime) {
		Order order = new Order();
		order.setAppointmenttime(appointmenttime);
		order.setOrderid(orderid);
		orderMapper.update_nj_cxyy(order);
	}

	/*
	 * 2017-8-28 回访数量
	 */
	@Override
	public int hfCountOrder(Map<String, Object> mapx) {
		return orderMapper.hfCountOrder(mapx);
	}

	/*
	 * 2017-8-28 回访订单
	 */
	@Override
	public List<Map<String, Object>> order_hf_Page(Map<String, Object> map1) {
		return orderMapper.order_hf_Page(map1);
	}

	/*
	 * 2017-8-28 回访完成
	 */
	@Override
	public void update_hfContent(int orderid) {
		orderMapper.update_hfContent(orderid);
	}

	/*
	 * 2017-8-28 接受订单
	 */
	@Override
	public void update_js(int orderid) {
		orderMapper.update_js(orderid);

	}

	/*
	 * 2017-8-29 订单详情接口
	 */
	@Override
	public Order order_detail(Map<String, Object> map) {
		return orderMapper.order_detail(map);
	}

	@Override
	public void addOrder(Order order) {
		orderMapper.addOrder(order);
	}

	/*
	 * 2017-9-2 分配
	 */
	@Override
	public void update_kfy(Long id, String email, int orderid) {
		orderMapper.update_kfy(id, email, orderid);
	}

	/*
	 * 2017-9-6 财务订单数量
	 */
	@Override
	public int finCount_order() {
		return orderMapper.finCount_order();
	}

	/*
	 * 2017-9-6 财务订单列表
	 */
	@Override
	public List<Order> fin_list(Map<String, Object> map) {
		return orderMapper.fin_list(map);
	}

	@Override
	public List<Order> findAll_fin() {
		return orderMapper.findAll_fin();
	}

	/*
	 * 2017-9-8 未付款待分配
	 */
	@Override
	public List<Order> nopay_fp(Map<String, Object> map) {
		return orderMapper.nopay_fp(map);
	}

	@Override
	public List<Order> nopay_js(Map<String, Object> map) {
		return orderMapper.nopay_js(map);
	}

	@Override
	public void update_nopayjs(int orderid) {
		orderMapper.update_nopayjs(orderid);
	}

	/*
	 * 2017-9-8 未付款拒绝订单
	 */
	@Override
	public void update_nopayjj(int orderid) {
		orderMapper.update_nopayjj(orderid);
	}

	/*
	 * 2017-9-8 未付款付款订单转化
	 */
	@Override
	public void update_order_tyfk(@Param("orderid") int orderid, @Param("time_end") String time_end) {
		orderMapper.update_order_tyfk(orderid, time_end);

	}

	@Override
	public void update_order_sqlc(Order order) {
		orderMapper.update_order_sqlc(order);
	}

	@Override
	public List<Order> sp_list(Map<String, Object> map) {
		return orderMapper.sp_list(map);
	}

	/*
	 * 2017-9-8 审批订单
	 */
	@Override
	public void update_check(Order order) {
		orderMapper.update_check(order);
	}

	/*
	 * 2017-9-8 潜在客户订单
	 */
	@Override
	public void update_order_ddsx(int orderid) {
		orderMapper.update_order_ddsx(orderid);

	}

	/*
	 * 2017-9-8 潜在客户订单数量
	 */
	@Override
	public int qzCount_order() {
		return orderMapper.qzCount_order();
	}

	/*
	 * 2017-9-8 潜在客户订单
	 */
	@Override
	public List<Order> qz_list(Map<String, Object> map) {
		return orderMapper.qzlist(map);
	}

	/*
	 * 2017-9-8 超权限订单数量
	 */
	@Override
	public int spCount_order() {
		return orderMapper.spCount_order();
	}

	/*
	 * 2017-9-8 未付款待接受订单数量
	 */
	@Override
	public int nopayCount_js(Map<String, Object> mapx) {
		return orderMapper.nopayCount_js(mapx);
	}

	/*
	 * 2017-9-8 未付款待分配订单数量
	 */
	@Override
	public int nopayCount_fp() {
		return orderMapper.nopayCount_fp();
	}

	@Override
	public void update_fin_kd(int orderid) {
		orderMapper.update_fin_kd(orderid);
	}

	@Override
	public List<Order> find_day_list() {
		return orderMapper.find_day_list();
	}

	@Override
	public int no_pay_count(Map<String, Object> map) {
		return orderMapper.no_pay_count(map);
	}

	@Override
	public List<Order> nopay_All(Map<String, Object> map1) {
		return orderMapper.nopay_All(map1);
	}

	@Override
	public List<Order> find_month_list() {
		return orderMapper.find_month_list();
	}


	/*
	 * 客服员订单数量
	 */
	@Override
	public int getCount_kfOrder(String id) {
		return orderMapper.getCount_kfOrder(id);
	}

	/*
	 * 未付款客服员订单数量
	 */
	@Override
	public int getCount_nopaykfOrder(String id) {
		return orderMapper.getCount_nopaykfOrder(id);
	}

	/*
	 * 修改订单信息
	 */
	@Override
	public void update_Order(Order order) {
		orderMapper.update_Order(order);
	}

	@Override
	public int nopayCount_njs(Map<String, Object> mapx) {
		return orderMapper.nopayCount_njs(mapx);
	}

	/*
	 * 当月新进订单
	 */
	@Override
	public List<Order> month_list(Map<String, Object> map) {
		return orderMapper.month_list(map);
	}

	/*
	 * 当月新进订单数量
	 */
	@Override
	public int count_month_list() {
		return orderMapper.count_month_list();
	}

	/*
	 * 已结算订单
	 */
	@Override
	public List<Order> account_list(Map<String, Object> map) {
		return orderMapper.account_list(map);
	}

	@Override
	public int count_a_list() {
		return orderMapper.count_a_list();
	}

	/*
	 * 定时任务查询违章订单
	 */
	@Override
	public List<Order> wzcx_totime() {
		return orderMapper.wzcx_totime();
	}

	@Override
	public void update_wz_status(int wz_status, int orderid) {
		orderMapper.update_wz_status(wz_status, orderid);
	}

	// 取消违章警示
	@Override
	public void update_wzstatus(int orderid) {
		orderMapper.update_wzstatus(orderid);
	}
	/*
	 * 修改订单总额 支付，线下支付订单
	 */
	@Override
	public void update_Dpay(Order order) {
		orderMapper.update_Dpay(order);
	}
	/**
	 * @author zjf 2018-1-22  删除订单
	 */
	@Override
	public void deleteOrder(int orderid) {
		orderMapper.deleteOrder(orderid);
	}

	@Override
	public void update_chargeback(int orderid) {
		orderMapper.update_chargeback(orderid);
	}

}
