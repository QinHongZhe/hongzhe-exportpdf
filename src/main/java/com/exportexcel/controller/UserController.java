package com.exportexcel.controller;

/**
 * @author TANGSHUAI
 * @date 2020年11月25日 上午10:55:02
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exportexcel.po.Hobby;
import com.exportexcel.po.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/userExcel")
	public @ResponseBody void userExcel(HttpServletResponse response) throws IOException{
//		Hobby hobby=new Hobby();
//		hobby.setId(1);
//		hobby.setName("打篮球");
//		
//		Hobby hobby2=new Hobby();
//		hobby2.setId(2);
//		hobby2.setName("跑步");
//		
//		List<Hobby> hobbies=new ArrayList<Hobby>();
//		hobbies.add(hobby);
//		hobbies.add(hobby2);
//		
//		
//		User user = new User();
//		user.setName("张三");
//		user.setAge(19);
//		user.setHobbies(hobbies);
//		
//		User user2 = new User();
//		user2.setName("李四");
//		user2.setAge(23);
//		user.setHobbies(hobbies);
//		
//		
//		List<User> list = new ArrayList<User>();
//		list.add(user);
//		list.add(user2);
//
//		 //通过工具类创建writer
//		ExcelWriter writer = ExcelUtil.getWriter(true);
//		 //合并单元格后的标题行，使用默认标题样式
//		System.out.println(list.size()+"=====================");
//		writer.merge(list.size()-1, "仓库报表");
//		 //一次性写出内容，使用默认样式，强制输出标题
//		writer.write(list, true);
//		//response为HttpServletResponse对象
//		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//		//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
//		String filename="仓库报表.xlsx";
//		response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode((String) filename, "UTF-8")); 
//		ServletOutputStream out=response.getOutputStream(); 
//		writer.flush(out, true);
//		 //关闭writer，释放内存
//		writer.close();
//		//此处记得关闭输出Servlet流
//		IoUtil.close(out);
		
	}

	@RequestMapping("/excel")
	public ModelAndView excel(HttpServletResponse response) throws IOException {

		List<Hobby> hobbies = new ArrayList<Hobby>();

		Hobby hobby = new Hobby();
		hobby.setId(1);
		hobby.setName("打篮球");
		hobbies.add(hobby);

		Hobby hobby2 = new Hobby();
		hobby2.setId(2);
		hobby2.setName("跑步");
		hobbies.add(hobby2);

		List<User> list = new ArrayList<User>();

		User user = new User();
		user.setName("张三");
		user.setAge(19);
		user.setHobbies(hobbies);
		list.add(user);

		User user2 = new User();
		user2.setName("李四");
		user2.setAge(23);
		user2.setHobbies(hobbies);
		list.add(user2);

		// 导出
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "检查日志汇总");
		// 注解对象Class
		mv.addObject(NormalExcelConstants.CLASS, User.class);
		// 自定义表格参数
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("检查日志汇总", "数量:  " + 20 + "    时间:  " + 12 + "   至   " + 10 + "  备注：0是通过，1是不通过  ", "检查日志"));
		// 导出数据列表
		mv.addObject(NormalExcelConstants.DATA_LIST, list);

		return mv;
	}
}
