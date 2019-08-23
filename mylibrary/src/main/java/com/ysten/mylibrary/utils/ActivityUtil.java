package com.ysten.mylibrary.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2019/8/1/001.
 * 关于Activity的一些封装方法
 */

public class ActivityUtil {

    /**
     * 获取当前栈顶Activity的名称
     * @return
     */
    public static String getTopApp(Context context)
    {
        String topActivity = "";
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> forGroundActivity = activityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo currentActivity;
        currentActivity = forGroundActivity.get(0);
        topActivity = currentActivity.topActivity.getPackageName();
        return topActivity;
    }

    /**
     * 判断某个activity是否在运行
     * @param mContext
     * @param activityClassName
     * @return
     */
    public static boolean isActivityRunning(Context mContext, String activityClassName)
    {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if(info != null && info.size() > 0)
        {
            ComponentName component = info.get(0).topActivity;
            if(component.getClassName().contains(activityClassName))
            {
                return true;
            }
        }
        return false;
    }

}
