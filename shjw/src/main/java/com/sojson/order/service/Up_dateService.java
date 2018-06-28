package com.sojson.order.service;

import com.sojson.common.model.Up_date;

public interface Up_dateService {
/*
 * 查找时间
 * */
	Up_date findUp_date(String orderno);
	/*
	 * 添加时间
	 * */
	void addUp_date(Up_date up_date1);
	/*
	 * 修改时间
	 * */
	void update_up_date(Up_date up_date1);

}
