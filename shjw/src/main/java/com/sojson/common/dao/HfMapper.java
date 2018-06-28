package com.sojson.common.dao;

import java.util.List;

import com.sojson.common.model.Hf;

/*
 * author zjf
 * 回访记录接口
 * 2017-8-29
 */
public interface HfMapper {
    //添加回访内容
	void add_hf(Hf hf);

	List<Hf> find_hf(int orderid);

}
