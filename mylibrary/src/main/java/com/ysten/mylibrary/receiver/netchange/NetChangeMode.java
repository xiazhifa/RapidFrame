package com.ysten.mylibrary.receiver.netchange;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;


public class NetChangeMode {
	private NetConnectChangReceiver myReceive;
	
	//注册广播
	public void register(Context context,NetStatusCallback callback)
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		myReceive = new NetConnectChangReceiver();
		myReceive.setListener(callback);
		context.registerReceiver(myReceive, filter);
	}
	
	//注销广播
	public void unRegister(Context context)
	{
		if(myReceive!=null)
		{
			context.unregisterReceiver(myReceive);
		}	
	}
}
