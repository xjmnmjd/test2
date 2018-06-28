package com.sojson.system.service;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.Pb;

public interface PbService {
    /*
     * 添加排班
     * */
	void addPb(Pb b);
	   /*
     * 删除本周排班
     * */
	void deletePb(String start_time, String end_time);
	   /*
  * 查询排班排班
  * */
	List<Pb> find_pb(Map<String,Object> map);

}
