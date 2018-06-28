package com.sojson.car.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sojson.car.carUtil.CarExcelOperate;
import com.sojson.car.carUtil.DaocExcelOperate;
import com.sojson.car.carUtil.MbExcelOperate;
import com.sojson.car.service.CarService;
import com.sojson.common.controller.BaseController;
import com.sojson.common.model.Car;

/**
 * 车辆管理
 * 
 * @author zjf
 * @version 1.0,2017年9月15日
 * 
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("car")
public class CarController extends BaseController {
	@Resource
	CarService carService;

	/**
	 * 车辆管理
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "carList")
	public ModelAndView carList(ModelMap map, String pageIndex, Car car) {
		if (pageIndex == null || pageIndex.equals("")) {
			pageIndex = "1";
		}
		int apageindex = Integer.parseInt(pageIndex);
		int counts = carService.getCountCar();
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		int page = (apageindex - 1) * 10;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("page", page);
		map1.put("car", car);
		List<Map<String, Object>> list = carService.carList(map1);
		map.put("carlist", list);
		map.put("pageIndex", apageindex);
		map.put("total", total);
		return new ModelAndView("car/car");
	}

	/**
	 * 添加车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "addCar")
	@ResponseBody
	public Map<String, Object> addCar(Car car) {
		carService.addCar(car);
		return resultMap;
	}

	/**
	 * 查找车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "findCar")
	@ResponseBody
	public Map<String, Object> findCar(String carid) {
		Car car = carService.findCar(Integer.parseInt(carid));
		resultMap.put("car", car);
		return resultMap;
	}

	/**
	 * 修改车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateCar")
	@ResponseBody
	public Map<String, Object> updateCar(String carid, Car car) {
		car.setCarid(Integer.parseInt(carid));
		carService.updateCar(car);
		return resultMap;
	}

	/**
	 * 删除车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteCar")
	@ResponseBody
	public Map<String, Object> deleteCar(String carid) {
		carService.deleteCar(Integer.parseInt(carid));
		return resultMap;
	}

	/**
	 * 导出车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "daoc_car")
	@ResponseBody
	public Map<String, Object> daoc_car(HttpServletResponse response,
			HttpServletRequest request) {
		List<Car> car_list = carService.findAll_Car();
		DaocExcelOperate.createExcel(car_list, response);
		return resultMap;
	}

	/**
	 * 模板下载
	 * 
	 * @return
	 */
	@RequestMapping(value = "mb_load")
	@ResponseBody
	public Map<String, Object> mb_load(HttpServletResponse response,
			HttpServletRequest request) {
		List<Car> car_list = carService.findAll_Car();
		MbExcelOperate.createExcel(car_list, response);
		return resultMap;
	}

	/**
	 * 上传文件导入
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "onload")
	public ModelAndView onload(ModelMap map,
			@RequestParam("uploadfile") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String p = request.getContextPath() + "/upload";
		// MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
		if (!file.isEmpty()) {
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
			String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
			String path1 = request.getSession().getServletContext()
					.getRealPath("/upload/" + filename);// 存放位置
			File destFile = new File(path1);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils
						.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<Car> carlist = CarExcelOperate.readExcel(path1);
			for (Car car : carlist) {
				carService.addCar(car);
			}
			destFile.delete();
		}
		int counts = carService.getCountCar();
		int page = 0;
		int total = (counts % 10 == 0) ? (counts / 10) : (counts / 10 + 1);
		Car car = new Car();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.clear();
		map1.put("page", page);
		map1.put("car", car);
		List<Map<String, Object>> list = carService.carList(map1);
		map.put("carlist", list);
		map.put("pageIndex", 1);
		map.put("total", total);

		return new ModelAndView("car/car");
	}

}
