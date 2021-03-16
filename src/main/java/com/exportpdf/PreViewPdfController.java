package com.exportpdf;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TANGSHUAI
 * @date 2020年12月29日 下午9:01:25
 * 文件预览
 */
@Controller
@RequestMapping("pdf")
public class PreViewPdfController {
	
	@RequestMapping(value = "filePreView")
	public void filePreView(HttpServletResponse response){
        File file = new File("D:\\PPT\\供应商合同.pdf");
        if (file.exists()){
            byte[] data = null;
            try {
                FileInputStream input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
                input.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            return;
        }

    }
}
