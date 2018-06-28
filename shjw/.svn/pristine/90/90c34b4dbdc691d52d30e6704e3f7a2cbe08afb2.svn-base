package com.sojson.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Bill;
import com.sojson.common.model.ChangeRecord;
import com.sojson.common.model.Kd;
import com.sojson.common.model.Order;
import com.sojson.common.model.Pay;
import com.sojson.common.model.UUser;
import com.sojson.core.shiro.token.manager.TokenManager;
import com.sojson.order.service.IChangeRecord;
import com.sojson.order.service.IOrderService;
import com.sojson.order.service.KdService;

@Controller
@Scope(value = "prototype")
@RequestMapping("cr")
public class ChangeRecordController extends BaseController {
	@Resource
	IChangeRecord ichangeRecord;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());
	/**
	 * 付款页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "listRecord")
	public ModelAndView listRecord(ModelMap map,String pageIndex,
			String operator,String start_time,String end_time,String s_status) {
		String tj_start = start_time;
		String tj_end = end_time;

		if (start_time != null && !start_time.equals("")) {
			start_time += " 00:00:00";
		}
		if (end_time != null && !end_time.equals("")) {
			end_time += " 00:00:00";
		}
		
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("operator", operator);
		map1.put("start_time", start_time);
		map1.put("end_time", end_time);
		      if ((start_time == null || "".equals(start_time))
				 && (end_time == null || "".equals(end_time))
				 &&operator==null||"".equals(operator)){
		    	  map1.put("page", page);		
		     }else{
		         s_status = "1";
		    	 map1.put("page", -1); 		    	 
		     }
		      if ("".equals(s_status) || s_status == null) {
					s_status = "0";
				}
				int counts = ichangeRecord.CountRecord();
				int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		        List<ChangeRecord> listRecord=ichangeRecord.findList(map1);
		map.put("listRecord", listRecord);
		map.put("operator", operator);
		map.put("end_time", tj_end);
		map.put("start_time", tj_start);
		
		map.put("pageIndex", apageindex);
		map.put("total", total);
		map.put("s_status", Integer.parseInt(s_status));
	  return new ModelAndView("system/change_record");
	}

}
