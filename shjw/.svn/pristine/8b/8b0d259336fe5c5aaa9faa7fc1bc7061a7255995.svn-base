package com.sojson.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.KdMapper;
import com.sojson.common.model.Kd;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.KdService;
@Service
public class KdserviceImpl extends BaseMybatisDao<KdMapper> implements KdService{
   @Autowired
   KdMapper kdMapper;
   @Override
   public void addKd(Kd kd) {
	   kdMapper.addKd(kd);
   }
@Override
public Kd find_kd(int orderid) {
	return kdMapper.find_kd(orderid);
}
@Override
public void update_kd(Kd kd) {
	kdMapper.update_kd(kd);
} 
}
