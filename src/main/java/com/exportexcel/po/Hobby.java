package com.exportexcel.po;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author TANGSHUAI
 * @date 2020年11月25日 上午11:49:39
 * 
 */
public class Hobby implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4078668186442066291L;

	@Excel(name = "爱好id", width = 10)
	private Integer id;

	@Excel(name = "爱好名称", width = 20)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hobby [id=" + id + ", name=" + name + "]";
	}

	public Hobby(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Hobby() {
		super();
	}

}
