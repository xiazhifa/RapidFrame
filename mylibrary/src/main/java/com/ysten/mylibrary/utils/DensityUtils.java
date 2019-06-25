package com.ysten.mylibrary.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * 获取屏幕尺寸和转换
 * Created by Administrator on 2019/6/5/005.
 */

public class DensityUtils {
    private DensityUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int qip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}

