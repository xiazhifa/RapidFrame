package com.ysten.mylibrary.receiver.diy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * 广播
 */
public class DiyReceiver extends BroadcastReceiver
{	
	private static final String TAG = "DiyReceiver";
	private DiyListener listener;


	/**
	 * 接收广播
	 * @param context
	 * @param intent
	 */
	@Override
	public void onReceive(Context context, Intent intent) 
	{
	    listener.onReceive(context, intent);
	}

	/**
	 * 设置回调接口
	 * @param listener
	 */
	public void setListener(DiyListener listener)
	{
		this.listener = listener;
	}
	
}