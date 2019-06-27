package com.ysten.mylibrary.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetUtil {

	/**
	 * IP地址格式化
	 * @param paramInt
	 * @return
	 */
	public static String intToIp(int paramInt) 
	{
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
    }

	/**
	 * 从完整的网址中取出ip：port部分
	 * @param url
	 * @return
	 */
	public static String FillURL(String url)
	{
		int index = url.indexOf("/");
		int start = index + 1;
		int end = url.indexOf("/", start + 1);
		return url.substring(start + 1, end);
	}
	

	/**
	 * 判断是否有外网连接
	 * @return
	 */
	public static final boolean ping() 
	{
		String result = null;
		try {
			String ip = "www.baidu.com";
			Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) 
			{
				stringBuffer.append(content);
			}
			Log.d("------ping-----","result content : " + stringBuffer.toString());
			// ping的状态
			int status = p.waitFor();
			if (status == 0) 
			{
				result = "success";
				return true;
			} 
			else 
			{
				result = "failed";
			}
		} 
		catch (IOException e) 
		{
			result = "IOException";
		} 
		catch (InterruptedException e) 
		{
			result = "InterruptedException";
		} 
		finally 
		{
			Log.d("----result---", "result = " + result);
		}
		return false;
	}

    /**
     * 判断是否是WiFi网络
     */
    public static boolean isWifiNet(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        return workinfo != null && workinfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

}
