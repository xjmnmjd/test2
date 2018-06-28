package com.sojson.order.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sojson.common.model.Order;
import com.sojson.common.model.Pay;

public class ExcelOperate_xx {

	/**
	 * 创建Excel导出详细报表
	 * 
	 * @param list
	 *            数据
	 */
	public static void createExcel(List<Pay> list,
			HttpServletResponse response) {
		// 创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workbook.createSheet("订单报表");
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
		headCell.setCellValue("车牌");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(2);
		headCell.setCellValue("支付时间");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(3);
		headCell.setCellValue("支付金额(元)");
		headCell.setCellStyle(cellStyle);  
		
		headCell = hssfRow.createCell(4);
		headCell.setCellValue("支付类型");
		headCell.setCellStyle(cellStyle);
		
		headCell = hssfRow.createCell(5);
		headCell.setCellValue("支付种类");
		headCell.setCellStyle(cellStyle);
		
		headCell = hssfRow.createCell(6);
		headCell.setCellValue("说明");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(7);
		headCell.setCellValue("客服员");
		headCell.setCellStyle(cellStyle);

		// 添加数据内容
		for (int i = 0; i < list.size(); i++) {
			hssfRow = sheet.createRow((int) i + 1);
			Pay pay = new Pay();
			pay = list.get(i);
			// 创建单元格，并设置值
			String owner="无设置";
			if(pay.getOrder()!=null){
				owner=pay.getOrder().getOwner();
			}
			HSSFCell cell = hssfRow.createCell(0);
			cell.setCellValue(owner);
			cell.setCellStyle(cellStyle);
			String licenseplate="无设置";
			if(pay.getOrder()!=null){
				licenseplate=pay.getOrder().getLicenseplate();
			}
			cell = hssfRow.createCell(1);
			cell.setCellValue(licenseplate);
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(2);
			cell.setCellValue(pay.getPay_time());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(3);
			cell.setCellValue(pay.getPay_money());
			cell.setCellStyle(cellStyle);
			   String pay_type="";
               if(pay.getPay_type()==1){
            	   pay_type="线下支付"; 
               }
               if(pay.getPay_type()==2){
            	   pay_type="支出"; 
               }
               if(pay.getPay_type()==4){
            	   pay_type="线上支付"; 
               }
			cell = hssfRow.createCell(4);
			cell.setCellValue(pay_type);
			cell.setCellStyle(cellStyle);
			   String pay_kind="";
               if(pay.getPay_kind()==1){
            	   pay_kind="委托服务费"; 
               }
               if(pay.getPay_kind()==2){
            	   pay_kind="上线检测费"; 
               }
               if(pay.getPay_kind()==3){
            	   pay_kind="违章费"; 
               }
               if(pay.getPay_kind()==4){
            	   pay_kind="余款"; 
               }
               if(pay.getPay_kind()==5){
            	   pay_kind="其他"; 
               }
			cell = hssfRow.createCell(5);
			cell.setCellValue(pay_kind);
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(6);
			cell.setCellValue(pay.getRemark());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(7);
			cell.setCellValue(pay.getCustomer());
			cell.setCellStyle(cellStyle);


		}

		// 保存Excel文件
		try {
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ "shjw_report.xls");
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			// OutputStream outputStream = new
			// FileOutputStream("D:/order_fin.xls");
			workbook.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createSheetExcel(List<Pay> list,
			HttpServletResponse response) {
		// 创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workbook.createSheet("收入");
		HSSFSheet sheet1 = workbook.createSheet("支出");
		// 添加表头行
		HSSFRow hssfRow = sheet.createRow(0);
		HSSFRow hssfRow1 = sheet1.createRow(0);
		// 设置单元格格式居中
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 添加表头内容
		HSSFCell headCell = hssfRow.createCell(0);

		headCell.setCellValue("车主");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(1);
		headCell.setCellValue("车牌");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(2);
		headCell.setCellValue("支付时间");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(3);
		headCell.setCellValue("支付金额(元)");
		headCell.setCellStyle(cellStyle);  
		
		headCell = hssfRow.createCell(4);
		headCell.setCellValue("支付类型");
		headCell.setCellStyle(cellStyle);
		
		headCell = hssfRow.createCell(5);
		headCell.setCellValue("支付种类");
		headCell.setCellStyle(cellStyle);
		
		headCell = hssfRow.createCell(6);
		headCell.setCellValue("说明");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(7);
		headCell.setCellValue("客服员");
		headCell.setCellStyle(cellStyle);
		
		HSSFCell headCell1 = hssfRow1.createCell(0);

		headCell1.setCellValue("车主");
		headCell1.setCellStyle(cellStyle);

		headCell1 = hssfRow1.createCell(1);
		headCell1.setCellValue("车牌");
		headCell1.setCellStyle(cellStyle);

		headCell1 = hssfRow1.createCell(2);
		headCell1.setCellValue("支付时间");
		headCell1.setCellStyle(cellStyle);

		headCell1 = hssfRow1.createCell(3);
		headCell1.setCellValue("支付金额(元)");
		headCell1.setCellStyle(cellStyle);  
		
		headCell1 = hssfRow1.createCell(4);
		headCell1.setCellValue("支付类型");
		headCell1.setCellStyle(cellStyle);
		
		headCell1 = hssfRow1.createCell(5);
		headCell1.setCellValue("支付种类");
		headCell1.setCellStyle(cellStyle);
		
		headCell1 = hssfRow1.createCell(6);
		headCell1.setCellValue("说明");
		headCell1.setCellStyle(cellStyle);

		headCell1 = hssfRow1.createCell(7);
		headCell1.setCellValue("客服员");
		headCell1.setCellStyle(cellStyle);

		// 添加数据内容
		for (int i = 0; i < list.size(); i++) {
			hssfRow = sheet.createRow((int) i + 1);
			Pay pay = new Pay();
			pay = list.get(i);
			// 创建单元格，并设置值
			String owner="无设置";
			if(pay.getOrder()!=null){
				owner=pay.getOrder().getOwner();
			}
			HSSFCell cell = hssfRow.createCell(0);
			cell.setCellValue(owner);
			cell.setCellStyle(cellStyle);
			String licenseplate="无设置";
			if(pay.getOrder()!=null){
				licenseplate=pay.getOrder().getLicenseplate();
			}
			cell = hssfRow.createCell(1);
			cell.setCellValue(licenseplate);
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(2);
			cell.setCellValue(pay.getPay_time());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(3);
			cell.setCellValue(pay.getPay_money());
			cell.setCellStyle(cellStyle);
			   String pay_type="";
               if(pay.getPay_type()==1){
            	   pay_type="线下支付"; 
               }
               if(pay.getPay_type()==2){
            	   pay_type="支出"; 
               }
               if(pay.getPay_type()==4){
            	   pay_type="线上支付"; 
               }
			cell = hssfRow.createCell(4);
			cell.setCellValue(pay_type);
			cell.setCellStyle(cellStyle);
			   String pay_kind="";
               if(pay.getPay_kind()==1){
            	   pay_kind="委托服务费"; 
               }
               if(pay.getPay_kind()==2){
            	   pay_kind="上线检测费"; 
               }
               if(pay.getPay_kind()==3){
            	   pay_kind="违章费"; 
               }
               if(pay.getPay_kind()==4){
            	   pay_kind="余款"; 
               }
               if(pay.getPay_kind()==5){
            	   pay_kind="其他"; 
               }
			cell = hssfRow.createCell(5);
			cell.setCellValue(pay_kind);
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(6);
			cell.setCellValue(pay.getRemark());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(7);
			cell.setCellValue(pay.getCustomer());
			cell.setCellStyle(cellStyle);


		}

		// 保存Excel文件
		try {
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ "shjw_report.xls");
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			// OutputStream outputStream = new
			// FileOutputStream("D:/order_fin.xls");
			workbook.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取Excel
	 * 
	 * @return 数据集合
	 */
	private static List<Order> readExcel() {
		List<Order> list = new ArrayList<Order>();
		HSSFWorkbook workbook = null;

		try {
			// 读取Excel文件
			InputStream inputStream = new FileInputStream("D:/order_fin.xls");
			workbook = new HSSFWorkbook(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 循环工作表
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}

				// 将单元格中的内容存入集合
				Order order = new Order();

				HSSFCell cell = hssfRow.getCell(0);
				if (cell == null) {
					continue;
				}
				order.setOrderid((int) cell.getNumericCellValue());

				cell = hssfRow.getCell(1);
				if (cell == null) {
					continue;
				}
				order.setOwner(cell.getStringCellValue());

				cell = hssfRow.getCell(2);
				if (cell == null) {
					continue;
				}
				order.setLicenseplate(cell.getStringCellValue());

				cell = hssfRow.getCell(3);
				if (cell == null) {
					continue;
				}
				order.setTotalorder(cell.getNumericCellValue());

				cell = hssfRow.getCell(4);
				if (cell == null) {
					continue;
				}
				order.setCopetotal(cell.getNumericCellValue());

				cell = hssfRow.getCell(5);
				if (cell == null) {
					continue;
				}
				order.setOnlinepayment(cell.getNumericCellValue());

				cell = hssfRow.getCell(6);
				if (cell == null) {
					continue;
				}
				order.setOfflinepayment(cell.getNumericCellValue());

				cell = hssfRow.getCell(7);
				if (cell == null) {
					continue;
				}
				order.setPay(cell.getNumericCellValue());

				cell = hssfRow.getCell(8);
				if (cell == null) {
					continue;
				}
				order.setSubmittime(cell.getStringCellValue());

				cell = hssfRow.getCell(9);
				if (cell == null) {
					continue;
				}
				order.setBill_a(cell.getStringCellValue());

				list.add(order);
			}
		}
		return list;
	}
}
