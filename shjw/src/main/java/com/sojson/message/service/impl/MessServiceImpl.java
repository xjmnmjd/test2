package com.sojson.message.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.MessMapper;
import com.sojson.common.model.Message;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.message.service.MessService;
@Service
public class MessServiceImpl extends BaseMybatisDao<MessMapper> implements MessService{
  @Autowired
  MessMapper messMapper;

@Override
public int getCountMessage() {
	return messMapper.getCountMessage();
}

@Override
public List<Map<String, Object>> messList(Map<String, Object> map) {
	return messMapper.messList(map);
}

@Override
public void delete_Mess(int messid) {
	messMapper.delete_Mess(messid);
}

@Override
public List<Message> findAllMess() {
	return messMapper.findAllMess();
}

@Override
public void add_message(Message message) {
	messMapper.add_message(message);
}
}
