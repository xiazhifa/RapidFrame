package com.ysten.mylibrary.receiver.diy;

import android.content.Context;
import android.content.Intent;

/**
 * 回调接口
 */
public interface DiyListener {
	void onReceive(Context context, Intent intent);
}
