package com.ysten.mylibrary.receiver.diy;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;


/**
 *方便快速的自定义广播
 */
public class DiyBroadcastMode {
	private DiyReceiver myReceive;
	
	//注册广播
	public void register(Activity activity, String[] actions, DiyListener listener)
	{
		IntentFilter filter = new IntentFilter();
		for(String string:actions)
		{
			filter.addAction(string);
		}
		myReceive = new DiyReceiver();
		myReceive.setListener(listener);
		activity.registerReceiver(myReceive, filter);
				
	}
	
	//注销广播
	public void unRegister(Activity activity)
	{
		if(myReceive!=null)
		{
			activity.unregisterReceiver(myReceive);
		}	
	}
	
	

}
