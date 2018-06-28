package com.sojson.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.common.dao.MMenuMapper;
import com.sojson.common.model.M_menu;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.permission.service.MMenuService;
@Service
public class MMenuServiceImpl extends BaseMybatisDao<MMenuMapper> implements MMenuService{
   @Autowired
   MMenuMapper mMenuMapper;

  @Override
   public List<M_menu> getAllMenu() {
	return mMenuMapper.getAllMenu();
}

   @Override
   public void addMenu(M_menu menu) {
	mMenuMapper.addMenu(menu);
   }

@Override
public M_menu findMenu(int menuid) {
	return mMenuMapper.findMenu(menuid);
}

@Override
public void updateMenu(M_menu menu) {
	mMenuMapper.updateMenu(menu);
}

@Override
public void deleteMenu(int menuid) {
	mMenuMapper.deleteMenu(menuid);
}

@Override
public int getCountMenu() {
	return mMenuMapper.getCountMenu();
}

@Override
public List<M_menu> getAllMenu_fy(int pageIndex) {
	return mMenuMapper.getAllMenu_fy(pageIndex);
}
}
