package com.ysten.mylibrary.receiver.mount;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 快速注册挂载设备状态变化广播
 */
public class MountChangeMode {
	private MountReceiver myReceive;
	
	//注册广播
	public void register(Context activity,MountListener listener)
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_REMOVED);
		filter.addAction(Intent.ACTION_MEDIA_EJECT);
		filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		filter.addDataScheme("file");
		myReceive = new MountReceiver();
		myReceive.setListener(listener);
		activity.registerReceiver(myReceive, filter);
				
	}
	
	//注销广播
	public void unRegister(Context activity)
	{
		if(myReceive!=null)
		{
			activity.unregisterReceiver(myReceive);
		}	
	}
}
