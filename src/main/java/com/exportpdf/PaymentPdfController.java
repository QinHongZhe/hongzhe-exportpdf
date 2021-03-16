package com.exportpdf;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exportexcel.utils.PDFUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;

/**
 * @author TANGSHUAI
 * @date 2020年12月4日 下午5:05:11
 * @付款申请打印
 */
@Controller
@RequestMapping("/pdf")
public class PaymentPdfController {

	@Autowired
	private PDFUtils pdfutils;

	@GetMapping(value = "/paymentRequest")
	public void purchaseReceiptPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		// String filename="付款申请.pdf";
		// 下载文件的默认名称
		// String filename="付款申请.pdf";
		// response.setHeader("Content-Disposition", "attachment;filename="
		// +URLEncoder.encode((String) filename, "UTF-8"));
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Document document = new Document();
		//设置A4纸大小
		Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(),421);
		pageSize.rotate();
		document.setPageSize(pageSize);
		
		
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		
		// 创建标题
		PdfPTable createTable = pdfutils.createTable(1);
		PdfPCell createPdfPCell = pdfutils.createPdfPCell("付款申请", PDFUtils.titleFont);
		createTable.addCell(createPdfPCell);
		document.add(createTable);


		PdfPTable createTable2 = pdfutils.createTable(3, 100);
		PdfPCell createPdfPCell2 = pdfutils.createPdfPCell("上海汉沃实业有限公司", PDFUtils.messFont, 1,0);
		createTable2.addCell(createPdfPCell2);
		
		PdfPCell createPdfPCell3 = pdfutils.createPdfPCell("申请日期：" + dateFormat.format(new Date()), PDFUtils.messFont, 2,0);
		createTable2.addCell(createPdfPCell3);
		document.add(createTable2);

		PdfPTable createTable3 = pdfutils.createTable(4, 100);
		PdfPCell createPdfPCell4 = pdfutils.createPdfPCell("申请人:", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell4);

		PdfPCell createPdfPCell5 = pdfutils.createPdfPCell("张三", PDFUtils.messFont, 3);
		createTable3.addCell(createPdfPCell5);
		document.add(createTable3);

		PdfPTable table3 = new PdfPTable(4);
		// 设置宽度
		table3.setWidthPercentage(100);
		// 设置间距
		table3.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell5;
		cell5 = new PdfPCell(new Paragraph("申请部门:", PDFUtils.messFont));
		cell5.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell5.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell5.setMinimumHeight(20);// 设置单元格的高度
		cell5.setColspan(1);
		table3.addCell(cell5);
		// 创建第二列
		PdfPCell cell6;
		cell6 = new PdfPCell(new Paragraph("财务部", PDFUtils.messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置靠左
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setMinimumHeight(20);// 设置单元格的高度
		cell6.setColspan(3);
		table3.addCell(cell6);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(4);
		// 设置宽度
		table4.setWidthPercentage(100);
		// 设置间距
		table4.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell7;
		cell7 = new PdfPCell(new Paragraph("付款事由:", PDFUtils.messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setMinimumHeight(20);// 设置单元格的高度
		cell7.setColspan(1);
		table4.addCell(cell7);

		PdfPCell cell8;
		cell8 = new PdfPCell(new Paragraph("测试付款申请", PDFUtils.messFont));
		cell8.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell8.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell8.setMinimumHeight(20);// 设置单元格的高度
		cell8.setColspan(3);
		table4.addCell(cell8);
		document.add(table4);

		PdfPTable table5 = new PdfPTable(4);
		// 设置宽度
		table5.setWidthPercentage(100);
		// 设置间距
		table5.setSpacingBefore(0);

		PdfPCell cell9;
		cell9 = new PdfPCell(new Paragraph("付款总金额", PDFUtils.messFont));
		cell9.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell9.setMinimumHeight(20);// 设置单元格的高度
		cell9.setColspan(1);
		table5.addCell(cell9);

		PdfPCell cell10;
		cell10 = new PdfPCell(new Paragraph("50000", PDFUtils.messFont));
		cell10.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell10.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell10.setMinimumHeight(20);// 设置单元格的高度
		cell10.setColspan(3);
		table5.addCell(cell10);

		document.add(table5);

		PdfPTable table6 = new PdfPTable(4);
		// 设置宽度
		table6.setWidthPercentage(100);
		// 设置间距
		table6.setSpacingBefore(0);

		PdfPCell cell11;
		cell11 = new PdfPCell(new Paragraph("付款方式", PDFUtils.messFont));
		cell11.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell11.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell11.setMinimumHeight(20);// 设置单元格的高度
		cell11.setColspan(1);
		table6.addCell(cell11);

		PdfPCell cell12;
		cell12 = new PdfPCell(new Paragraph("电汇", PDFUtils.messFont));
		cell12.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell12.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell12.setMinimumHeight(20);// 设置单元格的高度
		cell12.setColspan(3);
		table6.addCell(cell12);
		document.add(table6);

		PdfPTable table7 = new PdfPTable(4);
		// 设置宽度
		table7.setWidthPercentage(100);
		// 设置间距
		table7.setSpacingBefore(0);

		PdfPCell cell13;
		cell13 = new PdfPCell(new Paragraph("支付日期:", PDFUtils.messFont));
		cell13.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell13.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell13.setMinimumHeight(20);// 设置单元格的高度
		cell13.setColspan(1);
		table7.addCell(cell13);

		PdfPCell cell14;
		cell14 = new PdfPCell(new Paragraph("50000", PDFUtils.messFont));
		cell14.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell14.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell14.setMinimumHeight(20);// 设置单元格的高度
		cell14.setColspan(3);
		table7.addCell(cell14);

		document.add(table7);

		PdfPTable table8 = new PdfPTable(4);
		// 设置宽度
		table8.setWidthPercentage(100);
		// 设置间距
		table8.setSpacingBefore(0);

		PdfPCell cell15;
		cell15 = new PdfPCell(new Paragraph("支付对象:", PDFUtils.messFont));
		cell15.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell15.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell15.setMinimumHeight(20);// 设置单元格的高度
		cell15.setColspan(1);
		table8.addCell(cell15);

		PdfPCell cell16;
		cell16 = new PdfPCell(new Paragraph("山东兰塘环保科技有限公司", PDFUtils.messFont));
		cell16.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell16.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell16.setMinimumHeight(20);// 设置单元格的高度
		cell16.setColspan(3);
		table8.addCell(cell16);

		document.add(table8);

		PdfPTable table9 = new PdfPTable(1);
		// 设置宽度
		table9.setWidthPercentage(100);
		// 设置间距
		table9.setSpacingBefore(0);

		PdfPCell cell17;
		cell17 = new PdfPCell(new Paragraph("审批流程", PDFUtils.messFont));
		cell17.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell17.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell17.setMinimumHeight(20);// 设置单元格的高度
		cell17.setColspan(1);
		table9.addCell(cell17);

		document.add(table9);

		for (int i = 0; i < 3; i++) {
			PdfPTable table10 = new PdfPTable(4);
			// 设置宽度
			table10.setWidthPercentage(100);
			// 设置间距
			table10.setSpacingBefore(0);

			PdfPCell createPdfPCell6 = pdfutils.createPdfPCell("审批人:", PDFUtils.messFont, 1);
			table10.addCell(createPdfPCell6);

			PdfPCell cell19;
			cell19 = new PdfPCell(new Paragraph("审批结论:", PDFUtils.messFont));
			cell19.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell19.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell19.setMinimumHeight(20);// 设置单元格的高度
			cell19.setColspan(1);
			table10.addCell(cell19);

			PdfPCell cell20;
			cell20 = new PdfPCell(new Paragraph("备注:", PDFUtils.messFont));
			cell20.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell20.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell20.setMinimumHeight(20);// 设置单元格的高度
			cell20.setColspan(1);
			table10.addCell(cell20);

			PdfPCell cell21;
			cell21 = new PdfPCell(new Paragraph("审批时间:" + dateFormat.format(new Date()), PDFUtils.messFont));
			cell21.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell21.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell21.setMinimumHeight(20);// 设置单元格的高度
			cell21.setColspan(1);
			table10.addCell(cell21);

			document.add(table10);
		}
		PdfPTable table11 = new PdfPTable(3);
		// 设置宽度
		table11.setWidthPercentage(100);
		// 设置间距
		table11.setSpacingBefore(0);

		PdfPCell cell22;
		cell22 = new PdfPCell(new Paragraph("打印时间:" + dateFormat.format(new Date()), PDFUtils.messFont));
		cell22.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell22.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell22.setMinimumHeight(20);// 设置单元格的高度
		cell22.setBorder(0);
		cell22.setColspan(1);
		table11.addCell(cell22);

		PdfPCell cell23;
		cell23 = new PdfPCell(new Paragraph("打印人:张三", PDFUtils.messFont));
		cell23.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell23.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell23.setMinimumHeight(20);// 设置单元格的高度
		cell23.setBorder(0);
		cell23.setColspan(2);
		table11.addCell(cell23);

		document.add(table11);

		document.close();
	}
}
