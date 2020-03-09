package com.ysten.mylibrary.receiver.mount;

/**
 * 回调接口
 */
public interface MountListener {
	void Mount(String path);
	void UnMount(String path);
}
