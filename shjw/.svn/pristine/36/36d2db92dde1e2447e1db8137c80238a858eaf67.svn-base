package com.sojson.system.eutil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.sojson.common.model.Wt;

public class ExcelOperate {
	/**
	 * 读取Excel
	 * 
	 * @return 数据集合
	 */
	public static List<Wt> readExcel() {
		List<Wt> listwt = new ArrayList<Wt>();
		HSSFWorkbook workbook = null;
		
		try {
			// 读取Excel文件
			InputStream inputStream = new FileInputStream("F:\\zhu.xls");
			workbook = new HSSFWorkbook(inputStream);
			//workbook = new XSSFWorkbook(excelFile);
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
		for (short i = 0; i <= total; i++) {
			Wt wt = new Wt();
			row = hssfSheet.getRow(i);
			ce = row.getCell(0);
			if (ce != null) {
			wt.setWt_area(ce.getStringCellValue());
			}
			ce = row.getCell(1);
			if (ce != null) {
			wt.setWt_car_number(ce.getStringCellValue());
			}
			ce = row.getCell(2);
			if (ce != null) {
			wt.setWt_mj_fee(ce.getNumericCellValue());
			}
			ce = row.getCell(3);
			if (ce != null) {
			wt.setWt_mj_zl(ce.getStringCellValue());
			}
			ce = row.getCell(4);
			if (ce != null) {
			wt.setWt_fmj_fee(ce.getNumericCellValue());
			}
			ce = row.getCell(5);
			if (ce != null) {
			wt.setWt_fmj_zl(ce.getStringCellValue());
			}
			ce = row.getCell(6);
			if (ce != null) {
				wt.setWt_remark(ce.getStringCellValue());
			}
			ce = row.getCell(7);
			if (ce != null) {
				wt.setWt_province(ce.getStringCellValue());
			}
			listwt.add(wt);
		}
		System.out.println(total);
	
		return listwt;
	}
}
