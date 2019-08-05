package com.ysten.mylibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast工具类
 */
public class ToastUtil {
    /**
    * @Fields 控制Toast是否能展示
    */
	public static boolean canToast = true;

	/**
	*@description:随时随地展示Toast
	*@author:Administrator
	*@date:2019/8/5/005 17:13
	*@param: 参数描述
	*@return  返回类型及作用
	*@throws:如果此方法抛异常，简单描述下
	*/
	public static void show(final Context context,final String str){
		if(!canToast)return;
		Handler handlerThree=new Handler(Looper.getMainLooper());
        handlerThree.post(new Runnable(){
            public void run(){
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
            }
        });
	}
}
