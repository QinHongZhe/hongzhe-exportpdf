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
 * @date 2020年12月24日 下午2:03:46 代开证订单审批打印
 */
@Controller
@RequestMapping("/pdf")
public class CertifficateOrderPdfController {

	@Autowired
	private PDFUtils pdfUtils;

	@Autowired
	private HttpServletResponse response;

	@GetMapping("/certifficateOrderPdf")
	public @ResponseBody void certifficateOrderPdf() throws DocumentException, IOException {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建标题
		PdfPTable createTitle = pdfUtils.createTitle("代开证订单");
		document.add(createTitle);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("上海汉沃实业有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		// 订单编号
		PdfPTable createExpenseManagement = pdfUtils.createExpenseManagement("订单编号:", "GW");
		document.add(createExpenseManagement);
		// 订单日期
		PdfPTable createExpenseManagement2 = pdfUtils.createExpenseManagement("订单日期:", "2020-12-07");
		document.add(createExpenseManagement2);
		// 供应商名称
		PdfPTable createExpenseManagement3 = pdfUtils.createExpenseManagement("供应商名称:", "");
		document.add(createExpenseManagement3);
		// 开证公司名称
		PdfPTable createExpenseManagement4 = pdfUtils.createExpenseManagement("开证公司名称:", "");
		document.add(createExpenseManagement4);
		// 产品名称
		PdfPTable createExpenseManagement5 = pdfUtils.createExpenseManagement("产品名称:", "");
		document.add(createExpenseManagement5);
		// 包装规格
		PdfPTable createExpenseManagement6 = pdfUtils.createExpenseManagement("包装规格:", dateFormat.format(new Date()));
		document.add(createExpenseManagement6);
		// 数量(吨)
		PdfPTable createExpenseManagement7 = pdfUtils.createExpenseManagement("数量(吨):", "");
		document.add(createExpenseManagement7);
		// 单价(USD)
		PdfPTable createExpenseManagement8 = pdfUtils.createExpenseManagement("单价(USD):", "");
		document.add(createExpenseManagement8);
		// 总金额(USD)
		PdfPTable createExpenseManagement9 = pdfUtils.createExpenseManagement("总金额(USD):", "");
		document.add(createExpenseManagement9);
		// 目的港
		PdfPTable createExpenseManagement10 = pdfUtils.createExpenseManagement("目的港:", "");
		document.add(createExpenseManagement10);
		// 付款条件
		PdfPTable createExpenseManagement11 = pdfUtils.createExpenseManagement("付款条件:", "");
		document.add(createExpenseManagement11);
		// 备注
		PdfPTable createExpenseManagement12 = pdfUtils.createExpenseManagement("备注:", "");
		document.add(createExpenseManagement12);
		// 审批流程
		PdfPTable createTable3 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell4 = pdfUtils.createPdfPCell("审批流程", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell4);
		document.add(createTable3);
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
