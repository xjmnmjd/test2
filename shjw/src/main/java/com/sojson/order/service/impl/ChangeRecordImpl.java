package com.sojson.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.ChangeRecordMapper;
import com.sojson.common.model.ChangeRecord;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.order.service.IChangeRecord;
@Service
public class ChangeRecordImpl extends BaseMybatisDao<ChangeRecordMapper> implements IChangeRecord{
	@Autowired
	ChangeRecordMapper changeRecordMapper;
	/**
	 * @author zjf 2018-2-23
	 * @param 添加修改记录
	 */
	@Override
	public void addChangeRecord(ChangeRecord cr) {
		changeRecordMapper.addChangeRecord(cr);		
	}
	/**
	 * @author zjf 2018-2-24
	 * @param 查找修改记录
	 */
	@Override
	public List<ChangeRecord> findList(Map<String, Object> map) {
		return changeRecordMapper.findList(map);
	}
	/**
	 * @author zjf 2018-2-24
	 * @param map1 
	 * @param 查找修改记录条数
	 */
	@Override
	public int CountRecord() {
		return changeRecordMapper.CountRecord();
	}
}
