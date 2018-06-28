package com.sojson.car.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sojson.car.service.CarService;
import com.sojson.common.dao.CarMapper;
import com.sojson.common.model.Car;
import com.sojson.core.mybatis.BaseMybatisDao;

@Service
public class CarServiceImpl extends BaseMybatisDao<CarMapper> implements
		CarService {
	@Autowired
	CarMapper carMapper;

	/*
	 * 车辆数量查询
	 */
	@Override
	public int getCountCar() {
		return carMapper.getCountCar();
	}

	/*
	 * 车辆列表查询
	 */
	@Override
	public List<Map<String, Object>> carList(Map<String, Object> map) {
		return carMapper.carList(map);
	}

	/*
	 * 添加车辆
	 */
	@Override
	public void addCar(Car car) {
		carMapper.addCar(car);
	}

	/*
	 * 查询车辆
	 */
	@Override
	public Car findCar(int carid) {
		return carMapper.findCar(carid);
	}

	@Override
	public void updateCar(Car car) {
		carMapper.updateCar(car);
	}

	@Override
	public void deleteCar(int carid) {
		carMapper.deleteCar(carid);
	}

	@Override
	public List<Car> findAll_Car() {
		return carMapper.findAll_Car();
	}
}
