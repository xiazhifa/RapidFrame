package com.ysten.mylibrary.utils;

import java.text.DecimalFormat;

/**
 * 格式转换工具类
 */
public class FormatUtil {

	/**
	 * 格式转换
	 * @param size
	 * @return
	 */
	public static String byteToMB(long size) {
		return new DecimalFormat("0.##").format(size * 1.0 / (1024 * 1024));
	}

	/**
	 * 格式转换
	 * @param size
	 * @return
	 */
	public static String getMformB(long size) 
	{
		if (size > (1024 * 1024 * 1024)) 
		{
			return (size / 1024 / 1024 / 1024) + "GB";
		} 
		else if (size > (1024 * 1024)) 
		{
			return (size / 1024 / 1024) + "MB";
		} 
		else if (size > 1024) 
		{
			return (size / 1024) + "KB";
		} 
		else 
		{
			return size + "B";
		}
	}		
}
