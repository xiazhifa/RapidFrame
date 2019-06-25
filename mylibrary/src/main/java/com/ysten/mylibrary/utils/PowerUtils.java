package com.ysten.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * 电源公里工具类
 * Created by Administrator on 2019/5/15/015.
 */

public class PowerUtils {
    /**
     * 获取当前电量
     * @param context
     * @return
     */
    public static int getPowerLevel(Context context)
    {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        return level;
    }


    /**
     * 当前是否在充电
     * @param context
     * @return
     */
    public static boolean IsCharging(Context context)
    {
    	  IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
          Intent batteryStatus = context.registerReceiver(null, ifilter);
    	  int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		  boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
          return isCharging;   
    }
}
