package com.ysten.mylibrary.receiver.netchange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;

/**
 * 广播
 */
public class NetConnectChangReceiver extends BroadcastReceiver {

	private static final String TAG = "NetConnectChangReceiver";
	private NetStatusCallback mCallback;

	@Override
	public void onReceive(Context context, Intent intent) {

		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction()))
		{
			int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
			Log.e(TAG, "wifiState" + wifiState);
			switch (wifiState) {
				case WifiManager.WIFI_STATE_DISABLED://wifi不可用
					break;
				case WifiManager.WIFI_STATE_DISABLING://wifi不可用中
					break;
				case WifiManager.WIFI_STATE_ENABLING://wiif启用中
					break;
				case WifiManager.WIFI_STATE_ENABLED: //wifi已启用
					break;
				case WifiManager.WIFI_STATE_UNKNOWN: //wifi状态未知
					break;
				default:
					break;
			}
		}
		else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction()))
		{
			Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (null != parcelableExtra) {
				NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
				NetworkInfo.State state = networkInfo.getState();
				boolean isConnected = state == NetworkInfo.State.CONNECTED;
				Log.e(TAG, "isConnected" + isConnected);
				if (isConnected)
				{
					Log.e(TAG, "当前WiFi连接可用 ");
				}
				else
				{
					Log.e(TAG, "当前WiFi连接不可用 ");
				}
			}
		}
		else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
		{
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			Log.i(TAG, "CONNECTIVITY_ACTION");

			NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
			if (activeNetwork != null) {
				if (activeNetwork.isConnected()) {
					if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
						if(mCallback!=null)mCallback.netStatus(NetStatusCode.NetWifiConnect);
						WifiManager wifi_service = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
						WifiInfo wifiInfo = wifi_service.getConnectionInfo();
						if(mCallback!=null)mCallback.wifiLevle(wifiInfo.getRssi());
						Log.e(TAG, "当前WiFi连接可用 ");
					}
					else if (activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET) {
						// connected to the mobile provider's data plan
						if(mCallback!=null)mCallback.netStatus(NetStatusCode.NetEthConnect);
						Log.e(TAG, "当前有线网络连接可用 ");
					}
					else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
						// connected to the mobile provider's data plan
						if(mCallback!=null)mCallback.netStatus(NetStatusCode.NetMobileConnect);
						Log.e(TAG, "当前移动网络连接可用 ");
					}
				}
				else {
					if(mCallback!=null)mCallback.netStatus(NetStatusCode.NetNoConnect);
					Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
				}
				Log.e(TAG, "info.getTypeName()" + activeNetwork.getTypeName());
				Log.e(TAG, "getSubtypeName()" + activeNetwork.getSubtypeName());
				Log.e(TAG, "getState()" + activeNetwork.getState());
				Log.e(TAG, "getDetailedState()" + activeNetwork.getDetailedState().name());
				Log.e(TAG, "getDetailedState()" + activeNetwork.getExtraInfo());
				Log.e(TAG, "getType()" + activeNetwork.getType());
			}
			else {
				Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
				if(mCallback!=null)mCallback.netStatus(NetStatusCode.NetNoConnect);
			}
		}
	}

	public void setListener(NetStatusCallback callback)
	{
		this.mCallback = callback;
	}

}