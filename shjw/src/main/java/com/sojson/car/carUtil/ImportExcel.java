package com.sojson.car.carUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ImportExcel {
	public List<List<List<String>>> importExcel(File file, int cLength) {
		try {
			InputStream xlsIs = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(xlsIs);
			List<List<List<String>>> worksheetList = new ArrayList<List<List<String>>>();
			for (int nSheet = 0; nSheet < hssfWorkbook.getNumberOfSheets(); ++nSheet) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(nSheet);
				if (hssfSheet == null) {
					worksheetList.add(null);
					continue;
				}
				List<List<String>> workSheet = new ArrayList<List<String>>();
				for (int nRow = 0; nRow <= hssfSheet.getLastRowNum(); ++nRow) {
					HSSFRow hssfRow = hssfSheet.getRow(nRow);
					if (hssfRow == null) {
						workSheet.add(null);
						continue;
					}
					List<String> rowList = new ArrayList<String>();
					int len = hssfRow.getLastCellNum();
					len = len > cLength ? len : cLength;
					for (int nCell = 0; nCell < len; ++nCell) {
						HSSFCell xh = hssfRow.getCell(nCell);
						if (xh == null) {
							rowList.add(null);
							continue;
						}
						String cellValue = getVlaue(xh);
						rowList.add(cellValue);
					}
					workSheet.add(rowList);
				}
				worksheetList.add(workSheet);
			}
			return worksheetList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getVlaue(HSSFCell xh) {
		String reValue = null;
		if (xh.getCellType() == xh.CELL_TYPE_BOOLEAN) {
			reValue = String.valueOf(xh.getBooleanCellValue());
		} else if (xh.getCellType() == xh.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(xh)) {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = HSSFDateUtil.getJavaDate(xh.getNumericCellValue());
				reValue = dateformat.format(dt);
			} else {
				reValue = String.valueOf(xh.getNumericCellValue());
			}
		} else {
			reValue = String.valueOf(xh.getStringCellValue());
		}
		return reValue;
	}
}
