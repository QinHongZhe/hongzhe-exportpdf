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
 * @date 2020年12月13日 上午10:43:17 其他付款申请
 */
@Controller
@RequestMapping(value = "pdf")
public class ElsePayMentPdfController {

	@Autowired
	private PDFUtils pdfUtils;

	@GetMapping(value = "/elsePayMent")
	public @ResponseBody void elsePayMent(HttpServletResponse response) throws DocumentException, IOException {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		// 格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 创建标题
		PdfPTable createTitle = pdfUtils.createTitle("其他付款申请");
		document.add(createTitle);
		// 公司名称
		PdfPTable createHead = pdfUtils.createHead("上海汉沃实业有限公司", "申请日期:" + dateFormat.format(new Date()));
		document.add(createHead);
		//申请人
		PdfPTable createExpenseManagement = pdfUtils.createExpenseManagement("申请人:", "张三");
		document.add(createExpenseManagement);
		//支付对象
		PdfPTable createExpenseManagement2 = pdfUtils.createExpenseManagement("支付对象:", "京东");
		document.add(createExpenseManagement2);
		// 付款事由
		PdfPTable createExpenseManagement3 = pdfUtils.createExpenseManagement("付款事由:", "京东购买商品");
		document.add(createExpenseManagement3);
		// 付款总金额
		PdfPTable createExpenseManagement4 = pdfUtils.createExpenseManagement("付款总金额:", "5000");
		document.add(createExpenseManagement4);
		// 付款方式
		PdfPTable createExpenseManagement5 = pdfUtils.createExpenseManagement("付款方式", "微信支付");
		document.add(createExpenseManagement5);
		// 支付日期
		PdfPTable createExpenseManagement6 = pdfUtils.createExpenseManagement("支付日期", dateFormat.format(new Date()));
		document.add(createExpenseManagement6);
		//审批流程
		PdfPTable createTable3 = pdfUtils.createTable(1, 100);
		PdfPCell createPdfPCell4 = pdfUtils.createPdfPCell("审批流程", PDFUtils.messFont, 1);
		createTable3.addCell(createPdfPCell4);
		document.add(createTable3);
		//打印审批人
		for (int i = 0; i < 4; i++) {
			PdfPTable createApprover = pdfUtils.createApprover("张三", "同意", "通过",dateFormat.format(new Date()));
			document.add(createApprover);
		}
		//打印时间
		PdfPTable createHead2 = pdfUtils.createHead("打印时间:"+dateFormat.format(new Date()), "打印人:张三");
		document.add(createHead2);
		document.close();
	}
}
