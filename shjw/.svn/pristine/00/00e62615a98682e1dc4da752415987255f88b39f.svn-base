package com.sojson.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.InforMapper;
import com.sojson.common.model.Infor;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.system.service.MyInforService;
@Service
public class MyInforServiceImpl extends BaseMybatisDao<InforMapper> implements MyInforService{
    @Autowired
    InforMapper inforMapper;
    /*
     * 留言表信息查询
     * */
	@Override
	public List<Infor> find_inforList() {
		return inforMapper.find_inforList();
	}
    /*
     * 留言表信息修改
     * */	
	@Override
	public void update_ly(Infor infor) {
		inforMapper.update_ly(infor);
	}
	   /*
     * 留言表信息删除
     * */	
	@Override
	public void deleteInfor(int id) {
		inforMapper.deleteInfor(id);		
	}
}
