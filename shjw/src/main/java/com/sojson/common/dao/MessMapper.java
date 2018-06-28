package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.Message;

public interface MessMapper {
    /*
     * 短信数量
     * */
	int getCountMessage();
    /*
     * 短信列表
     * */
	List<Map<String, Object>> messList(Map<String, Object> map);
    /*
     * 删除短信
     * */
	void delete_Mess(int messid);
    /*
     * 查询所有短信
     * */
	List<Message> findAllMess();
    /*
     * 添加短信
     * */	
	void add_message(Message message);

}
