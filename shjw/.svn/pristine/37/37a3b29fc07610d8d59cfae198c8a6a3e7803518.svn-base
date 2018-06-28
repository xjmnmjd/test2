package com.sojson.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;

import com.sojson.common.model.Chargeback;
import com.sojson.common.model.Order;

/*
 * zjf
 * 订单处理接口
 * 2017-8-24
 */
public interface IOrderService {
	/*
	 * 2017-8-24 订单数量查询
	 */
	int getCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-24 全部订单分页查询
	 */
	List<Map<String, Object>> orderListPage(Map<String, Object> map1);

	/*
	 * 2017-8-24 待分配订单条件分页查询
	 */
	List<Map<String, Object>> order_nfp_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25 待接受订单分页查询
	 */
	List<Map<String, Object>> order_njs_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25 待接受订单数量
	 */
	int njsCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-29 接受订单
	 */
	void update_js(int orderid);

	/*
	 * 2017-8-25 违章查询订单数量
	 */
	int wzCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25 违章查询订单分页查询
	 */
	List<Map<String, Object>> order_wzcx_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25 待分配数量查询
	 */
	int nfpCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25 违章修改数据
	 */
	void update_ywz(int orderid);

	/*
	 * 2017-8-25 无违章进入资料收集
	 */
	void update_wwz(Order order);

	/*
	 * 2017-8-25 资料收集订单数量接口
	 */
	int zlCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25 资料收集订单接口
	 */
	List<Map<String, Object>> order_zlsj_Page(Map<String, Object> map1);

	/*
	 * 2017-8-25 资料已收集接口
	 */
	void update_ysj(int parseInt);

	/*
	 * 2017-8-25 资料收集违章
	 */
	void order_wz_th(int orderid);

	/*
	 * 2017-8-25 委托办理订单数量接口
	 */
	int wtCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-25 委托办理订单
	 */
	List<Map<String, Object>> order_wtbl_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 委托办理订单资料已寄等待中修改接口
	 */
	void update_zlyj(int parseInt);

	/*
	 * 2017-8-28 委托办理订单资料收到进入缴费接口
	 */
	void update_zlsd(int parseInt);

	/*
	 * 2017-8-28 缴费确认订单数量接口
	 */
	int jfCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 缴费确认订单接口
	 */
	List<Map<String, Object>> order_jfqr_Page(Map<String, Object> map1);

	/*
	 * 2017-8-28 缴费确认接口
	 */
	void update_jf(Order order);

	/*
	 * 2017-8-28 预约年检订单数量接口
	 */
	int yyCountOrder(Map<String, Object> mapx);

	/*
	 * 2017-8-28 预约年检订单接口
	 */
	List<Map<String, Object>> order_yynj_Page(Map<String, Object> map1);

	/*
	 * 预约年检时间
	 */
	void update_yynj(String yy_time, String yy_station, int orderid);

	/*
	 * 年检订单数量
	 */
	int njCountOrder(Map<String, Object> mapx);

	/*
	 * 年检订单接口
	 */
	List<Map<String, Object>> order_nj_Page(Map<String, Object> map1);

	/*
	 * 年检通过接口
	 */
	void update_nj_tg(int orderid);

	/*
	 * 年检重新预约接口接口
	 */
	void update_nj_tg(int parseInt, String nj_time);

	/*
	 * 回访接口
	 */
	int hfCountOrder(Map<String, Object> mapx);

	/*
	 * 回访订单
	 */
	List<Map<String, Object>> order_hf_Page(Map<String, Object> map1);

	/*
	 * 回访年检完成
	 */
	void update_hfContent(int parseInt);

	/*
	 * 订单详情接口
	 */
	Order order_detail(Map<String, Object> map);

	/*
	 * 添加订单接口接口
	 */
	void addOrder(Order order);

	/*
	 * 分配接口
	 */
	void update_kfy(Long id, String email, int orderid);

	/*
	 * 财务订单数量接口
	 */
	int finCount_order();

	/*
	 * 财务列表接口
	 */
	List<Order> fin_list(Map<String, Object> map1);

	/*
	 * 财务全部接口
	 */
	List<Order> findAll_fin();

	/*
	 * 未付款待分配接口
	 */
	List<Order> nopay_fp(Map<String, Object> map);

	/*
	 * 未付款待接受接口
	 */
	List<Order> nopay_js(Map<String, Object> map);

	/*
	 * 未付款接受接口
	 */
	void update_nopayjs(int orderid);

	/*
	 * 未付款拒绝订单接口
	 */
	void update_nopayjj(int parseInt);

	/*
	 * 未付款付款订单转化接口
	 */
	void update_order_tyfk(@Param("orderid")int orderid, @Param("time_end")String time_end);

	/*
	 * 未付款付款订单申请超权限审批接口
	 */
	void update_order_sqlc(Order order);

	/*
	 * 超权限审批接口
	 */
	List<Order> sp_list(Map<String, Object> map1);

	/*
	 * 审批接口
	 */
	void update_check(Order order);

	/*
	 * 潜在客户接口
	 */
	void update_order_ddsx(int parseInt);

	/*
	 * 潜在客户数量接口
	 */
	int qzCount_order();

	/*
	 * 潜在客户接口
	 */
	List<Order> qz_list(Map<String, Object> map1);

	/*
	 * 超权限数量接口
	 */
	int spCount_order();

	/*
	 * 未付款待接受数量接口
	 */
	int nopayCount_js(Map<String, Object> mapx);

	/*
	 * 未付款待分配数量接口
	 */
	int nopayCount_fp();

	/*
	 * 添加快递信息接口
	 */
	void update_fin_kd(int parseInt);

	/*
	 * 查询当天记录金额接口
	 */
	List<Order> find_day_list();

	/*
	 * 未付款订单数量接口
	 */
	int no_pay_count(Map<String, Object> mapx);

	/*
	 * 未付款全部接口
	 */
	List<Order> nopay_All(Map<String, Object> map1);

	/*
	 * 财务查询当月接口
	 */
	List<Order> find_month_list();

	/*
	 * 客服员订单数量接口
	 */
	int getCount_kfOrder(String id);

	/*
	 * 未付款客服员订单数量接口
	 */
	int getCount_nopaykfOrder(String id);

	/*
	 * 修改订单信息接口
	 */
	void update_Order(Order order);

	/*
	 * 未付款未接收接口
	 */
	int nopayCount_njs(Map<String, Object> mapx);

	/*
	 * 当月新进订单接口
	 */
	List<Order> month_list(Map<String, Object> map);

	/*
	 * 当月新进订单数量接口
	 */
	int count_month_list();

	/*
	 * 已结算订单接口
	 */
	List<Order> account_list(Map<String, Object> map1);

	/*
	 * 已结算订单数量接口
	 */
	int count_a_list();

	/*
	 * 定时任务查询违章订单接口
	 */
	List<Order> wzcx_totime();

	/*
	 * 定时任务修改查询违章订单状态接口
	 */
	void update_wz_status(int wz_status, int orderid);

	/*
	 * 取消违章警示接口
	 */
	void update_wzstatus(int orderid);
	/*
	 * 修改订单pay接口
	 */
	void update_Dpay(Order order);
	/*
	 * 删除订单接口
	 */
	void deleteOrder(int orderid);
	/*
	 * 2018 6/10 修改 修改退单订单状态
	 */
	void update_chargeback(int parseInt);

}
