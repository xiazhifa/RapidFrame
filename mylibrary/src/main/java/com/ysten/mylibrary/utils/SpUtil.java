package com.ysten.mylibrary.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Map;

public class SpUtil {
	private SpUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 保存文件名
	 */
	public static final String FILE_NAME = "config";


	/**
	 * 保存数据的方法，根据类型调用不同的保存方法
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD) 
	public static void put(Context context, String key, Object object) {

		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}
		editor.apply(); //commit同步的，apply异步的
	}


	/**
	 * 获取数据的方法，根据默认值得到数据的类型，然后调用对应方法获取值
	 */
	public static Object get(Context context, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);

		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}
		return null;
	}

	/**
	 * 移除某个key对应的值
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD) 
	public static void remove(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		editor.apply(); //commit同步的，apply异步的
	}

	/**
	 * 清除所有数据
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD) 
	public static void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.apply(); //commit同步的，apply异步的
	}

	/**
	 * 查询某个key是否已经存在
	 */
	public static boolean contains(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 */
	public static Map<String, ?> getAll(Context context) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
		return sp.getAll();
	}

}
