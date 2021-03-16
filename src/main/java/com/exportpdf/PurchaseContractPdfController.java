package com.exportpdf;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author TANGSHUAI
 * @date 2020年12月24日 下午2:58:41 采购合同审批打印
 */
@Controller
@RequestMapping("pdf")
public class PurchaseContractPdfController {

	@Autowired
	private PDFUtils pdfUtils;

	@Autowired
	private HttpServletResponse response;

	@GetMapping("/purchaseContractPdf")
	public @ResponseBody void purchaseContractPdf() throws DocumentException, IOException {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();

		// 创建标题
		PdfPTable createTable = pdfUtils.createTable(1);
		PdfPCell createPdfPCell = pdfUtils.createPdfPCell("采购合同", PDFUtils.titleFont);
		createTable.addCell(createPdfPCell);
		document.add(createTable);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("上海汉沃实业有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		// 采购合同编号
		PdfPTable createTable2 = pdfUtils.createTable(3, 100);
		PdfPCell createPdfPCell2 = pdfUtils.createPdfPCell("合同编号:CGXXXXXX", PDFUtils.messFont, 1);
		createTable2.addCell(createPdfPCell2);
		// 采购日期
		PdfPCell createPdfPCell3 = pdfUtils.createPdfPCell("采购日期:CGXXXXXX", PDFUtils.messFont, 1);
		createTable2.addCell(createPdfPCell3);
		// 供应商名称
		PdfPCell createPdfPCell4 = pdfUtils.createPdfPCell("供应商名称:CGXXXXXX", PDFUtils.messFont, 1);
		createTable2.addCell(createPdfPCell4);
		document.add(createTable2);
		// 付款方式
		PdfPTable createTable3 = pdfUtils.createTable(3, 100);
		PdfPCell createPdfPCell5 = pdfUtils.createPdfPCell("付款方式:CGXXXXXX", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell5);
		// 合同订金金额
		PdfPCell createPdfPCell6 = pdfUtils.createPdfPCell("合同订金金额:", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell6);
		// 备注
		PdfPCell createPdfPCell7 = pdfUtils.createPdfPCell("备注:CGXXXXXX", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell7);
		document.add(createTable3);
		// 制单人
		PdfPTable createTable6 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell17 = pdfUtils.createPdfPCell("制单人:张三", PDFUtils.messFont, 1);
		createTable6.addCell(createPdfPCell17);
		document.add(createTable6);
		//
		PdfPTable createTable5 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell16 = pdfUtils.createPdfPCell("采购合同明细", PDFUtils.messFont, 1);
		createTable5.addCell(createPdfPCell16);
		document.add(createTable5);
		// 采购合同明细
		PdfPTable createTable4 = pdfUtils.createTable(16, 100);
		PdfPCell createPdfPCell8 = pdfUtils.createPdfPCell("序号", PDFUtils.messFont, 1);
		createTable4.addCell(createPdfPCell8);
		PdfPCell createPdfPCell9 = pdfUtils.createPdfPCell("产品名称", PDFUtils.messFont, 3);
		createTable4.addCell(createPdfPCell9);
		PdfPCell createPdfPCell10 = pdfUtils.createPdfPCell("产品编号", PDFUtils.messFont, 2);
		createTable4.addCell(createPdfPCell10);
		PdfPCell createPdfPCell11 = pdfUtils.createPdfPCell("包装规格", PDFUtils.messFont, 2);
		createTable4.addCell(createPdfPCell11);
		PdfPCell createPdfPCell12 = pdfUtils.createPdfPCell("单价(RMB)", PDFUtils.messFont, 2);
		createTable4.addCell(createPdfPCell12);
		PdfPCell createPdfPCell13 = pdfUtils.createPdfPCell("数量", PDFUtils.messFont, 2);
		createTable4.addCell(createPdfPCell13);
		PdfPCell createPdfPCell14 = pdfUtils.createPdfPCell("单位", PDFUtils.messFont, 1);
		createTable4.addCell(createPdfPCell14);
		PdfPCell createPdfPCell15 = pdfUtils.createPdfPCell("金额(RMB)", PDFUtils.messFont, 3);
		createTable4.addCell(createPdfPCell15);
		document.add(createTable4);
		for (int i = 0; i < 5; i++) {
			PdfPTable createTable_4 = pdfUtils.createTable(16, 100);
			PdfPCell createPdfPCell_8 = pdfUtils.createPdfPCell("" + (i + 1), PDFUtils.messFont, 1);
			createTable_4.addCell(createPdfPCell_8);
			PdfPCell createPdfPCell_9 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 3);
			createTable_4.addCell(createPdfPCell_9);
			PdfPCell createPdfPCell_10 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 2);
			createTable_4.addCell(createPdfPCell_10);
			PdfPCell createPdfPCell_11 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 2);
			createTable_4.addCell(createPdfPCell_11);
			PdfPCell createPdfPCell_12 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 2);
			createTable_4.addCell(createPdfPCell_12);
			PdfPCell createPdfPCell_13 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 2);
			createTable_4.addCell(createPdfPCell_13);
			PdfPCell createPdfPCell_14 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 1);
			createTable_4.addCell(createPdfPCell_14);
			PdfPCell createPdfPCell_15 = pdfUtils.createPdfPCell("", PDFUtils.messFont, 3);
			createTable_4.addCell(createPdfPCell_15);
			document.add(createTable_4);
		}
		PdfPTable createTable_5 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell_16 = pdfUtils.createPdfPCell("审批流程", PDFUtils.messFont, 1);
		createTable_5.addCell(createPdfPCell_16);
		document.add(createTable_5);
		// 审批人
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
