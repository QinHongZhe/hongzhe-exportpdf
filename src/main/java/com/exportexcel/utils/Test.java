package com.exportexcel.utils;

import java.io.File;


public class Test {
	public static void main(String[] args) {
		extracted();
	}
	
	private static void extracted() {
		File file = new File("D:/Pictureserver/1.jpg");
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除成功");
			}
		}
	}
}
