package com.sojson.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.InforMapper;
import com.sojson.common.model.Infor;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.wx.service.InforService;

@Service
public class InforServiceImpl extends BaseMybatisDao<InforMapper> implements
		InforService {

	@Autowired
	InforMapper billMapper;

	@Override
	public List<Infor> ListInforByOpenid(String openid) {
		return billMapper.ListInforByOpenid(openid);
	}

	@Override
	public void insert(Infor infor) {
		billMapper.insert(infor);
	}

}
