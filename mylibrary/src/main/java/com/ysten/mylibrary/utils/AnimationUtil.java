package com.ysten.mylibrary.utils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


public class AnimationUtil {

	private static String TAG = "AnimationUtil";
	private static View previousView;
	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public static void startBigAnim(View view, boolean filter,float scale,int duration,boolean isFillAfter)
	{
		if (filter && previousView != null) 
		{
			previousView.clearAnimation();
		}
		previousView = view;
		Animation animation;
		animation = new ScaleAnimation(0.0f, scale, 0.0f, scale,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		animation.setDuration(duration);
		animation.setFillAfter(isFillAfter);
		view.clearAnimation();
		view.startAnimation(animation);
	}
	
}
