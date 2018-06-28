package com.sojson.order.service;

import com.sojson.common.model.Kd;

public interface KdService {
    /*
     * 添加快递
     * */
	void addKd(Kd kd);
	 /*
     * 查询快递
     * */
	Kd find_kd(int parseInt);
	 /*
     * 修改快递
     * */
	void update_kd(Kd kd);

}
