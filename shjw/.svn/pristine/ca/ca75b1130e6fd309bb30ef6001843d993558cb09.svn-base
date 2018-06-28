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

public class ExcelOperate {

	/**
	 * 创建Excel
	 * 
	 * @param list
	 *            数据
	 */
	public static void createExcel(List<Order> list,
			HttpServletResponse response) {
		// 创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workbook.createSheet("订单表");
		// 添加表头行
		HSSFRow hssfRow = sheet.createRow(0);
		// 设置单元格格式居中
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 添加表头内容
		HSSFCell headCell = hssfRow.createCell(0);

		headCell.setCellValue("订单编号");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(1);
		headCell.setCellValue("车主");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(2);
		headCell.setCellValue("车牌");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(3);
		headCell.setCellValue("订单总额(元)");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(4);
		headCell.setCellValue("应付总额");
		headCell.setCellStyle(cellStyle);
		
		headCell = hssfRow.createCell(5);
		headCell.setCellValue("实际支付");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(6);
		headCell.setCellValue("线上支付");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(7);
		headCell.setCellValue("线下支付");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(8);
		headCell.setCellValue("支出");
		headCell.setCellStyle(cellStyle);

		headCell = hssfRow.createCell(9);
		headCell.setCellValue("付款时间");
		headCell.setCellStyle(cellStyle);

		// 添加数据内容
		for (int i = 0; i < list.size(); i++) {
			hssfRow = sheet.createRow((int) i + 1);
			Order order = new Order();
			order = list.get(i);
			// 创建单元格，并设置值
			HSSFCell cell = hssfRow.createCell(0);
			cell.setCellValue(order.getOrderid());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(1);
			cell.setCellValue(order.getOwner());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(2);
			cell.setCellValue(order.getLicenseplate());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(3);
			cell.setCellValue(order.getTotalorder());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(4);
			cell.setCellValue(order.getCopetotal());
			cell.setCellStyle(cellStyle);
			
			cell = hssfRow.createCell(5);
			cell.setCellValue(order.getPayment_amount());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(6);
			cell.setCellValue(order.getOnlinepayment());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(7);
			cell.setCellValue(order.getOfflinepayment());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(8);
			cell.setCellValue(order.getPay());
			cell.setCellStyle(cellStyle);

			cell = hssfRow.createCell(9);
			cell.setCellValue(order.getSubmittime());
			cell.setCellStyle(cellStyle);

		}

		// 保存Excel文件
		try {
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ "order_fn.xls");
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
