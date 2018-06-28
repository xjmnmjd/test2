package com.sojson.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sojson.common.model.Infor;

/**
 * @author xj
 * @version 1.0 2017-8-24 留言mapper接口
 */
public interface InforMapper {
	/*
	 * 通过openid获取留言
	 */
	public List<Infor> ListInforByOpenid(@Param("openid") String openid);

	/*
	 * 添加留言
	 */
	public void insert(Infor infor);
	/*
	 * 留言列表查询
	 */
	public List<Infor> find_inforList();
	/*
	 * 留言列表修改
	 */
	public void update_ly(Infor infor);
	/*
	 * 留言列表删除
	 */
	public void deleteInfor(int id);

}
