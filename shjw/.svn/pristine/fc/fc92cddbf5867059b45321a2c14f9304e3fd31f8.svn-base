package com.sojson.order.service;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.Pay;

public interface PayService {
     //添加线下支付接口
	void add_pay(Pay pay);

	Pay findone_pay(int parseInt);
	//更改支付
	void update_pay(Pay pay);
	//线下支付接口
	List<Pay> getOrder_xx(int orderid);
	//支出接口
	List<Pay> getOrder_zf(int orderid);
    //时间段内付款详情

	List<Pay> find_pay_time(Map<String, Object> map2);
	//应付总额查询
	List<Pay> getOrder_yf(int orderid);
	//删除支付
	void deletePay(int parseInt);
    //当天的线下支付
	List<Pay> find_day_xxlist();
	//当天的支出
	List<Pay> find_day_zflist();
	//当月的线下支付
	List<Pay> find_month_xxlist();
	//当月的支出
	List<Pay> find_month_zflist();
    //根据订单id删除
	void deleteByOrderid(int orderid);


}
