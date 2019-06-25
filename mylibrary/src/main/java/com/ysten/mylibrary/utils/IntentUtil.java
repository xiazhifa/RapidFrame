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

	public static void startActivity(Context context,Class<? extends Activity> target){
		context.startActivity(new Intent().setClass(context, target));
	}

	public void startActivity(Context context,String packageName,String ClassName)
	{
		Intent mIntent = new Intent();
		mIntent.setComponent(new ComponentName(packageName, ClassName));
		try {
			context.startActivity(mIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startActivity(Context context,Class<? extends Activity> target,boolean isFinish){
		context.startActivity(new Intent().setClass(context, target));
        if(context instanceof Activity && isFinish)
        {
        	((Activity)context).finish();
        }
    }

	public static void startActivity(Context activity,Class<? extends Activity> target,int flag){
		activity.startActivity(new Intent().setClass(activity, target).addFlags(flag));
	}

	public static void startActivity(Context activity,Class<? extends Activity> target,int flag,String key,int value){
		activity.startActivity(new Intent().setClass(activity, target).addFlags(flag).putExtra(key, value));
	}

	public static void startActivity(Context activity,Class<? extends Activity> target,String key,String value){
		activity.startActivity(new Intent().setClass(activity, target).putExtra(key, value));
	}

	public static void startActivity(Context context,Class<? extends Activity> target,boolean isFinish,String key,int value){
		context.startActivity(new Intent().setClass(context, target).putExtra(key, value));
		if(context instanceof Activity && isFinish)
		{
			((Activity)context).finish();
		}
	}

	public static void startActivity(Context context,String action){
		context.startActivity(new Intent(action));
	}

	public static void startService(Context context,Class<? extends Service> target){
		context.startService(new Intent().setClass(context, target));
	}

	public static void sendBroadCast(Context context,String action,String key,String value){
		Intent intent = new Intent(action);
		intent.putExtra(key, value);
		context.sendBroadcast(intent);
	}
}
