package com.sojson.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Gj;
import com.sojson.common.model.VehicleType;

public interface GjService {
	/*
	 * 添加订单操作轨迹
	 */
	void add_GJ(Gj gj);

	/**
	 * 根据订单号获取所有轨迹
	 * 
	 * @author xj 2017-12-21
	 * @param orderno
	 * @return
	 */
	public List<Gj> findByOrderNo(@Param("orderno") String orderno);
	/**
	 *获取所有轨迹
	 * 
	 * @author zjf 2018-6-10
	 * @param map 
	 * @param orderno
	 * @return
	 */
	List<Gj> selectGjList(Map<String, Object> map);

	int countGj();
}
