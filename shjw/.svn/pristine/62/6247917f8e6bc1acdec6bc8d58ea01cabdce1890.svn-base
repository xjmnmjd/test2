package com.sojson.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.EmailMapper;
import com.sojson.common.model.JW_Email;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.EmailService;
@Service
public class EmailServiceImpl extends BaseMybatisDao<EmailMapper> implements EmailService{
	@Autowired
	EmailMapper emailMapper;

	@Override
	public void add_email(JW_Email jw_Email) {
		emailMapper.add_email(jw_Email);
	}
}
