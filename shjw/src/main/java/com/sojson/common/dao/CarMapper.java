package com.sojson.common.dao;

import java.util.List;
import java.util.Map;

import com.sojson.common.model.Car;

public interface CarMapper {
    /*
     * 车辆数量接口
     * */
	int getCountCar();
    /*
     * 车辆列表接口
     * */
	List<Map<String, Object>> carList(Map<String, Object> map);
    /*
     * 添加车辆接口
     * */
	void addCar(Car car);
    /*
     * 查找车辆接口
     * */
	Car findCar(int carid);
    /*
     * 修改车辆接口
     * */
	void updateCar(Car car);
    /*
     * 删除车辆接口
     * */
	void deleteCar(int carid);
    /*
     * 查询车辆接口
     * */
	List<Car> findAll_Car();

}
