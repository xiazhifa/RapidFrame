package com.ysten.mylibrary.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.R.bool;

public class ListUtil {

	/**
	 * 将List<String>转换成List<File>
	 * @param list
	 * @return
	 */
	public static List<File> ConvertTo(List<String> list)
	{
		List<File> filelist = new ArrayList<>();
		for(int i=0;i<list.size();i++)
		{
			File file =new File(list.get(i));
			filelist.add(file);
		}
		return filelist;
	}

	/**
	 * 查找指定项后的项
	 * @param file
	 * @param list
	 * @param canLoop
	 * @return
	 */
	public static File FindNextFile(File file,List<File> list,boolean canLoop)
	{
		int index = 0;
		for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getPath().equals(file.getPath()))
    		{
    			index = i;
    		}
    	}
		if(index +1 < list.size())
		{
			return list.get(index+1);
		}else {
			File file2 = canLoop?list.get(0):file;
			return file2;
		}
	}

	/**
	 * 查找指定项前的项
	 * @param file
	 * @param list
	 * @param canLoop
	 * @return
	 */
	public static File FindProFile(File file,List<File> list,boolean canLoop)
	{	
		int index = 0;
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).getPath().equals(file.getPath()))
    		{
    			index = i;
    		}
    	}
		if(index -1 > -1)
		{
			file = list.get(index-1);
		}else {
			file = canLoop?list.get(list.size()-1):file;
		}
		return file;  
	}
}
