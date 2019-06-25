package com.ysten.mylibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast工具类
 */
public class ToastUtil {
	public static boolean canToast = true;

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
