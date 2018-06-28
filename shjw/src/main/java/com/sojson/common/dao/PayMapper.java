package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Pay;

public interface PayMapper {
	// 线下支付接口

	void add_pay(Pay pay);

	// 单个查询
	Pay findone_pay(@Param("id") int id);

	// 修改支出
	void update_pay(Pay pay);

	// 线下支付
	List<Pay> getOrder_xx(int orderid);

	// 支出
	List<Pay> getOrder_zf(int orderid);

	// 时间段的支付接口
	List<Pay> find_pay_time(Map<String, Object> map);

	// 应付总额接口
	List<Pay> getOrder_yf(int orderid);

	// 删除接口
	void deletePay(int id);

	// 当天的线下支付
	List<Pay> find_day_xxlist();

	// 当天的支出
	List<Pay> find_day_zflist();

	// 当月的线下支付
	List<Pay> find_month_xxlist();

	// 当月的支出
	List<Pay> find_month_zflist();
   //根据订单号删除
	void deleteByOrderid(int orderid);

}
