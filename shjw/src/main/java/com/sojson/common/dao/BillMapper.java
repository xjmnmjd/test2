package com.sojson.common.dao;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Bill;

/*
 * xj
 * 2017-8-24
 * 发票mapper接口
 */
public interface BillMapper {
	/*
	 * 通过订单号获取发票信息
	 */
	public Bill getBillByOrderNo(@Param("order_no") String order_no);

	/*
	 * 添加发票
	 */
	public void insert(Bill bill);

	/*
	 * 修改发票
	 */
	public void update(Bill bill);

}
