package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Order;

/*
 * author xj
 * 订单处理接口
 * 2017-8-14
 */
public interface OrderMapper {
	// =============== author xj ================================
	/*
	 * 2017-8-15 添加订单
	 */
	void insertOrder(Order order);

	/*
	 * 2017-8-16 根据uuid获取订单
	 */
	Order findByUUID(@Param("uuid") String uuid, @Param("openid") String openid);

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
	 * 2017-9-8 xj 修改订单支付状态
	 */
	void updateOrderState(Order order);

	/*
	 * 2017-9-8 xj 根据订单号查询订单
	 */
	Order findByOrderNo(@Param("orderno") String orderno,
			@Param("openid") String openid);

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
	 * 2017-8-18 修改订单费用信息
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

	/*
	 * 2017-8-24 订单数量查询
	 */
	int getCountOrder(Map<String, Object> map);

	/*
	 * 2017-8-24 全部订单分页查询
	 */
	List<Map<String, Object>> orderListPage(Map<String, Object> map1);

	/*
	 * 2017-8-24 待分配订单普通查询查询
	 */

	List<Map<String, Object>> order_nfp_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25待接收订单分页查询
	 */

	List<Map<String, Object>> order_njs_Page(Map<String, Object> map);

	/*
	 * 2017-8-25待接收订单数量
	 */
	int njscountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25违章订单数量
	 */
	int wzcountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25违章订单分页查询
	 */
	List<Map<String, Object>> order_wzcx_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25待分配数量查询
	 */
	int nfpCount(Map<String, Object> mapx);

	/*
	 * 2017-8-25 有违章修改状态
	 */
	void update_ywz(int orderid);

	/*
	 * 2017-8-25 无违章进入资料收集接口
	 */

	void update_wwz(Order order);

	/*
	 * 2017-8-25 资料收集数量接口
	 */
	int zlsj_Count(Map<String, Object> mapx);

	/*
	 * 2017-8-25 资料收集接口
	 */
	List<Map<String, Object>> order_zlsj_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25 已收集资料接口
	 */
	void update_ysj(int orderid);

	/*
	 * 2017-8-25 收集资料违章退回接口
	 */
	void order_wz_th(int orderid);

	/*
	 * 2017-8-25 委托办理数量接口
	 */
	int wtCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 委托办理订单接口
	 */
	List<Map<String, Object>> order_wtbl_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 委托办理订单资料已寄等待中修改接口
	 */
	void update_zlyj(int orderid);

	/*
	 * 2017-8-28 委托办理订单资料收到进入缴费接口
	 */
	void update_zlsd(int orderid);

	/*
	 * 2017-8-28 缴费确认订单数量接口
	 */
	int jfCountOrder(Map<String, Object> mapx);

	List<Map<String, Object>> order_jfqr_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 缴费确认接口
	 */
	void update_jf(Order order);

	/*
	 * 2017-8-28 预约年检数量接口
	 */
	int yyCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 预约年检接口
	 */
	List<Map<String, Object>> order_yynj_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 预约年检时间接口
	 */
	void update_yynj(Order order);

	/*
	 * 2017-8-28 年检订单数量接口
	 */
	int njCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 年检订单接口
	 */
	List<Map<String, Object>> order_nj_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 年检通过接口
	 */
	void update_nj_tg(int orderid);

	/*
	 * 2017-8-28 重新预约接口
	 */

	void update_nj_cxyy(Order order);

	/*
	 * 2017-8-28 回访数量
	 */
	int hfCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 回访订单
	 */
	List<Map<String, Object>> order_hf_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 回访内容
	 */
	void update_hfContent(int orderid);

	/*
	 * 2017-8-28 接受订单接口
	 */
	void update_js(int orderid);

	/*
	 * 2017-8-29 订单详情接口
	 */
	Order order_detail(Map<String, Object> map);

	/*
	 * 2017-8-29 添加订单接口
	 */
	void addOrder(Order order);

	/*
	 * 2017-8-29 分配订单接口
	 */

	void update_kfy(Long userid, String email, int orderid);

	/*
	 * 2017-9-6 财务数量接口
	 */
	int finCount_order();

	/*
	 * 2017-9-6 财务订单列表
	 */
	List<Order> fin_list(Map<String, Object> map);

	/*
	 * 2017-9-6 财务订单全部查询
	 */
	List<Order> findAll_fin();

	/*
	 * 2017-9-8 未付款待分配查询
	 */
	List<Order> nopay_fp(Map<String, Object> map);

	/*
	 * 2017-9-8 未付款待接受查询
	 */
	List<Order> nopay_js(Map<String, Object> map);

	/*
	 * 2017-9-8 未付款接受查询
	 */
	void update_nopayjs(int orderid);

	/*
	 * 2017-9-8 未付款拒绝
	 */
	void update_nopayjj(int orderid);

	/*
	 * 2017-9-8 未付款订单付款转化接口
	 */
	void update_order_tyfk(@Param("orderid")int orderid,@Param("time_end")String time_end);

	/*
	 * 2017-9-9 未付款订单申请超权限转化接口
	 */
	void update_order_sqlc(Order order);

	/*
	 * 2017-9-9 超权限转化接口
	 */
	List<Order> sp_list(Map<String, Object> map);

	/*
	 * 2017-9-9 审批接口
	 */
	void update_check(Order order);

	/*
	 * 2017-9-9 潜在客户接口
	 */
	void update_order_ddsx(int orderid);

	/*
	 * 2017-9-9 潜在客户数量接口
	 */
	int qzCount_order();

	/*
	 * 2017-9-9 潜在客户接口
	 */
	List<Order> qzlist(Map<String, Object> map);

	/*
	 * 2017-9-9 超权限数量接口
	 */
	int spCount_order();

	/*
	 * 2017-9-9 未付款待接受数量接口
	 */
	int nopayCount_js(Map<String, Object> mapx);

	/*
	 * 2017-9-9 未付款待分配数量接口
	 */
	int nopayCount_fp();

	/*
	 * 2017-9-9 添加快递信息修改接口
	 */
	void update_fin_kd(int orderid);

	/*
	 * 2017-9-15 查询当天金额接口
	 */
	List<Order> find_day_list();

	/*
	 * 2017-9-18 未付款订单数量接口
	 */
	int no_pay_count(Map<String, Object> map);

	/*
	 * 2017-9-18 未付款全部订单接口
	 */
	List<Order> nopay_All(Map<String, Object> map1);

	/*
	 * 2017-9-27 财务查询单月数据接口
	 */
	List<Order> find_month_list();

	/*
	 * 2017-10-31 客服员订单数量接口
	 */
	int getCount_kfOrder(String id);

	/*
	 * 2017-10-31 未付款客服员订单数量接口
	 */
	int getCount_nopaykfOrder(String id);

	/*
	 * 2017-11-10 修改订单信息接口
	 */
	void update_Order(Order order);

	/*
	 * 2017-11-16 未付款未接收接口
	 */
	int nopayCount_njs(Map<String, Object> mapx);
	/*
	 * 2017-12-6  当月新进订单接口
	 */
	List<Order> month_list(Map<String, Object> map);
	/*
	 * 2017-12-6  当月新进订单数量接口
	 */
	int count_month_list();
	/*
	 * 2017-12-6  已结算订单接口
	 */
	List<Order> account_list(Map<String, Object> map);
	/*
	 * 2017-12-6  已结算订单数量接口
	 */
	int count_a_list();
	/*
	 * 2017-12-6  定时任务查询违章查询接口
	 */
	List<Order> wzcx_totime();
	/*
	 * 2017-12-6  定时任务查询违章查询订单修改接口
	 */
	void update_wz_status(int wz_status, int orderid);
	/*
	 * 2017-12-6  取消违章警示接口
	 */
	void update_wzstatus(int orderid);
	/*
	 * 2017-1-11  修改订单总额，支付，线下支付接口
	 */
	void update_Dpay(Order order);
	/*
	 * 2018-1-22  删除订单接口
	 */
	void deleteOrder(int orderid);
	/*
	 * 2018-6-10  修改退单状态
	 */
	void update_chargeback(int orderid);

}