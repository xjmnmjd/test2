package com.sojson.message.messUtil;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sojson.common.model.Car;
import com.sojson.common.model.Message;

public class MessExcelOperate {

	  /**
	   * 创建 短信导出Excel
	   * 
	   * @param list
	   *      数据
	   */
	  public static void createExcel(List<Message> list,HttpServletResponse response) {
	    // 创建一个Excel文件
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 创建一个工作表
	    HSSFSheet sheet = workbook.createSheet("短信表");
	    // 添加表头行
	    HSSFRow hssfRow = sheet.createRow(0);
	    // 设置单元格格式居中
	    HSSFCellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	 
	    // 添加表头内容
	    HSSFCell headCell = hssfRow.createCell(0);
	    
	    headCell.setCellValue("发送时间");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(1);
	    headCell.setCellValue("手机号码");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(2);
	    headCell.setCellValue("车主");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(3);
	    headCell.setCellValue("车牌");
	    headCell.setCellStyle(cellStyle);
	 
	    headCell = hssfRow.createCell(4);
	    headCell.setCellValue("短信内容");
	    headCell.setCellStyle(cellStyle);
	    
	    headCell = hssfRow.createCell(5);
	    headCell.setCellValue("发送状态");
	    headCell.setCellStyle(cellStyle);
	 
	    
	    
	 
	    // 添加数据内容
	    for (int i = 0; i < list.size(); i++) {
	      hssfRow = sheet.createRow((int) i + 1);
	       Message message=new Message();
	       message=list.get(i);
	      // 创建单元格，并设置值
	      HSSFCell cell = hssfRow.createCell(0);
	      cell.setCellValue(message.getMessage_time());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(1);
	      cell.setCellValue(message.getMessage_phone());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(2);
	      cell.setCellValue(message.getMessage_owner());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(3);
	      cell.setCellValue(message.getMessage_plate());
	      cell.setCellStyle(cellStyle);
	 
	      cell = hssfRow.createCell(4);
	      cell.setCellValue(message.getMessage_content());
	      cell.setCellStyle(cellStyle);
	      
	      cell = hssfRow.createCell(5);
	      cell.setCellValue(message.getMessage_status());
	      cell.setCellStyle(cellStyle);
	 

	    }
	 
	    // 保存Excel文件
	    try {	    
	      response.setContentType("application/vnd.ms-excel; charset=utf-8");
	      response.setHeader("Content-Disposition","attachment;filename="+"message.xls");
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
