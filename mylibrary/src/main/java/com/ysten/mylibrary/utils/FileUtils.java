
package com.ysten.mylibrary.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * 文件相关操作
 */
public class FileUtils {
	public static String TAG = "FileUtils";

	/**
	 * 获取该路径下的已用百分比
	 * @param path
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	public static double getUseAmount(String path) 
	{
		File file = new File(path);
		if(!file.exists())
		{
			return 0;
		}
		long blocksize;
		long totalblock;
		long availbleblocks;

		StatFs stat = new StatFs(path);
		// 此处进行版本的判断因为在2.3版本中 getBlockSize()等方法还适用,之后的有些版本有了新的方法进行替代。
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) 
		{
			blocksize = stat.getBlockSizeLong();
			totalblock = stat.getBlockCountLong();
			availbleblocks = stat.getAvailableBlocksLong();
		} 
		else 
		{
			blocksize = stat.getBlockSize();
			totalblock = stat.getBlockCount();
			availbleblocks = stat.getAvailableBlocks();
		}
		long totalSize = blocksize * totalblock;
		long useSize = totalSize - availbleblocks * blocksize;
		double percent = (useSize * 1.0 / totalSize);
		return percent;
	}

	/**
	 * 获取该路径下的可用空间大小
	 * @param path
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	public static long getAvailbleSize(String path) 
	{
		File file = new File(path);
		if(!file.exists())
		{
			return 0;
		}
		long blocksize;
		long totalblock;
		long availbleblocks;

		StatFs stat = new StatFs(path);
		// 此处进行版本的判断因为在2.3版本中 getBlockSize()等方法还适用,之后的有些版本有了新的方法进行替代。
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) 
		{
			blocksize = stat.getBlockSizeLong();
			totalblock = stat.getBlockCountLong();
			availbleblocks = stat.getAvailableBlocksLong();
		} 
		else 
		{
			blocksize = stat.getBlockSize();
			totalblock = stat.getBlockCount();
			availbleblocks = stat.getAvailableBlocks();
		}
		long totalSize = blocksize * totalblock;
		long useSize = totalSize - availbleblocks * blocksize;
		return useSize;
	}

	/**
	 * 获取该路径下的总空间大小
	 * @param path
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	public static long getTotalSize(String path) 
	{
		File file = new File(path);
		if(!file.exists())
		{
			return 0;
		}
		long blocksize;
		long totalblock;

		StatFs stat = new StatFs(path);
		// 此处进行版本的判断因为在2.3版本中 getBlockSize()等方法还适用,之后的有些版本有了新的方法进行替代。
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) 
		{
			blocksize = stat.getBlockSizeLong();
			totalblock = stat.getBlockCountLong();
		} 
		else 
		{
			blocksize = stat.getBlockSize();
			totalblock = stat.getBlockCount();
		}
		long totalSize = blocksize * totalblock;
		return totalSize;
	}

	/**
	 * 复制文件
	 * @param filePath_old
	 * @param filePath_new
	 */
	public static void copyFile(final String filePath_old,final String filePath_new) 
	{
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		FileOutputStream fout = null;
		try 
		{
			fis = new FileInputStream(filePath_old);
			int size = fis.available();
			byte[] array = new byte[size];
			fis.read(array);
			fout = new FileOutputStream(filePath_new);
			fout.write(array);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			Log.d(TAG, "copy file error:" + e.getMessage());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log.d(TAG, "copy file error:" + e.getMessage());
		} 
		finally 
		{
			if (fis != null) 
			{
				try 
				{
					fis.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			if (fout != null) 
			{
				try 
				{
					fout.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

    /**
     * 复制文件夹及其中的文件
     *
     * @param oldPath String 原文件夹路径 如：data/user/0/com.test/files
     * @param newPath String 复制后的路径 如：data/user/0/com.test/cache
     * @return <code>true</code> if and only if the directory and files were copied;
     *         <code>false</code> otherwise
     */
    public static void copyFolder(final String oldPath, final String newPath) {
    	try {
            File newFile = new File(newPath);
            if(newFile.exists())
            {
            	deleteAll(newFile);
            }
            if (!newFile.mkdirs()) {
                Log.e("--Method--", "copyFolder: cannot create directory.");
            } 
            File oldFile = new File(oldPath);
            String[] files = oldFile.list();
            File temp;
            for (String file : files) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file);
                } else {
                    temp = new File(oldPath + File.separator + file);
                }
 
                if (temp.isDirectory()) {   //如果是子文件夹
                    copyFolder(oldPath + "/" + file, newPath + "/" + file);
                } else if (!temp.exists()) {
                    Log.e("--Method--", "copyFolder:  oldFile not exist.");
                } else if (!temp.isFile()) {
                    Log.e("--Method--", "copyFolder:  oldFile not file.");
                } else if (!temp.canRead()) {
                    Log.e("--Method--", "copyFolder:  oldFile cannot read.");
                } else {
                    FileInputStream fileInputStream = new FileInputStream(temp);
                    FileOutputStream fileOutputStream = new FileOutputStream(newPath + "/" + temp.getName());
                    byte[] buffer = new byte[1024];
                    int byteRead;
                    while ((byteRead = fileInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, byteRead);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            }
        } 
		catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * 删除目录或文件
	 * @param file
	 */
	public static void deleteAll(File file)
	{  
		 if(file.isFile() || file.list().length ==0)
		    {  
			   file.delete();            
		    }
		    else
		    {      
		          File[] files = file.listFiles();  
		          for (int i = 0; i < files.length; i++) 
		          {  
		                  deleteAll(files[i]);  
		                  files[i].delete();      
		          }   
		          if(file.exists())file.delete();
		    } 
   }

	/**
	 * 重命名文件
	 * @param path:原文件
	 * @param newname:新名称
	 * @param context
	 * @return
	 */
	public static String ReName(String path,String newname,Context context)
	{
        File file=new File(path);
        if(file.exists()) 
        {
        File newfile=new File(file.getParent()+File.separator+newname);//创建新名字的抽象文件
        if(file.renameTo(newfile)) 
          {
          	System.out.println("重命名成功");
            return file.getPath();
          }
        else 
          {
            System.out.println("重命名失败！新文件名已存在");
            return file.getPath();
          }
        }
        else 
        {
            System.out.println("重命名文件不存在！");
			return file.getPath();
        }
    }

	/**
	 * 获取文件夹或文件的大小,以字节为大小
	 * @param file
	 * @return
	 */
	public static double getDirSize(File file) {     
        if (file.exists()) {     
            if (file.isDirectory()) {     
                File[] children = file.listFiles();     
                double size = 0;     
                for (File f : children)     
                    size += getDirSize(f);     
                return size;     
            } else {
                double size = (double) file.length();        
                return size;     
            }     
        } else {     
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");     
            return 0.0;     
        }     
    }

	/**
	 * 创建目录
	 * 
	 * @param context
	 * @param dirName
	 *            文件夹名称
	 * @return
	 */
	public static File createFileDir(Context context, String dirName) {
		String filePath;
		String state = Environment.getExternalStorageState();
		// 如SD卡已存在，则存储；反之存在data目录下
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// SD卡路径
			filePath = Environment.getExternalStorageDirectory()
					+ File.separator + dirName;
		} else {
			filePath = context.getCacheDir().getPath() + File.separator
					+ dirName;
		}
		File destDir = new File(filePath);
		if (!destDir.exists()) {
			boolean isCreate = destDir.mkdirs();
			Log.i("FileUtils", filePath + " has created. " + isCreate);
		}
		return destDir;
	}

	/**
	 * 判断某目录下文件是否存在
	 * 
	 * @param dir
	 *            目录
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static boolean isFileExists(File dir, String fileName) {
		return new File(dir, fileName).exists();
	}

	/**
	 * 读取assets目录下的文本文件
	 */
	public static String readAssetsTxt(String filePath, Context context) {
		String result = "";
		try {
			InputStreamReader isr = new InputStreamReader(context
					.getResources().getAssets().open(filePath), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String lineTxt = null;
			while ((lineTxt = br.readLine()) != null) {
				result = result + lineTxt;
			}
			br.close();

		} catch (Exception e) {
			Log.d("xzf", "文件读取错误");
		}
		return result;
	}
}
