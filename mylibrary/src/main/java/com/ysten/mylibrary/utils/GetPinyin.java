package com.ysten.mylibrary.utils;

/**
 * 检测字符串是否是中文的工具类
 */
public class GetPinyin {

	/**
	*@description:检测字符串是否是中文
	*@author:Administrator
	*@date:2019/8/5/005 17:13
	*@param: 参数描述
	*@return  返回类型及作用
	*@throws:如果此方法抛异常，简单描述下
	*/
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
