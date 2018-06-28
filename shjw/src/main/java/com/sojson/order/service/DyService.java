package com.sojson.order.service;

import com.sojson.common.model.Dy;


public interface DyService {
     //查找打印编号 
	Dy find_dy(int orderid);
	 //插入打印编号 
	void add_dy(Dy dy);
	//删除编号
	void delete_dy(Dy dy);

}
