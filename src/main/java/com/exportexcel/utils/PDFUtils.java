package com.exportexcel.utils;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.lowagie.text.Cell;

/**
 * @author TANGSHUAI
 * @date 2020年12月12日 下午11:29:46 pdf工具类
 */
@Repository
public class PDFUtils {

	// 蓝色字体
	public static Font blueFont;
	// 小三号
	public static Font smallThreeFont;
	// 小三号加粗
	public static Font smallThreeGreenFont;
	// 五号
	public static Font fiveFont;
	// 五号
	public static Font smallFiveFont;
	// 五号
	public static Font smallSixFont;
	// 小四号 加粗
	public static Font greenFont;
	// 小四号
	public static Font messFont;
	// 标题加粗 四号
	public static Font titleFont;
	static {
		try {
			// 设置中文
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			blueFont = new Font(bfChinese);
			blueFont.setColor(BaseColor.BLUE);
			blueFont.setSize(5);
			smallThreeFont = new Font(bfChinese, 15);
			smallThreeFont.setColor(BaseColor.BLACK);
			smallThreeGreenFont = new Font(bfChinese, 15, Font.BOLD);
			smallThreeGreenFont.setColor(BaseColor.BLACK);
			fiveFont = new Font(bfChinese);
			fiveFont.setColor(BaseColor.BLACK);
			fiveFont.setSize(10.5f);
			smallFiveFont = new Font(bfChinese);
			smallFiveFont.setColor(BaseColor.BLACK);
			smallFiveFont.setSize(9);
			smallSixFont = new Font(bfChinese);
			smallSixFont.setColor(BaseColor.BLACK);
			smallSixFont.setSize(6.5f);
			greenFont = new Font(bfChinese, 12, Font.BOLD);
			greenFont.setColor(BaseColor.BLACK);
			messFont = new Font(bfChinese, 12);
			messFont.setColor(BaseColor.BLACK);
			titleFont = new Font(bfChinese, 20, Font.BOLD);
			titleFont.setColor(BaseColor.BLACK);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 创建table
	 * 
	 * @param column
	 * @return
	 */
	public PdfPTable createTable(Integer column) {
		PdfPTable table = new PdfPTable(column);
		// 设置表格宽度
		table.setWidthPercentage(50);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);
		return table;
	}

	/**
	 * 创建table,设置宽度
	 * 
	 * @param column
	 * @param wdith
	 * @return
	 */
	public PdfPTable createTable(Integer column, Integer wdith) {
		PdfPTable table = new PdfPTable(column);
		// 设置宽度
		table.setWidthPercentage(wdith);
		// 设置间距
		table.setSpacingBefore(0);
		return table;
	}

	/**
	 * 创建单元格，标题
	 * 
	 * @param name
	 * @param font
	 * @return
	 */
	public PdfPCell createPdfPCell(String name, Font font) {
		PdfPCell cell;
		// 第一行
		cell = new PdfPCell(new Paragraph(name, font));
		// 设置文字可以居中
		cell.setUseAscender(true);
		// 设置水平居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		// 设置垂直居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		// 设置边框
		cell.setBorder(0);
		return cell;
	}
	/**
	 * 封装标题
	 * @param title
	 * @return
	 */
	public PdfPTable createTitle(String title) {
		PdfPTable createTable = createTable(1);
		PdfPCell createPdfPCell = createPdfPCell(title, PDFUtils.titleFont);
		createTable.addCell(createPdfPCell);
		return createTable;
	}

	/**
	 * 创建单元格，不带边框
	 * 
	 * @param name
	 * @param font
	 * @param colspan
	 * @return
	 */
	public PdfPCell createPdfPCell(String name, Font font, Integer colspan, Integer border) {
		PdfPCell cell;
		cell = new PdfPCell(new Paragraph(name, font));
		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setMinimumHeight(20);// 设置单元格的高度
		cell.setBorderWidth(border);// 设置边框
		cell.setColspan(colspan);// 设置占用几列
		return cell;
	}

	/**
	 * 创建单元格，带边框
	 * 
	 * @param name
	 * @param font
	 * @param colspan
	 * @param border
	 * @return
	 */
	public PdfPCell createPdfPCell(String name, Font font, Integer colspan) {
		PdfPCell cell;
		cell = new PdfPCell(new Paragraph(name, font));
		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setMinimumHeight(20);// 设置单元格的高度
		cell.setColspan(colspan);
		return cell;
	}

	/**
	 * 报账管理创建表格
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	public PdfPTable createExpenseManagement(String title, String content) {
		PdfPTable table = createTable(4, 100);
		PdfPCell cell = createPdfPCell(title, PDFUtils.messFont, 1);
		table.addCell(cell);
		PdfPCell cell2 = createPdfPCell(content, PDFUtils.messFont, 3);
		table.addCell(cell2);
		return table;
	}

	/**
	 * 打印审批人
	 * 
	 * @param name
	 * @param conclusion
	 * @param remark
	 * @param date
	 * @return
	 */
	public PdfPTable createApprover(String name, String conclusion, String remark, String date) {
		PdfPTable table = createTable(4, 100);
		PdfPCell cell = createPdfPCell("审批人:" + name, PDFUtils.messFont, 1);
		table.addCell(cell);
		PdfPCell cell2 = createPdfPCell("审批结论:" + conclusion, PDFUtils.messFont, 1);
		table.addCell(cell2);
		PdfPCell cell3 = createPdfPCell("备注:" + remark, PDFUtils.messFont, 1);
		table.addCell(cell3);
		PdfPCell cell4 = createPdfPCell("审批时间:" + date, PDFUtils.messFont, 1);
		table.addCell(cell4);
		return table;
	}
	/**
	 * 公司名称，申请日期
	 * 打印时间，打印人
	 * @param company
	 * @param date
	 * @return
	 */
	public PdfPTable createHead(String content,String content2) {
		PdfPTable createTable = createTable(3, 100);
		PdfPCell createPdfPCell = createPdfPCell(content, PDFUtils.messFont, 1, 0);
		createTable.addCell(createPdfPCell);
		PdfPCell createPdfPCell2 = createPdfPCell(content2, PDFUtils.messFont, 2, 0);
		createTable.addCell(createPdfPCell2);
		return createTable;
	}

}
