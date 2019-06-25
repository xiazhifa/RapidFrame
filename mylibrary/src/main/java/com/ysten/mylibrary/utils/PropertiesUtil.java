package com.ysten.mylibrary.utils;

/**
 * JAVA配置文件工具类
 * Created by Administrator on 2018/12/2/002.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import android.util.Log;

public class PropertiesUtil{
	
    //加载、读取Properties文件
    public static Properties loadProperties(String propertiesPath){
        Properties pps = new Properties();
        try {
            InputStream in = new FileInputStream(propertiesPath);
            pps.load(in);
            in.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }
    
    //向Properties文件中加入数据
    public static Properties addProperties(String propertiesPath,Properties pps,String key,String value){
        try {
            OutputStream fos = new FileOutputStream(propertiesPath);
            pps.setProperty(key, value);
            pps.store(fos, "Update '" + key + "' value");
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }
    
    //从Properties文件中根据key删除
    public static Properties removeProperties(String propertiesPath,Properties pps,String key){
        try {
            OutputStream oFile = new FileOutputStream(propertiesPath);
            pps.remove(key);
            pps.store(oFile, "Delete '" + key);
            oFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }

    //从Properties文件中根据key获取值value
    public static String getProperties(String propertiesPath,Properties pps,String key){
        try {
            OutputStream oFile = new FileOutputStream(propertiesPath);
            String value =pps.getProperty(key);
            pps.store(oFile, "Delete '" + key);
            oFile.close();
            return value;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Properties文件中根据key更新value
    public static Properties updatePropertiesByKey(String propertiesPath,Properties pps,String key,String value){
        try {
            OutputStream oFile = new FileOutputStream(propertiesPath);
            pps.setProperty(key, value);
            pps.store(oFile, "Update '" + key);
            oFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }
    
    //update Properties文件,以byte[]的形式，重新向properties中写入，特殊情况用
    public static Properties updateProperties(String propertiesPath,Properties pps,byte [] str){
        try {
            File file = new File(propertiesPath);
            FileOutputStream oFile = new FileOutputStream(file);
            oFile.write(str);
            oFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }
}

