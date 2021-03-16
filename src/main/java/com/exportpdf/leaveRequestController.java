package com.exportpdf;

import java.text.SimpleDateFormat;
import java.util.Date;

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
 * @date 2020年12月14日 下午9:00:49
 * 
 */
@Controller
@RequestMapping("/pdf")
public class leaveRequestController {

	@Autowired
	private PDFUtils pdfUtils;

	@Autowired
	private HttpServletResponse response;

	@GetMapping(value = "/leaveRequest")
	public void leaveRequest() throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建标题
		PdfPTable createTitle = pdfUtils.createTitle("请假申请");
		document.add(createTitle);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("XXXXX有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		// 申请人
		PdfPTable createExpenseManagement = pdfUtils.createExpenseManagement("申请人:", "张三");
		document.add(createExpenseManagement);
		// 申请部门
		PdfPTable createExpenseManagement2 = pdfUtils.createExpenseManagement("请假类型:", "病假");
		document.add(createExpenseManagement2);
		// 暂支事由
		PdfPTable createExpenseManagement3 = pdfUtils.createExpenseManagement("开始日期:", "2020-12-11");
		document.add(createExpenseManagement3);
		// 暂支方式
		PdfPTable createExpenseManagement4 = pdfUtils.createExpenseManagement("结束日期:", "2020-12-12");
		document.add(createExpenseManagement4);
		// 暂支金额
		PdfPTable createExpenseManagement5 = pdfUtils.createExpenseManagement("请假销售:", "5");
		document.add(createExpenseManagement5);
		// 还款方式
		PdfPTable createExpenseManagement6 = pdfUtils.createExpenseManagement("请假事由:", "感冒了");
		document.add(createExpenseManagement6);
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
