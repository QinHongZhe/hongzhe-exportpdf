package com.exportpdf;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
 * @date 2020年11月24日 下午2:17:12
 * 
 */
@Controller
public class ExportPdfController2 {
	// 采购入库单打印
	@GetMapping(value = "/purchaseReceiptPDF")
	public void purchaseReceiptPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/pdf");
		// 下载文件的默认名称
		// response.setHeader("Content-Disposition", "attachment;filename=XXX.pdf");
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
		Font titleFont = new Font(bfChinese, 14, Font.BOLD);
		titleFont.setColor(BaseColor.BLACK);

		Document document = new Document();
		// 设置页面大小
		/*
		 * Rectangle pageSize = new Rectangle(PageSize.A4.getHeight(),
		 * PageSize.A4.getWidth()); pageSize.rotate(); document.setPageSize(pageSize);
		 */
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		// PdfWriter.getInstance(document, response.getOutputStream());
		// 打开文档
		document.open();

		// 创建表格
		PdfPTable table = new PdfPTable(1);
		// 设置表格宽度
		table.setWidthPercentage(50);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		PdfPCell cell;
		// 第一行
		cell = new PdfPCell(new Paragraph("上  海  汉  沃  实  业  有  限  公  司", smallThreeFont));
		// 设置文字可以居中
		cell.setUseAscender(true);
		// 设置水平居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		// 设置垂直居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthRight(0);
		table.addCell(cell);

		// 第二行
		cell = new PdfPCell(new Paragraph("电话:021－58978979/50801922/23     传真:021-58979038", fiveFont));
		cell.setMinimumHeight(20); // 设置单元格高度
		cell.setUseAscender(true); // 设置可以居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setBorder(0);
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("编号:12345", fiveFont));
		cell.setMinimumHeight(20); // 设置单元格高度
		cell.setUseAscender(true); // 设置可以居中
		cell.setHorizontalAlignment(Cell.ALIGN_RIGHT); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setBorder(0);
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("入 库 单", smallThreeGreenFont));
		cell.setMinimumHeight(20); // 设置单元格高度
		cell.setUseAscender(true); // 设置可以居中
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell.setBorder(0);
		table.addCell(cell);
		// 将表格保存到对象
		document.add(table);
		// 1=======================================================================
		// 创建入库明细，显示4列
		PdfPTable table2 = new PdfPTable(4);
		// 设置宽度
		table2.setWidthPercentage(100);
		// 设置行间距
		table2.setSpacingBefore(5);
		// 创建第一列
		PdfPCell cell2 = new PdfPCell(new Paragraph("仓库名称:测试", messFont));
		cell2.setBorder(0);
		cell2.setColspan(2);
		table2.addCell(cell2);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(new Date());
		// 创建第二列
		cell2 = new PdfPCell(new Paragraph("入   库   日   期 :" + format, messFont));
		cell2.setBorder(0);
		cell2.setColspan(2);
		table2.addCell(cell2);

		// 加入到对象
		document.add(table2);
		// 2========================================================================
		// 创建入库明细，显示6列
		PdfPTable table3 = new PdfPTable(4);
		// 设置宽度
		table3.setWidthPercentage(100);
		// 设置行间距
		table3.setSpacingBefore(5);
		// 创建第一列
		PdfPCell cell3 = new PdfPCell(new Paragraph("提  货  地 :", messFont));
		cell3.setBorder(0);
		cell3.setColspan(2);
		table3.addCell(cell3);

		// 创建第二列
		cell3 = new PdfPCell(new Paragraph("采购合同编号:CG123456", messFont));
		cell3.setBorder(0);
		cell3.setColspan(2);
		table3.addCell(cell3);

		document.add(table3);
		// 3=========================================================================
		// 创建入库明细，显示6列
		PdfPTable table4 = new PdfPTable(4);
		// 设置宽度
		table4.setWidthPercentage(100);
		// 设置行间距
		table4.setSpacingBefore(5);
		// 创建第一列
		PdfPCell cell4 = new PdfPCell(new Paragraph("物流公司:", messFont));
		cell4.setBorder(0);
		cell4.setColspan(2);
		table4.addCell(cell4);

		// 创建第二列
		cell4 = new PdfPCell(new Paragraph("供 应 商 名 称 :上海供应商", messFont));
		cell4.setBorder(0);
		cell4.setColspan(2);
		table4.addCell(cell4);

		document.add(table4);
		// 4.=======================================================================
		// 创建入库明细，显示6列
		PdfPTable table5 = new PdfPTable(4);
		// 设置宽度
		table5.setWidthPercentage(100);
		// 设置间距
		table5.setSpacingBefore(5);
		// 创建第一列
		PdfPCell cell5 = new PdfPCell(new Paragraph("运输车辆:", messFont));
		cell5.setBorder(0);
		cell5.setColspan(2);
		table5.addCell(cell5);

		// 创建第二列
		cell5 = new PdfPCell(new Paragraph("备注:", messFont));
		cell5.setBorder(0);
		cell5.setColspan(2);
		table5.addCell(cell5);

		document.add(table5);
		// 5.========================================================================
		// 创建入库明细，显示6列
		PdfPTable table6 = new PdfPTable(8);
		// 设置宽度
		table6.setWidthPercentage(100);
		// 设置间距
		table6.setSpacingBefore(10);
		// 创建第一列
		PdfPCell cell6 = new PdfPCell(new Paragraph("编号", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);
		table6.addCell(cell6);
		// 创建第二列
		cell6 = new PdfPCell(new Paragraph("产品名称", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(2);
		table6.addCell(cell6);
		// 创建第三列
		cell6 = new PdfPCell(new Paragraph("件数", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);
		table6.addCell(cell6);
		// 创建第四列
		cell6 = new PdfPCell(new Paragraph("包装", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);
		table6.addCell(cell6);

		cell6 = new PdfPCell(new Paragraph("数量(吨)", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);
		table6.addCell(cell6);

		cell6 = new PdfPCell(new Paragraph("采购单价(元)", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);
		table6.addCell(cell6);

		cell6 = new PdfPCell(new Paragraph("总金额(元)", messFont));
		cell6.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell6.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell6.setColspan(1);

		table6.addCell(cell6);
		document.add(table6);
		// 6.========================================================================
		// 查询入库明细
		// 件数合计
		Double totalPiece = 0.0;
		// 数量合计
		Double totalsum = 0.0;
		// 总金额合计
		Double sumMoney = 0.0;
		PdfPTable table7 = new PdfPTable(8);
		// 设置宽度
		table7.setWidthPercentage(100);
		// 设置间距
		table7.setSpacingBefore(0);
		// 创建第一列
		PdfPCell cell7 = new PdfPCell(new Paragraph("", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);
		table7.addCell(cell7);
		// 创建第二列
		cell7 = new PdfPCell(new Paragraph("1234", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(2);
		table7.addCell(cell7);
		// 创建第三列
		// dto.setPsid(purchaseReceiptDetail.get(i).get);
		cell7 = new PdfPCell(new Paragraph("", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);
		table7.addCell(cell7);

		// 创建第四列
		cell7 = new PdfPCell(new Paragraph("", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);
		table7.addCell(cell7);

		cell7 = new PdfPCell(new Paragraph("", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);
		table7.addCell(cell7);

		cell7 = new PdfPCell(new Paragraph("", messFont));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);
		table7.addCell(cell7);
		// 统计总金额

		cell7 = new PdfPCell(new Paragraph(""));
		cell7.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
		cell7.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
		cell7.setColspan(1);

		table7.addCell(cell7);
		document.add(table7);
	// System.out.println("totalPiece============="+totalPiece);
	// System.out.println("totalsum============="+totalsum);

	// 8.=====================================================================
	PdfPTable table71 = new PdfPTable(8);
	// 设置宽度
	table71.setWidthPercentage(100);
	// 设置间距
	table71.setSpacingBefore(0);
	// 创建第一列
	PdfPCell cell71 = new PdfPCell(new Paragraph("合计", messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(3);table71.addCell(cell71);
	// 创建第二列
	cell71=new PdfPCell(new Paragraph(""+totalPiece,messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(1);table71.addCell(cell71);
	// 创建第三列
	cell71=new PdfPCell(new Paragraph("",messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(1);table71.addCell(cell71);
	// 创建第四列
	cell71=new PdfPCell(new Paragraph(""+totalsum,messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(1);table71.addCell(cell71);

	cell71=new PdfPCell(new Paragraph("",messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(1);table71.addCell(cell71);

	cell71=new PdfPCell(new Paragraph(""+sumMoney,messFont));cell71.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell71.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell71.setColspan(1);table71.addCell(cell71);

	table71.addCell(cell71);document.add(table71);

	// 9.=======================================================================
	PdfPTable table8 = new PdfPTable(1);
	// 设置宽度
	table8.setWidthPercentage(100);
	// 设置间距
	table8.setSpacingBefore(10);
	// 创建第一列
	PdfPCell cell8 = new PdfPCell(new Paragraph("", messFont));cell8.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell8.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell8.setBorder(0);cell8.setColspan(1);table8.addCell(cell8);document.add(table8);

	// 10.=======================================================================
	PdfPTable table9 = new PdfPTable(4);
	// 设置宽度
	table9.setWidthPercentage(100);
	// 设置间距
	table9.setSpacingBefore(0);
	// 创建第一列
	PdfPCell cell9 = new PdfPCell(new Paragraph("制单人:" , messFont));cell9.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell9.setBorder(0);cell9.setColspan(1);table9.addCell(cell9);
	// 创建第二列
	cell9=new PdfPCell(new Paragraph("财务签字:",messFont));cell9.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell9.setBorder(0);cell9.setColspan(1);table9.addCell(cell9);

	cell9=new PdfPCell(new Paragraph("公司盖章:",messFont));cell9.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell9.setBorder(0);cell9.setColspan(1);table9.addCell(cell9);

	cell9=new PdfPCell(new Paragraph("仓库盖章:",messFont));cell9.setHorizontalAlignment(Cell.ALIGN_CENTER); // 设置水平居中
	cell9.setVerticalAlignment(Cell.ALIGN_MIDDLE); // 设置垂直居中
	cell9.setBorder(0);cell9.setColspan(1);table9.addCell(cell9);document.add(table9);

	document.close();

	writer.close();
	}

}
