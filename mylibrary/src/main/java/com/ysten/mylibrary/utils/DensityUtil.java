package com.ysten.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.WindowManager;

/**
 * 获取屏幕尺寸和转换
 * Created by Administrator on 2019/6/5/005.
 */

public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        @SuppressLint("WrongConstant")
        WindowManager wm = (WindowManager)context.getSystemService("window");
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        @SuppressLint("WrongConstant")
        WindowManager wm = (WindowManager)context.getSystemService("window");
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}

