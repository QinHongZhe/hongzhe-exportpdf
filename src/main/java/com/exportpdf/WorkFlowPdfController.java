package com.exportpdf;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exportexcel.utils.ConvertUpMoney;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;

/**
 * @author TANGSHUAI
 * @date 2020年11月27日 上午9:25:05
 * 
 */
@Controller
@RequestMapping("/work")
public class WorkFlowPdfController {

	@GetMapping(value = "/workFlow")
	public void purchaseReceiptPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		// 设置中文
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		// Font FontChinese = new Font(bfChinese, 15, Font.NORMAL);
		// 蓝色字体
		Font blueFont = new Font(bfChinese);
		blueFont.setColor(BaseColor.BLUE);
		blueFont.setSize(5);

		// 小三号
		Font smallThreeFont = new Font(bfChinese, 15);
		smallThreeFont.setColor(BaseColor.BLACK);
		// 小三号加粗
		Font smallThreeGreenFont = new Font(bfChinese, 15, Font.BOLD);
		smallThreeGreenFont.setColor(BaseColor.BLACK);
		// 五号
		Font fiveFont = new Font(bfChinese);
		fiveFont.setColor(BaseColor.BLACK);
		fiveFont.setSize(10.5f);

		// 五号
		Font smallFiveFont = new Font(bfChinese);
		smallFiveFont.setColor(BaseColor.BLACK);
		smallFiveFont.setSize(9);

		// 五号
		Font smallSixFont = new Font(bfChinese);
		smallSixFont.setColor(BaseColor.BLACK);
		smallSixFont.setSize(6.5f);

		// 小四号 加粗
		Font greenFont = new Font(bfChinese, 12, Font.BOLD);
		greenFont.setColor(BaseColor.BLACK);

		// 小四号
		Font messFont = new Font(bfChinese, 12);
		messFont.setColor(BaseColor.BLACK);

		// 标题加粗 四号
		Font titleFont = new Font(bfChinese, 20, Font.BOLD);
		titleFont.setColor(BaseColor.BLACK);

		Document document = new Document();
		PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();
		
		// 创建表格
		PdfPTable ta = new PdfPTable(1);
		// 设置表格宽度
		ta.setWidthPercentage(50);
		ta.setSpacingBefore(10f);
		ta.setSpacingAfter(10f);

		PdfPCell cellf;
		// 第一行
		cellf = new PdfPCell(new Paragraph("上 海 汉 沃 实 业 有 限 公 司", titleFont));
		// 设置文字可以居中
		cellf.setUseAscender(true);
		// 设置水平居中
		cellf.setHorizontalAlignment(Cell.ALIGN_CENTER);
		// 设置垂直居中
		cellf.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		cellf.setBorderWidthTop(0);
		cellf.setBorderWidthLeft(0);
		cellf.setBorderWidthRight(0);
		ta.addCell(cellf);

		// 第二行
		cellf = new PdfPCell(new Paragraph("电话:021－58978979/50801922/23     传真:021-58979038", fiveFont));
		cellf.setMinimumHeight(20); // 设置单元格高度
		cellf.setUseAscender(true); // 设置可以居中
		cellf.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cellf.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cellf.setBorder(0);
		ta.addCell(cellf);
		document.add(ta);
		
		PdfPTable table0 = new PdfPTable(1);
		// 设置表格宽度
		table0.setWidthPercentage(50);
		table0.setSpacingBefore(10f);
		table0.setSpacingAfter(10f);
		// 合同标题================================================
		PdfPCell cell0;
		// 第一行
		cell0 = new PdfPCell(new Paragraph("采购合同", smallThreeFont));
		// 设置文字可以居中
		cell0.setUseAscender(true);
		// 设置水平居中
		cell0.setHorizontalAlignment(Cell.ALIGN_CENTER);
		// 设置垂直居中
		cell0.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		cell0.setBorder(0);
		table0.addCell(cell0);
		document.add(table0);
		// 合同主体=================================================
		PdfPTable table = new PdfPTable(2);
		// 设置宽度
		table.setWidthPercentage(100);
		// 设置间距
		table.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell;
		cell = new PdfPCell(new Paragraph("单据类型：采购合同", messFont));
		cell.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setMinimumHeight(20);// 设置单元格的高度
		cell.setColspan(1);
		table.addCell(cell);
		// 创建第二列
		PdfPCell cell2;
		cell2 = new PdfPCell(new Paragraph("单据编号：CG000001", messFont));
		cell2.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置靠左
		cell2.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell2.setMinimumHeight(20);// 设置单元格的高度
		cell2.setColspan(1);
		table.addCell(cell2);
		document.add(table);

		PdfPTable table2 = new PdfPTable(2);
		// 设置宽度
		table2.setWidthPercentage(100);
		// 设置间距
		table2.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell5;
		cell5 = new PdfPCell(new Paragraph("供应商名称：测试供应商", messFont));
		cell5.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell5.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell5.setMinimumHeight(20);// 设置单元格的高度
		cell5.setColspan(1);
		table2.addCell(cell5);
		// 创建第二列
		PdfPCell cell6;
		cell6 = new PdfPCell(new Paragraph("采购日期：2020-12-01", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置靠左
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setMinimumHeight(20);// 设置单元格的高度
		cell6.setColspan(1);
		table2.addCell(cell6);
		document.add(table2);

		PdfPTable table3 = new PdfPTable(2);
		// 设置宽度
		table3.setWidthPercentage(100);
		// 设置间距
		table3.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell7;
		cell7 = new PdfPCell(new Paragraph("付款方式：现金", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setMinimumHeight(20);// 设置单元格的高度
		cell7.setColspan(1);
		table3.addCell(cell7);
		// 创建第二列
		PdfPCell cell8;
		cell8 = new PdfPCell(new Paragraph("合同订金金额：500", messFont));
		cell8.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置靠左
		cell8.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell8.setMinimumHeight(20);// 设置单元格的高度
		cell8.setColspan(1);
		table3.addCell(cell8);
		document.add(table3);

		PdfPTable table4 = new PdfPTable(2);
		// 设置宽度
		table4.setWidthPercentage(100);
		// 设置间距
		table4.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell9;
		cell9 = new PdfPCell(new Paragraph("备注：现金", messFont));
		cell9.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell9.setMinimumHeight(20);// 设置单元格的高度
		cell9.setColspan(1);
		table3.addCell(cell9);
		document.add(table4);

		// 合同明细=====================================================
		PdfPTable table5 = new PdfPTable(5);
		// 设置宽度
		table5.setWidthPercentage(100);
		// 设置间距
		table5.setSpacingBefore(0);

		// 创建第一列
		PdfPCell cell10;
		cell10 = new PdfPCell(new Paragraph("产品名称", messFont));
		cell10.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell10.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell10.setMinimumHeight(20);// 设置单元格的高度
		cell10.setColspan(1);
		table5.addCell(cell10);

		PdfPCell cell11;
		cell11 = new PdfPCell(new Paragraph("产品规格", messFont));
		cell11.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell11.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell11.setMinimumHeight(20);// 设置单元格的高度
		cell11.setColspan(1);
		table5.addCell(cell11);

		PdfPCell cell12;
		cell12 = new PdfPCell(new Paragraph("产品吨数", messFont));
		cell12.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell12.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell12.setMinimumHeight(20);// 设置单元格的高度
		cell12.setColspan(1);
		table5.addCell(cell12);

		PdfPCell cell13;
		cell13 = new PdfPCell(new Paragraph("单价", messFont));
		cell13.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell13.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell13.setMinimumHeight(20);// 设置单元格的高度
		cell13.setColspan(1);
		table5.addCell(cell13);

		PdfPCell cell14;
		cell14 = new PdfPCell(new Paragraph("产品吨数", messFont));
		cell14.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell14.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell14.setMinimumHeight(20);// 设置单元格的高度
		cell14.setColspan(1);
		table5.addCell(cell14);
		document.add(table5);

		for (int i = 0; i < 5; i++) {
			PdfPTable table6 = new PdfPTable(5);
			// 设置宽度
			table6.setWidthPercentage(100);
			// 设置间距
			table6.setSpacingBefore(0);
			PdfPCell cell15;
			cell15 = new PdfPCell(new Paragraph("名称" + i, messFont));
			cell15.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell15.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell15.setMinimumHeight(20);// 设置单元格的高度
			cell15.setColspan(1);
			table6.addCell(cell15);

			PdfPCell cell16;
			cell16 = new PdfPCell(new Paragraph("规格" + i, messFont));
			cell16.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell16.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell16.setMinimumHeight(20);// 设置单元格的高度
			cell16.setColspan(1);
			table6.addCell(cell16);

			PdfPCell cell17;
			cell17 = new PdfPCell(new Paragraph("吨数" + i, messFont));
			cell17.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell17.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell17.setMinimumHeight(20);// 设置单元格的高度
			cell17.setColspan(1);
			table6.addCell(cell17);

			PdfPCell cell18;
			cell18 = new PdfPCell(new Paragraph("单价" + i, messFont));
			cell18.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell18.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell18.setMinimumHeight(20);// 设置单元格的高度
			cell18.setColspan(1);
			table6.addCell(cell18);

			PdfPCell cell19;
			cell19 = new PdfPCell(new Paragraph("金额" + i, messFont));
			cell19.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell19.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell19.setMinimumHeight(20);// 设置单元格的高度
			cell19.setColspan(1);
			table6.addCell(cell19);
			document.add(table6);
		}

		PdfPTable table7 = new PdfPTable(2);
		// 设置宽度
		table7.setWidthPercentage(100);
		// 设置间距
		table7.setSpacingBefore(0);
		PdfPCell cell20;
		cell20 = new PdfPCell(new Paragraph("总金额（小写）:" + 5000, messFont));
		cell20.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell20.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell20.setMinimumHeight(20);// 设置单元格的高度
		cell20.setColspan(1);
		table7.addCell(cell20);

		PdfPCell cell21;
		cell21 = new PdfPCell(new Paragraph("总金额（大写）:" + ConvertUpMoney.toChinese("5000"), messFont));
		cell21.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell21.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell21.setMinimumHeight(20);// 设置单元格的高度
		cell21.setColspan(1);
		table7.addCell(cell21);
		document.add(table7);

		// 提交人=================================================
		PdfPTable table8 = new PdfPTable(4);
		// 设置宽度
		table8.setWidthPercentage(100);
		// 设置间距
		table8.setSpacingBefore(0);
		PdfPCell cell22;
		cell22 = new PdfPCell(new Paragraph("提交人:张三", messFont));
		cell22.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell22.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell22.setMinimumHeight(20);// 设置单元格的高度
		cell22.setColspan(1);
		table8.addCell(cell22);

		PdfPCell cell23;
		cell23 = new PdfPCell(new Paragraph("部门:采购部", messFont));
		cell23.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell23.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell23.setMinimumHeight(20);// 设置单元格的高度
		cell23.setColspan(1);
		table8.addCell(cell23);

		PdfPCell cell24;
		cell24 = new PdfPCell(new Paragraph("职务:采购经理", messFont));
		cell24.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell24.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell24.setMinimumHeight(20);// 设置单元格的高度
		cell24.setColspan(1);
		table8.addCell(cell24);

		PdfPCell cell25;
		cell25 = new PdfPCell(new Paragraph("提交时间:2020.11.27", messFont));
		cell25.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
		cell25.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell25.setMinimumHeight(20);// 设置单元格的高度
		cell25.setColspan(1);
		table8.addCell(cell25);
		document.add(table8);

		// 审批人
		for (int i = 0; i < 3; i++) {
			PdfPTable table9 = new PdfPTable(4);
			// 设置宽度
			table9.setWidthPercentage(100);
			// 设置间距
			table9.setSpacingBefore(0);
			PdfPCell cell26;
			cell26 = new PdfPCell(new Paragraph("审批人:张三", messFont));
			cell26.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell26.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell26.setMinimumHeight(20);// 设置单元格的高度
			cell26.setColspan(1);
			table9.addCell(cell26);

			PdfPCell cell27;
			cell27 = new PdfPCell(new Paragraph("审批结论:通过", messFont));
			cell27.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell27.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell27.setMinimumHeight(20);// 设置单元格的高度
			cell27.setColspan(1);
			table9.addCell(cell27);

			PdfPCell cell28;
			cell28 = new PdfPCell(new Paragraph("备注:审批通过，同意采购审批通过，同意采购审批通过，同意采购审批通过，同意采购", messFont));
			cell28.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell28.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell28.setMinimumHeight(20);// 设置单元格的高度
			cell28.setColspan(1);
			table9.addCell(cell28);

			PdfPCell cell29;
			cell29 = new PdfPCell(new Paragraph("审批时间:" + new Date(), messFont));
			cell29.setHorizontalAlignment(Cell.ALIGN_LEFT); // 设置水平居中
			cell29.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
			cell29.setMinimumHeight(20);// 设置单元格的高度
			cell29.setColspan(1);
			table9.addCell(cell29);
			document.add(table9);

		}

		document.close();
	}
}
