package com.sojson.car.carUtil;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sojson.common.model.Car;

public class DaocExcelOperate {

	  /**
	   * 创建 车辆导出Excel
	   * 
	   * @param list
	   *      数据
	   */
	  public static void createExcel(List<Car> list,HttpServletResponse response) {
	    // 创建一个Excel文件
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 创建一个工作表
	    HSSFSheet sheet = workbook.createSheet("车辆表");
	    // 添加表头行
	    HSSFRow hssfRow = sheet.createRow(0);
	    // 设置单元格格式居中
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	 
	    // 添加表头内容
	    HSSFCell headCell = hssfRow.createCell(0);
	    
	    headCell.setCellValue("车主");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(1);
	    headCell.setCellValue("手机号码");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(2);
	    headCell.setCellValue("车牌号");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(3);
	    headCell.setCellValue("车辆类型");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(4);
	    headCell.setCellValue("使用性质");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(5);
	    headCell.setCellValue("注册日期");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(6);
	    headCell.setCellValue("车架号");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(7);
	    headCell.setCellValue("发动机号");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(8);
	    headCell.setCellValue("品牌型号");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(9);
	    headCell.setCellValue("检测站");
	    headCell.setCellStyle(cellStyle);
	    
	    
	 
	    // 添加数据内容
	    for (int i = 0; i < list.size(); i++) {
	      hssfRow = sheet.createRow((int) i + 1);
	       Car car=new Car();
	       car=list.get(i);
	      // 创建单元格，并设置值
	      HSSFCell cell = hssfRow.createCell(0);
	      cell.setCellValue(car.getCar_owner());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(1);
	      cell.setCellValue(car.getCar_phone());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(2);
	      cell.setCellValue(car.getCar_plate_number());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(3);
	      cell.setCellValue(car.getCar_type());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(4);
	      cell.setCellValue(car.getCar_property());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(5);
	      cell.setCellValue(car.getCar_regdate());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(6);
	      cell.setCellValue(car.getCar_vin_no());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(7);
	      cell.setCellValue(car.getCar_engine_No());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(8);
	      cell.setCellValue(car.getCar_brand_model());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(9);
	      cell.setCellValue(car.getCar_station());
	      cell.setCellStyle(cellStyle);
	    }
	 
	    // 保存Excel文件
	    try {	    
	      response.setContentType("application/vnd.ms-excel; charset=utf-8");
	      response.setHeader("Content-Disposition","attachment;filename="+"car.xls");
	      response.setCharacterEncoding("utf-8");
	      OutputStream os=response.getOutputStream();
	      //OutputStream outputStream = new FileOutputStream("D:/order_fin.xls");
	      workbook.write(os);
	      os.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	 

}
