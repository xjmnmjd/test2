package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.UUser;
import com.sojson.permission.bo.URoleBo;

public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

	UUser login(Map<String, Object> map);

	UUser findUserByEmail(String email);

	List<URoleBo> selectRoleByUserId(Long id);
	   //添加用户接口
		void addUser(UUser user);
	    //用户列表
		List<UUser> findAllUser(Map<String, Object> map);
		  //用户列表数量
		int getCountUser();

	    //修改用户
		void updateUser(UUser user);
	    //离职
		void update_user_leave(UUser user);
		
		UUser findOne_user(Long id);
		//查询客服员
		List<UUser> user_kfy();
        //删除用户
		void delete_User(long id);
	

}