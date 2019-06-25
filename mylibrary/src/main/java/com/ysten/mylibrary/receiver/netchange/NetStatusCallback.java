package com.ysten.mylibrary.receiver.netchange;

/**
 * 回调接口
 */
public interface NetStatusCallback {
  void netStatus(int status);
  void wifiLevle(int level);
}
