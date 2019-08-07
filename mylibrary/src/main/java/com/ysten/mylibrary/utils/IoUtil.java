package com.ysten.mylibrary.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.R.integer;
import android.util.Log;

public class IoUtil {

	private static final String TAG = "IoUtil";

	/**
	*@description:向文件中写内容
	*@author:Administrator
	*@date:2019/8/7/007 15:54
	*@param: 参数描述
	*@return  返回类型及作用
	*@throws:如果此方法抛异常，简单描述下
	*/
	public static boolean writeStringToFile(String dir,String name, String value,boolean append) {
		return writeByteToFile(dir, name, value.getBytes(), append);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static boolean writeStringToFile(String path, String value,boolean append) {
		return writeByteToFile(path, value.getBytes(), append);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static boolean writeStringToFile(String dir,String name, String value) {
		return writeByteToFile(dir, name, value.getBytes(), false);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static boolean writeStringToFile(String path, String value) {
		return writeByteToFile(path, value.getBytes(), false);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	private static boolean writeByteToFile(String dir, String name, byte[] value, boolean append) {
		String path = dir+File.separator+name;
		return write(path, value, append);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	private static boolean writeByteToFile(String path, byte[] value, boolean append) {
		return write(path, value, append);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static boolean writeByteToFile(String dir,String name, byte[] value) {
		String path = dir+File.separator+name;
		return write(path, value, false);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static boolean writeByteToFile(String path, byte[] value) {
		return write(path, value, false);
	}

	/**
	 *@description:向文件中写内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	private static boolean write(String path,byte[] value,boolean append)
	{
		if(!path.contains("/"))
		{
			Log.d(TAG, "非法路径:"+path);
			return false;
		}
		int index =path.lastIndexOf("/");
		String filePath = path.substring(0,index);
		File file = new File(filePath);
		if(!file.exists())
		{
			file.mkdirs();
		}
		FileOutputStream wrt = null;
		try {
			wrt = new FileOutputStream(path,append);
			wrt.write(value);
			wrt.close();
			return true;
		} catch (Throwable t) {
			Log.e(TAG, "", t);
		}
		return false;
	}

	/**
	 *@description:从文件中读取内容
	 *@author:Administrator
	 *@date:2019/8/7/007 15:54
	 *@param: 参数描述
	 *@return  返回类型及作用
	 *@throws:如果此方法抛异常，简单描述下
	 */
	public static String readStringFromFile(String path) {
		BufferedReader reader = null;
		try {
			StringBuffer fileData = new StringBuffer(100);
			reader = new BufferedReader(new FileReader(path));
			char[] buf = new char[100];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();
			return fileData.toString();
		} catch (Throwable e) {
			Log.e(TAG, "Exception", e);
		} finally {
			try {
				assert reader != null;
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
}
