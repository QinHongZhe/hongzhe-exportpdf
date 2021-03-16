package com.exportpdf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TANGSHUAI
 * @date 2020年12月24日 下午3:35:36
 * 消耗品出库
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exportexcel.utils.PDFUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/pdf")
public class ConsumablesOutOfStoragePdfController {

	@Autowired
	private PDFUtils pdfUtils;

	@Autowired
	private HttpServletResponse response;

	@GetMapping("/consumablesOutOfStoragePdf")
	public @ResponseBody void consumablesOutOfStoragePdf() throws DocumentException, IOException {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建标题
		PdfPTable createTitle = pdfUtils.createTitle("消耗品出库");
		document.add(createTitle);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("上海汉沃实业有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		// 消耗品出库单编号
		PdfPTable createExpenseManagement = pdfUtils.createExpenseManagement("消耗品出库单编号:", "GW");
		document.add(createExpenseManagement);
		// 记账公司
		PdfPTable createExpenseManagement2 = pdfUtils.createExpenseManagement("记账公司:", "2020-12-07");
		document.add(createExpenseManagement2);
		// 出库日期
		PdfPTable createExpenseManagement3 = pdfUtils.createExpenseManagement("出库日期:", "");
		document.add(createExpenseManagement3);
		// 领取人
		PdfPTable createExpenseManagement4 = pdfUtils.createExpenseManagement("领取人:", "");
		document.add(createExpenseManagement4);
		// 申请人
		PdfPTable createExpenseManagement41 = pdfUtils.createExpenseManagement("申请人:", "");
		document.add(createExpenseManagement41);
		// 采购明细
		PdfPTable createTable3 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell7 = pdfUtils.createPdfPCell("采购明细", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell7);
		document.add(createTable3);
		// 采购单明细
		PdfPTable createTable = pdfUtils.createTable(9, 100);
		PdfPCell createPdfPCell = pdfUtils.createPdfPCell("序号", PDFUtils.messFont, 1);
		createTable.addCell(createPdfPCell);
		PdfPCell createPdfPCell2 = pdfUtils.createPdfPCell("产品名称", PDFUtils.messFont, 2);
		createTable.addCell(createPdfPCell2);
		PdfPCell createPdfPCell3 = pdfUtils.createPdfPCell("包装规格", PDFUtils.messFont, 2);
		createTable.addCell(createPdfPCell3);
		PdfPCell createPdfPCell4 = pdfUtils.createPdfPCell("单价(RMB)", PDFUtils.messFont, 1);
		createTable.addCell(createPdfPCell4);
		PdfPCell createPdfPCell5 = pdfUtils.createPdfPCell("数量", PDFUtils.messFont, 1);
		createTable.addCell(createPdfPCell5);
		PdfPCell createPdfPCell6 = pdfUtils.createPdfPCell("采购总金额(RMB)", PDFUtils.messFont, 2);
		createTable.addCell(createPdfPCell6);
		document.add(createTable);
		for (int i = 0; i < 3; i++) {
			PdfPTable createTable_1 = pdfUtils.createTable(9, 100);
			PdfPCell createPdfPCell_1 = pdfUtils.createPdfPCell("" + (i + 1), PDFUtils.messFont, 1);
			createTable_1.addCell(createPdfPCell_1);
			PdfPCell createPdfPCell_2 = pdfUtils.createPdfPCell("产品" + i, PDFUtils.messFont, 2);
			createTable_1.addCell(createPdfPCell_2);
			PdfPCell createPdfPCell_3 = pdfUtils.createPdfPCell("总金额", PDFUtils.messFont, 2);
			createTable_1.addCell(createPdfPCell_3);
			PdfPCell createPdfPCell_4 = pdfUtils.createPdfPCell("总金额", PDFUtils.messFont, 1);
			createTable_1.addCell(createPdfPCell_4);
			PdfPCell createPdfPCell_5 = pdfUtils.createPdfPCell("总金额", PDFUtils.messFont, 1);
			createTable_1.addCell(createPdfPCell_5);
			PdfPCell createPdfPCell_6 = pdfUtils.createPdfPCell("总金额", PDFUtils.messFont, 2);
			createTable_1.addCell(createPdfPCell_6);
			document.add(createTable_1);
		}

		// 审批流程
		PdfPTable createTable31 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell71 = pdfUtils.createPdfPCell("审批流程", PDFUtils.messFont, 1);
		createTable31.addCell(createPdfPCell71);
		document.add(createTable31);
		// 打印审批人
		for (int i = 0; i < 4; i++) {
			PdfPTable createApprover = pdfUtils.createApprover("张三", "同意", "通过", dateFormat.format(new Date()));
			document.add(createApprover);
		}
		// 打印时间
		PdfPTable createHead2 = pdfUtils.createHead("打印时间:" + dateFormat.format(new Date()), "打印人:张三");
		document.add(createHead2);
		document.close();

	}
}
