package com.sojson.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.HfMapper;
import com.sojson.common.model.Hf;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.HfService;
@Service
public class HfServiceImpl extends BaseMybatisDao<HfMapper> implements HfService{
	@Autowired
    HfMapper hfMapper;

	@Override
	public void add_hf(Hf hf) {
        hfMapper.add_hf(hf);		
	}

	@Override
	public List<Hf> find_hf(int orderid) {
		// TODO Auto-generated method stub
		return hfMapper.find_hf(orderid);
	}
	
	
}
