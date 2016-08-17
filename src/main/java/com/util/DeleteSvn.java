package com.util;

import java.io.File;

public class DeleteSvn {
	private final static String fileName = ".svn";

	public static void main(String[] args) {
		String path = "D:\\zwxxbs";
		File root = new File(path);
		searchFile(root);
	}

	/**
	 * 循环查询.svn目录，找到即删除
	 * 
	 * @方法名:searchFile
	 * @参数 @param file
	 * @返回类型 void
	 */
	public static void searchFile(File file) {
		
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				if (f.getName().equals(fileName)) {
					System.out.println(f.getAbsolutePath());
					deleteDirectory(f);
				} else {
					searchFile(f);
				}
			}
		}
	}

	/**
	 * 删除目录
	 * 
	 * @方法名:deleteDirectory
	 * @参数 @param file
	 * @返回类型 void
	 */
	public static void deleteDirectory(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					deleteDirectory(f);
				} else {
					f.delete();
				}
			}
		}
		file.delete();
	}
}
