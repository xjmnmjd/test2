package com.sojson.message.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Car;
import com.sojson.common.model.Message;
import com.sojson.message.messUtil.MessExcelOperate;
import com.sojson.message.service.MessService;
/**
 * 短信管理
 * 
 * @author zjf
 * @version 1.0,2017年9月20日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("mess")
public class MessController extends BaseController{
	  @Resource
	  MessService messService;
		/**
		 * 短信管理
		 * 
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		@RequestMapping(value = "messList")
		public ModelAndView carList(ModelMap map,String me_search,
	     String start_time,String end_time,String pageIndex,Message message,String status) throws UnsupportedEncodingException{
			if(pageIndex==null||pageIndex.equals("")){
				pageIndex="1";	 
			 }
			String search_v="";
			 int apageindex=Integer.parseInt(pageIndex);
			 int counts=messService.getCountMessage();
			 int total=(counts%10==0)?(counts/10):(counts/10+1);
			 int page=(apageindex-1)*10;
			 if(me_search!=null){
		  		  search_v=URLDecoder.decode(me_search, "UTF-8");
		  	 }
			 String x_start_time="";
			 String x_end_time="";
		  	 if(start_time!=null&&!("".equals(start_time))){
		  		x_start_time=start_time+" 00:00:00";
		  	 }
		  	 if(end_time!=null&&!("".equals(end_time))){
		  		x_end_time=end_time+" 00:00:00";
		  	 }
		  	 Map<String,Object> map1=new HashMap<String,Object>();  
			    map1.put("page", page);
			    map1.put("message", message);
			    map1.put("search_v", search_v);
			    map1.put("start_time", x_start_time);
			    map1.put("end_time", x_end_time);
			 List<Map<String, Object>> list=messService.messList(map1);
			 
			 
			 if(status==null||"".equals(status)){
				status="0"; 				 
			 }
	           map.put("messlist", list);
	           map.put("pageIndex", apageindex);
	           map.put("total", total);
	           map.put("search", search_v);
	           map.put("message", message);
	           map.put("start_time", start_time);
	           map.put("end_time", end_time);
	           map.put("status", Integer.parseInt(status));
			return new ModelAndView("message/message");
		}
		/**
		 * 删除短信
		 * 
		 * @return
		 */
		@RequestMapping(value = "delete_Mess")
		@ResponseBody
		public Map<String, Object> delete_Mess(String messid) {
			messService.delete_Mess(Integer.parseInt(messid));
			return resultMap;
		}
		/**
		 * 导出短信
		 * 
		 * @return
		 */
		@RequestMapping(value = "mess_daoc")
		@ResponseBody
		public Map<String, Object> mess_daoc(HttpServletResponse response) {
			List<Message> messageL=messService.findAllMess();
			MessExcelOperate.createExcel(messageL, response);
			return resultMap;
		}
		
}
