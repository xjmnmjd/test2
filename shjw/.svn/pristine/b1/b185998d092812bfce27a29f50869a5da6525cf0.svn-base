package com.sojson.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Kd;
import com.sojson.order.service.IOrderService;
import com.sojson.order.service.KdService;

@Controller
@Scope(value = "prototype")
@RequestMapping("kd")
public class KdController extends BaseController {
	@Resource
	KdService kdService;
	@Resource
	IOrderService orderService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 添加快递订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "addkd_no")
	@ResponseBody
	public Map<String, Object> addkd_no(String orderid, String kd_no,
			String kd_company) {
		Kd kd = new Kd();
		kd.setOrderid(Integer.parseInt(orderid));
		kd.setKd_no(kd_no);
		kd.setKd_company(kd_company);
		kd.setInsert_time(insert_time);
		kdService.addKd(kd);
		orderService.update_fin_kd(Integer.parseInt(orderid));

		return resultMap;
	}

	/**
	 * 查询快递订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "find_kd")
	@ResponseBody
	public Map<String, Object> find_kd(String orderid) {
		Kd kd = kdService.find_kd(Integer.parseInt(orderid));
		resultMap.put("kd", kd);
		return resultMap;
	}

	/**
	 * 修改快递订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "update_kd")
	@ResponseBody
	public Map<String, Object> update_kd(String kdid, String orderid,
			String kd_no, String kd_company) {
		Kd kd = new Kd();
		kd.setKdid(Integer.parseInt(kdid));
		kd.setOrderid(Integer.parseInt(orderid));
		kd.setKd_no(kd_no);
		kd.setKd_company(kd_company);
		kdService.update_kd(kd);
		return resultMap;
	}

}
