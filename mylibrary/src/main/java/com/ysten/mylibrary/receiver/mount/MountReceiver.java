package com.ysten.mylibrary.receiver.mount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * U盘插拔状态变更的广播逻辑
 */

public class MountReceiver extends BroadcastReceiver 
{	
	private static final String TAG = "MountReceiver";
	private MountListener listener;


	/**
	 * 接收广播
	 * @param context
	 * @param intent
	 */
	@Override
	public void onReceive(Context context, Intent intent) 
	{
	    Log.d(TAG,"-----U盘-----intent.action=" + intent.getAction().toString());
		if(intent.getAction().equals(Intent.ACTION_MEDIA_MOUNTED))
		{
			listener.Mount(intent.getData().getPath());
		}
		else if(intent.getAction().equals(Intent.ACTION_MEDIA_EJECT))
		{
			listener.UnMount(intent.getData().getPath());
		}
		else
		{
			Log.d(TAG,"-----U盘-----无匹配");
		}
	}

	/**
	 * 设置回调接口
	 * @param listener
	 */
	public void setListener(MountListener listener)
	{
		this.listener = listener;
	}
	
}