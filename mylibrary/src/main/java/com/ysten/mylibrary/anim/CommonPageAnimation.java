package com.ysten.mylibrary.anim;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ysten.mylibrary.R;


public class CommonPageAnimation {

	private static final String TAG = "CommonPageAnimation";
	private Context mContext;
	private ImageView mMoveView;
	private ViewGroup viewGroup;
	private ObjectAnimator bottomY;// 纵向移动动画
	private ObjectAnimator bottomX;// 横向移动动画
	private ObjectAnimator bottomYSet;
	private ObjectAnimator bottomXSet;
	private int MOVE_VIEW_ID = 9999;
	private UiHandle mUiHandle;
	private int oldMoveviewWidth;
	private int oldMoveviewHeight;
	private int duration = 200;
	private Object tag;
	private static final int MSG_START_ANIMATION = 0;
	private boolean isFirstStart = false;
	private AnimatorListener listener;


	/**
	 * 构造方法
	 * @param context
	 * @param viewGroup
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public CommonPageAnimation(Context context, RelativeLayout viewGroup) {
		mContext = context;
		if (viewGroup != null && viewGroup.findViewById(MOVE_VIEW_ID) == null) 
		{
			mUiHandle = new UiHandle();
			mMoveView = new ImageView(context);
			mMoveView.setBackgroundResource(R.drawable.file_divider_shape);
			mMoveView.setVisibility(View.INVISIBLE);
			mMoveView.setId(MOVE_VIEW_ID);
			this.viewGroup = viewGroup;
			viewGroup.addView(mMoveView);
			viewGroup.bringChildToFront(mMoveView);
		}
	}


	/**
	 * 构造方法，支持自定义焦点框
	 * @param context
	 * @param viewGroup
	 * @param drawable
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public CommonPageAnimation(Context context, RelativeLayout viewGroup, Drawable drawable) {
		mContext = context;
		if (viewGroup != null && viewGroup.findViewById(MOVE_VIEW_ID) == null) 
		{
			mUiHandle = new UiHandle();
			mMoveView = new ImageView(context);
			mMoveView.setBackgroundDrawable(drawable);
			mMoveView.setVisibility(View.INVISIBLE);
			mMoveView.setId(MOVE_VIEW_ID);
			this.viewGroup = viewGroup;
			viewGroup.addView(mMoveView);
			viewGroup.bringChildToFront(mMoveView);
		}
	}

	/**
	 * 构造方法，支持自定义ID
	 * @param context
	 * @param viewGroup
	 * @param id
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public CommonPageAnimation(Context context, RelativeLayout viewGroup, int id) {
		mContext = context;
		MOVE_VIEW_ID = id;
		if (viewGroup != null && viewGroup.findViewById(MOVE_VIEW_ID) == null) 
		{
			mUiHandle = new UiHandle();
			mMoveView = new ImageView(context);
			mMoveView.setBackgroundResource(R.drawable.file_divider_shape);
			mMoveView.setVisibility(View.INVISIBLE);
			MOVE_VIEW_ID = id;
			mMoveView.setId(MOVE_VIEW_ID);
			this.viewGroup = viewGroup;
			viewGroup.addView(mMoveView);
			viewGroup.bringChildToFront(mMoveView);
		}
	}

	/**
	 * 设置焦点框图片
	 * @param drawable
	 * @return
	 */
	public CommonPageAnimation setDrawable(Drawable drawable)
	{
		mMoveView.setBackgroundDrawable(drawable);
		return CommonPageAnimation.this;
	}

	/**
	 * 设置动画时长
	 * @param duration
	 * @return
	 */
	public CommonPageAnimation setAnimDuration(int duration)
	{
		this.duration = duration;
		return CommonPageAnimation.this;
	}

	/**
	 * 设置动画监听
	 * @param listener
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setAnimationListener(AnimatorListener listener) {
		Log.d(TAG, "test setAnimationListener");
		this.listener = listener;
	}

	/**
	 * 把View提到前台
	 */
	public void brintViewToFront()
	{
		viewGroup.bringChildToFront(mMoveView);
	}

	/**
	 * 设置标记
	 * @param tags
	 */
	public void setTag(Object tags)
	{
		tag = tags;
	}

	/**
	 * 获得标记
	 * @return
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 动画是否在运行
	 * @return
	 */
	public boolean isAnimationRunning() {
		if (bottomY != null) {
			return bottomY.isRunning();
		}
		return false;
	}

	/**
	 * 设置初次动画。只设置焦点View到目标View,不进行动画效果
	 * @param isFirst
	 */
	public void setFirst(boolean isFirst) {
		isFirstStart = isFirst;
	}

	/**
	 * 隐藏焦点View
	 */
	public void hideMoveView() {
		if (mMoveView != null) {
			mMoveView.setVisibility(View.INVISIBLE);
			if (mMoveView.getAlpha() >= 0)
				mMoveView.setAlpha(0.0f);
		}
	}

	/**
	 * 展示焦点View
	 */
	public void showMoveView() {
		if (mMoveView != null) {
			mMoveView.setVisibility(View.VISIBLE);
			if (mMoveView.getAlpha() == 0)
				mMoveView.setAlpha(1.0f);
		}
	}


	/**
	 * 动画集合封装方法
	 * @param newx
	 * @param newy
	 * @param neww
	 * @param newh
	 * @param isNeedAnimatorSet
	 */
	private void setNextWaypointView(float newx, float newy, int neww,
			int newh, boolean isNeedAnimatorSet) {

		if (mMoveView != null) {
			float oldx = Math.round(mMoveView.getX());
			float oldy = Math.round(mMoveView.getY());

            //如果宽高相同，且平行，那仅需一种纵向动画或横向动画
			if (!isNeedAnimatorSet) {

				if (bottomX == null) {
					bottomX = new ObjectAnimator();
					bottomX.setTarget(mMoveView);
					bottomX.setPropertyName("x");
				}
				if (bottomY == null) {
					bottomY = new ObjectAnimator();
					bottomY.setTarget(mMoveView);
					bottomY.setPropertyName("y");
				}
                //初次动画仅设位置，不设动画
				if (isFirstStart) {
					hideMoveView();
					mMoveView.setLayoutParams(new RelativeLayout.LayoutParams((int) neww, (int) newh));
					mMoveView.setY(newy);
					mMoveView.setX(newx);
					isFirstStart = false;
					showMoveView();
				} else {
					//纵向动画
					if (newy != oldy) {
						bottomY.setFloatValues(oldy, newy);
						bottomY.setDuration(duration);
						bottomY.start();
						if (true) Log.i(TAG, "bottomY starts");
						return;
					}
					//横向动画
					if (newx != oldx) {
						bottomX.setFloatValues(oldx, newx);
						bottomX.setDuration(duration);
						bottomX.start();
						if (true) Log.i(TAG, "bottomX starts");
					}
				}
			} 
			//如果宽不同或高不同或不平行，那需要横向纵向同时移动，且要改变动画位置
			else {
				if (bottomXSet == null) {
					bottomXSet = new ObjectAnimator();
					bottomXSet.setTarget(mMoveView);
					bottomXSet.setPropertyName("x");
				}
				if (bottomYSet == null) {
					bottomYSet = new ObjectAnimator();
					bottomYSet.setTarget(mMoveView);
					bottomYSet.setPropertyName("y");
				}
				bottomYSet.setFloatValues(oldy, newy);
				bottomXSet.setFloatValues(oldx, newx);
				//初次动画仅设位置，不设动画
				if (isFirstStart) {
					hideMoveView();
					mMoveView.setLayoutParams(new RelativeLayout.LayoutParams((int) neww, (int) newh));
					mMoveView.setY(newy);
					mMoveView.setX(newx);
					isFirstStart = false;
					showMoveView();
				} 
				else {
					AnimatorSet mBottomAnimatorSet = new AnimatorSet();
					mBottomAnimatorSet.playTogether(bottomXSet, bottomYSet);
					final int oldw = mMoveView.getWidth();
					final int deltaw = mMoveView.getWidth() - neww;
					final int oldh = mMoveView.getHeight();
					final int deltah = mMoveView.getHeight() - newh;
					//用估值器不断的改变View的宽高
					bottomXSet.addUpdateListener(new AnimatorUpdateListener() {
						
						@Override
						public void onAnimationUpdate(ValueAnimator valueAnimator) {
							// TODO Auto-generated method stub
							int width = (int) (oldw - deltaw* valueAnimator.getAnimatedFraction());
							int height = (int) (oldh - deltah* valueAnimator.getAnimatedFraction());
							mMoveView.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
						}
					});
					mBottomAnimatorSet.setDuration(duration);
					if(listener !=null)mBottomAnimatorSet.addListener(listener);
					mBottomAnimatorSet.start();
				}
			}
			oldMoveviewHeight = newh;
			oldMoveviewWidth = neww;
		}
	}

	/**
	 * 开启向指定View的动画，延时delay毫秒
	 * @param nextWaypointView
	 * @param delayTime
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void startAnimation(View nextWaypointView, int delayTime) {
		if (nextWaypointView == null) {
			return;
		}
		//获得目标View在屏幕中的xy位置
		int[] location = new int[2];
		nextWaypointView.getLocationOnScreen(location);
		float newx = location[0];
		float newy = location[1];
		if (newx == 0 && newy == 0) {
			return;
		}

		//检查可见区域
		Rect rect = new Rect();
		nextWaypointView.getGlobalVisibleRect(rect);
		if (rect != null && newy != rect.top)newy = rect.top;
				
        //获得目标View的宽高
		int neww = nextWaypointView.getWidth();
		int newh = nextWaypointView.getHeight();
		
		//补充大小，因为默认的背景图是有泛影的
		if (nextWaypointView.getTag() != null) {
			if (nextWaypointView.getTag() instanceof String) {
				Log.d(TAG, "test Stringnewx-->>" + newx + "newy" + newy
						+ "neww" + neww + "newh" + newh);

				String animView = (String) nextWaypointView.getTag();
				if (animView.equals("dialog_view")) {
					Log.d(TAG, "test dialog_view-->>" + newx + "newy" + newy
							+ "neww" + neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimension(
									R.dimen.anim_dialog_y);
					newx = newx
							- mContext.getResources().getDimension(
									R.dimen.anim_dialog_x);
					neww = neww
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_dialog_w);
					newh = newh
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_dialog_h);
				} else if (animView.equals("button_view")) {
					Log.d(TAG, "test button_viewnewx-->>" + newx + "newy"
							+ newy + "neww" + neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimension(
									R.dimen.anim_button_y);
					newx = newx
							- mContext.getResources().getDimension(
									R.dimen.anim_button_x);
					neww = neww
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_button_w);
					newh = newh
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_button_h);
				} else if (animView.equals("system_lock_view")) {
					Log.d(TAG, "test system_lock_viewnewx-->>" + newx + "newy"
							+ newy + "neww" + neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimension(
									R.dimen.anim_system_lock_y);
					newx = newx
							- mContext.getResources().getDimension(
									R.dimen.anim_system_lock_x);
					neww = neww
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_system_lock_w);
					newh = newh
							+ (int) mContext.getResources().getDimension(
									R.dimen.anim_system_lock_h);
				} else if (animView.equals("region_item")) {

					Log.d(TAG, "test city,  newx" + "newy" + newy + "neww"
							+ neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_region_y);
					newx = newx
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_region_x);
					neww = neww
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_region_w);
					newh = newh
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_region_h);
					Log.i(TAG, "4");
				} else if (animView.equals("display_item")) {

					Log.d(TAG, "test display,  newx" + "newy" + newy + "neww"
							+ neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_display_y);
					newx = newx
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_display_x);
					neww = neww
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_display_w);
					newh = newh
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_display_h);
					Log.i(TAG, "5");
				}
				else if (animView.equals("net_info_item")) {

					Log.d(TAG, "test net_info_item,  newx" + "newy" + newy + "neww"
							+ neww + "newh" + newh);
					newy = newy
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_net_info_y);
					newx = newx
							- mContext.getResources().getDimensionPixelSize(
									R.dimen.anim_net_info_x);
					neww = neww
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_net_info_w);
					newh = newh
							+ (int) mContext.getResources()
									.getDimensionPixelSize(
											R.dimen.anim_net_info_h);
					Log.i(TAG, "6");
				}
				else {
                    Log.d(TAG, "test 240");

                    Log.d(TAG, "test system_lock_view newx-->>" + newx + "newy" + newy
                            + "neww" + neww + "newh" + newh);
                    newy = newy
                            - mContext.getResources().getDimension(R.dimen.anim_net_y);
                    newx = newx
                            - mContext.getResources().getDimension(R.dimen.anim_net_x);
                    neww = neww
                            + (int) mContext.getResources().getDimension(
                            R.dimen.anim_net_w);
                    newh = newh
                            + (int) mContext.getResources().getDimension(
                            R.dimen.anim_net_h);
                }
			}
          }
		//是否需要进行动画集合
		boolean isNeedAnimatorSet = false;
		//当前View的xy坐标
		float oldx = Math.round(mMoveView.getX());
		float oldy = Math.round(mMoveView.getY());

		//检查是否需要动画集合
		if ((neww != oldMoveviewWidth) || (newh != oldMoveviewHeight) || (((int) newx != (int) oldx) && ((int) newy != (int) oldy))) {
			isNeedAnimatorSet = true;
		}
		//具体的动画调起逻辑在hanlder中执行
		if (mUiHandle.hasMessages(MSG_START_ANIMATION)) {
			mUiHandle.removeMessages(MSG_START_ANIMATION);
		}
		Message message = mUiHandle.obtainMessage(MSG_START_ANIMATION);
		Bundle bundle = new Bundle();
		Log.i(TAG, "newx = " + newx + " , newy = " + newy+ " , isNeedAnimatorSet = " + isNeedAnimatorSet);
		bundle.putFloat("newx", newx);
		bundle.putFloat("newy", newy);
		bundle.putInt("neww", neww);
		bundle.putInt("newh", newh);
		bundle.putBoolean("isNeedAnimatorSet", isNeedAnimatorSet);
		message.setData(bundle);
		mUiHandle.sendMessage(message);
	}


	/**
	 * 动画集合开始运行
	 */
	@SuppressLint("HandlerLeak")
	class UiHandle extends Handler {
		@Override
		public void handleMessage(Message msg) {
			int what = msg.what;
			Log.d(TAG, "...handleMessage..what=." + what);
			switch (what) {
			case MSG_START_ANIMATION:
				Bundle bundle = msg.getData();
				//如果动画已经在运行，结束动画
				if (isAnimationRunning()) {
					bottomY.end();
				}
                //动画集合mBottomAnimatorSet开始启动
				setNextWaypointView(
						bundle.getFloat("newx"),
						bundle.getFloat("newy"),
						bundle.getInt("neww"),
						bundle.getInt("newh"),
						bundle.getBoolean("isNeedAnimatorSet"));
				if (mMoveView != null) {
					mMoveView.setVisibility(View.VISIBLE);
				}
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	}
}
