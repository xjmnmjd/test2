package com.sojson.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sojson.common.model.Wt;
import com.sojson.system.eutil.ExcelOperate;
import com.sojson.system.service.WtService;

@Controller
@Scope(value = "prototype")
@RequestMapping("wt")
public class WtController extends BaseController {
	@Resource
	WtService wtService;
	String insert_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(new Date());

	/**
	 * 监测站返回
	 * 
	 * @return
	 */
	@RequestMapping(value = "wtList")
	public ModelAndView wtList(ModelMap map, String pageIndex) {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int counts = wtService.getCountWt();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		List<Map<String, Object>> list = wtService
				.selectWtPage((apageindex - 1) * 10);
		map.put("wtlist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		return new ModelAndView("system/wtlist");
	}

	/**
	 * 添加外牌委托
	 * 
	 * @return
	 */
	@RequestMapping(value = "addWt")
	@ResponseBody
	public Map<String, Object> addWt(Wt wt) {
		wt.setWt_insert_time(insert_time);
		// 导入	
/*		 List<Wt> list=ExcelOperate.readExcel();
		 for(Wt tt:list){
		 tt.setWt_insert_time(insert_time);	
		 wtService.addWt(tt);
		 }*/		 
		wtService.addWt(wt);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 单个查询外牌委托
	 * 
	 * @return
	 */
	@RequestMapping(value = "selectOneWt")
	@ResponseBody
	public Map<String, Object> selectOneWt(String wtid) {
		Wt wt = wtService.selectOneWt(Integer.parseInt(wtid));
		resultMap.put("wt", wt);
		return resultMap;
	}

	/**
	 * 修改外牌委托
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateWt")
	@ResponseBody
	public Map<String, Object> updateWt(Wt wt, String wtid) {
		wt.setWtid(Integer.parseInt(wtid));
		wt.setWt_insert_time(insert_time);
		wtService.updateWt(wt);
		resultMap.put("message", 1);
		return resultMap;
	}

	/**
	 * 修改外牌委托
	 * 
	 * @return
	 */
	@RequestMapping(value = "	deleteWt")
	@ResponseBody
	public Map<String, Object> deleteWt(String wtid) {
		wtService.deleteWt(Integer.parseInt(wtid));
		resultMap.put("message", 1);
		return resultMap;
	}

}
