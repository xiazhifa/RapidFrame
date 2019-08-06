package com.ysten.mylibrary.utils;

import android.app.Activity;
import android.app.Notification.Action;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Intent工具类
 */
public class IntentUtil {

	/**
	 * 启动activity
	 * @param context
	 * @param target
	 */
	public static void startActivity(Context context,Class<? extends Activity> target){
		context.startActivity(new Intent().setClass(context, target));
	}

	/**
	 * 启动activity
	 * @param context
	 * @param packageName
	 * @param ClassName
	 */
	public static void startActivity(Context context,String packageName,String ClassName)
	{
		Intent mIntent = new Intent();
		mIntent.setComponent(new ComponentName(packageName, ClassName));
		try {
			context.startActivity(mIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动activity
	 * @param context
	 * @param packageName
	 * @param ClassName
	 */
	public static void startActivity(Context context,String packageName,String ClassName,int flag)
	{
		Intent mIntent = new Intent();
		mIntent.setComponent(new ComponentName(packageName, ClassName));
		mIntent.setFlags(flag);
		try {
			context.startActivity(mIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动activity
	 * @param context
	 * @param target
	 * @param isFinish
	 */
	public static void startActivity(Context context,Class<? extends Activity> target,boolean isFinish){
		context.startActivity(new Intent().setClass(context, target));
        if(context instanceof Activity && isFinish)
        {
        	((Activity)context).finish();
        }
    }

	/**
	 * 启动activity
	 * @param activity
	 * @param target
	 * @param flag
	 */
	public static void startActivity(Context activity,Class<? extends Activity> target,int flag){
		activity.startActivity(new Intent().setClass(activity, target).addFlags(flag));
	}

	/**
	 * 启动activity
	 * @param activity
	 * @param target
	 * @param flag
	 * @param key
	 * @param value
	 */
	public static void startActivity(Context activity,Class<? extends Activity> target,int flag,String key,int value){
		activity.startActivity(new Intent().setClass(activity, target).addFlags(flag).putExtra(key, value));
	}

	/**
	 * 启动activity
	 * @param activity
	 * @param target
	 * @param key
	 * @param value
	 */
	public static void startActivity(Context activity,Class<? extends Activity> target,String key,String value){
		activity.startActivity(new Intent().setClass(activity, target).putExtra(key, value));
	}

	/**
	 * 启动activity
	 * @param context
	 * @param target
	 * @param isFinish
	 * @param key
	 * @param value
	 */
	public static void startActivity(Context context,Class<? extends Activity> target,boolean isFinish,String key,int value){
		context.startActivity(new Intent().setClass(context, target).putExtra(key, value));
		if(context instanceof Activity && isFinish)
		{
			((Activity)context).finish();
		}
	}

	/**
	 * 启动activity
	 * @param context
	 * @param action
	 */
	public static void startActivity(Context context,String action){
		context.startActivity(new Intent(action));
	}

	/**
	 * 启动服务
	 * @param context
	 * @param target
	 */
	public static void startService(Context context,Class<? extends Service> target){
		context.startService(new Intent().setClass(context, target));
	}

	/**
	 * 发送广播
	 * @param context
	 * @param action
	 * @param key
	 * @param value
	 */
	public static void sendBroadCast(Context context,String action,String key,String value){
		Intent intent = new Intent(action);
		intent.putExtra(key, value);
		context.sendBroadcast(intent);
	}
}
