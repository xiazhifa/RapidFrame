package com.ysten.mylibrary.utils;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JsonUtil {
	private static String TAG = "JsonUtil";

	/**
	 * 根据KEY从JSON字符串中取出String值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getValue(String json,String key)
	{	
		try 
		{
			JSONObject jsonObject = new JSONObject(json);
			return jsonObject.getString(key);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "JSONException:"+e.getMessage());
		}
		return null;
	}

	/**
	 * 根据KEY从JSON字符串中取出Int值
	 * @param json
	 * @param key
	 * @return
	 */
	public static int getIntValue(String json,String key)
	{
		try 
		{
			JSONObject jsonObject = new JSONObject(json);
			return jsonObject.getInt(key);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "JSONException:"+e.getMessage());
		}
		return -1;
	}

	/**
	 * 检查json是否合法
	 * @param json
	 * @return
	 */
	public static boolean checkJson(String json)
	{
		return json.startsWith("{");
	}
}
