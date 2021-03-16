package com.exportexcel.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exportexcel.po.User;

/**
 * @author TANGSHUAI
 * @date 2021年1月5日 下午3:17:49
 * 
 */
@Controller
public class User2Controller {
	
	@PostMapping("/createUser")
	public @ResponseBody String createUser(@RequestBody @Valid User user,BindingResult results) {
		if (results.hasErrors())
			return results.getFieldError().getDefaultMessage();
		return "success";
	}
}
