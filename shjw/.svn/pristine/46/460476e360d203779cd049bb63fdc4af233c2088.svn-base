package com.sojson.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.WZ_img;

/**
 * @author xj
 * @version 1.0 2017-12-1 违章图片mapper接口
 */
public interface WZ_imgMapper {
	/*
	 * 通过订单号获取违章图片
	 */
	public List<WZ_img> getWZ_imgByOrderNo(@Param("order_no") String order_no);
	/*
	 * 添加违章图片违章图片 zjf
	 */
	public void insert_img(WZ_img wz_img);
	/*
	 * 添加订单号查询违章图片 zjf
	 */
	public List<WZ_img> find_wz_img(String orderno);

}
