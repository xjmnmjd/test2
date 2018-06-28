package com.sojson.wx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Infor;

/**
 * 
 * @author xj
 * @version 1.0 2017-11-30 留言接口
 */
public interface InforService {
	/*
	 * 通过openid获取留言
	 */
	public List<Infor> ListInforByOpenid(@Param("openid") String openid);

	/*
	 * 添加留言
	 */
	public void insert(Infor infor);
}
