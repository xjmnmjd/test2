package com.sojson.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.sojson.common.model.UUser;
import com.sojson.core.mybatis.page.Pagination;
import com.sojson.permission.bo.URoleBo;
import com.sojson.permission.bo.UserRoleAllocationBo;

public interface UUserService {

	int deleteByPrimaryKey(Long id);

	UUser insert(UUser record);

	UUser insertSelective(UUser record);

	UUser selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UUser record);

	int updateByPrimaryKey(UUser record);

	UUser login(String email, String pswd);

	UUser findUserByEmail(String email);

	Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo,
			Integer pageSize);

	Map<String, Object> deleteUserById(String ids);

	Map<String, Object> updateForbidUserById(Long id, Long status);

	Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
			Integer pageNo, Integer pageSize);

	List<URoleBo> selectRoleByUserId(Long id);

	Map<String, Object> addRole2User(Long userId, String ids);

	Map<String, Object> deleteRoleByUserIds(String userIds);

	/**
	 * 添加用户
	 * 
	 */
	void adduser(UUser user);

	/**
	 * 用户列表
	 * 
	 */
	List<UUser> userList(Map<String, Object> map1);

	/**
	 * 用户数量
	 * 
	 */
	int getCountUser();

	/**
	 * 单个查询用户
	 * 
	 */
	UUser findOne_user(Long Id);

	/**
	 * 修改用户
	 * 
	 */
	void updateUser(UUser user);

	/**
	 * 用户离职
	 * 
	 */
	void update_user_leave(UUser user);

	/**
	 * 查询客服员
	 * 
	 */
	List<UUser> user_kfy();

	/**
	 * 删除用户
	 * 
	 */
	void delete_User(long id);

}
