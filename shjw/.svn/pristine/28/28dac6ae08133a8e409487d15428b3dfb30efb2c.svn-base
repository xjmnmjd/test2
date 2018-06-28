package com.sojson.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.PbMapper;
import com.sojson.common.model.Pb;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.PbService;
@Service
public class PbServiceImpl extends BaseMybatisDao<PbMapper> implements PbService{
	
   @Autowired
   PbMapper pbMapper;

@Override
public void addPb(Pb b) {
	pbMapper.addPb(b);
  }
/*
 * 删除本周排班
 * */
@Override
public void deletePb(String start_time, String end_time) {
	pbMapper.deletePb(start_time,end_time);
}
/*
 * 查询排班
 * */
@Override
public List<Pb> find_pb(Map<String,Object> map) {
	return pbMapper.find_pb(map);
}
}
