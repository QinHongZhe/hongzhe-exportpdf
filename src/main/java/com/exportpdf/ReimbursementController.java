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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author TANGSHUAI
 * @date 2020年12月13日 下午12:59:48 报销申请
 */
@Controller
@RequestMapping(value = "pdf")
public class ReimbursementController {

	@Autowired
	private PDFUtils pdfUtils;

	@GetMapping(value = "/reimbursement")
	public void reimbursement(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建标题
		PdfPTable createTitle = pdfUtils.createTitle("报销申请");
		document.add(createTitle);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("上海汉沃实业有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		// 申请人
		PdfPTable createExpenseManagement = pdfUtils.createExpenseManagement("申请人:", "张三");
		document.add(createExpenseManagement);
		// 报销事由
		PdfPTable createExpenseManagement2 = pdfUtils.createExpenseManagement("报销事由:", "京东");
		document.add(createExpenseManagement2);
		// 报销总额
		PdfPTable createExpenseManagement3 = pdfUtils.createExpenseManagement("报销总额:", "5000");
		document.add(createExpenseManagement3);
		// 付款总金额
		PdfPTable createExpenseManagement4 = pdfUtils.createExpenseManagement("报销日期:", dateFormat.format(new Date()));
		document.add(createExpenseManagement4);
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
