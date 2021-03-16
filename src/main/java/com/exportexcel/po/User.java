package com.exportexcel.po;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**
 * @author TANGSHUAI
 * @date 2020年11月25日 上午10:33:10
 * 
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -1518670854283409543L;

	@Excel(name="用户名",width = 20)
	@NotNull(message = "用户名不能为空")
	private String name;
	
	@Excel(name = "年龄",width = 10)
	@NotNull(message="年龄不能为空")
	private Integer age;
	
	@ExcelCollection(name = "爱好信息")
	private List<Hobby> hobbies;
	
	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User(String name, Integer age, List<Hobby> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.hobbies = hobbies;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", hobbies=" + hobbies + "]";
	}
	
	
}
