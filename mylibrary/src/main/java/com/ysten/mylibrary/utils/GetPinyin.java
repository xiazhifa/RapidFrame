package com.ysten.mylibrary.utils;

/**
 * 检测字符串是否是中文
 */
public class GetPinyin {

	public static boolean checkIsChines(String name) {
		// 转化为字符
		char[] nameChar = name.toCharArray();
		if (nameChar[0] > 128) {
			return true;
		} else {// 为英文字符
			return false;
		}
	}

}
