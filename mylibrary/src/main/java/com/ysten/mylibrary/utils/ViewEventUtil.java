package com.ysten.mylibrary.utils;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ViewEventUtil {

	//注册焦点和点击事件
	public static void setOnClickAndFocus(View view,Object interfaces)
	{
		view.setOnClickListener((OnClickListener) interfaces);
		view.setOnFocusChangeListener((OnFocusChangeListener) interfaces);
	}
	
	//注册ITEM点击和选择事件
	public static void setOnItemSelectedAndClick(AdapterView<Adapter> view,Object interfaces)
	{
		view.setOnItemClickListener((OnItemClickListener) interfaces);
		view.setOnItemSelectedListener((OnItemSelectedListener) interfaces);
	}		
}
