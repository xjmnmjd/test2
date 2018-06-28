package com.sojson.car.carUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sojson.common.model.Car;

public class CarExcelOperate {
	/**
	 * 读取Excel
	 * 
	 * @return 数据集合
	 */
	public static List<Car> readExcel(String excelPath) {
		List<Car> listwt = new ArrayList<Car>();
		HSSFWorkbook workbook = null;

		try {
			// 读取Excel文件
			InputStream inputStream = new FileInputStream(excelPath);
			workbook = new HSSFWorkbook(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 循环工作表
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		if (hssfSheet == null) {
			return null;
		}
		HSSFRow row;
		HSSFCell ce;
		int total = hssfSheet.getLastRowNum();
		for (short i = 1; i <= total; i++) {
			Car car = new Car();
			row = hssfSheet.getRow(i);
			ce = row.getCell(0);
			if (ce != null) {
				car.setCar_owner(ce.getStringCellValue());
			}
			ce = row.getCell(1);
			if (ce != null) {
				car.setCar_phone(ce.getStringCellValue());
			}
			ce = row.getCell(2);
			if (ce != null) {
				car.setCar_plate_number(ce.getStringCellValue());
			}
			ce = row.getCell(3);
			if (ce != null) {
				car.setCar_type(ce.getStringCellValue());
			}
			ce = row.getCell(4);
			if (ce != null) {
				car.setCar_property(ce.getStringCellValue());
			}
			ce = row.getCell(5);
			if (ce != null) {
				car.setCar_regdate(ce.getStringCellValue());
			}
			ce = row.getCell(6);
			if (ce != null) {
				car.setCar_vin_no(ce.getStringCellValue());
			}
			ce = row.getCell(7);
			if (ce != null) {
				car.setCar_engine_No(ce.getStringCellValue());
			}
			ce = row.getCell(8);
			if (ce != null) {
				car.setCar_brand_model(ce.getStringCellValue());
			}
			ce = row.getCell(9);
			if (ce != null) {
				car.setCar_station(ce.getStringCellValue());
			}
			listwt.add(car);
		}
		return listwt;
	}
}
