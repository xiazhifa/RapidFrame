package com.ysten.mylibrary.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

	private static final String TAG = "TimeUtil";

	/**
	 * 将"yyyy.MM.dd HH:mm:ss"格式的时间转换成时间毫秒
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Long timeStrToSecond(String time)
	{
	  try 
	  {
	      SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	      Long second = format.parse(time).getTime()+1*60*60*1000;
	      return second;
	  } 
	  catch (Exception e)
	  {
	      e.printStackTrace();
	      Log.e(TAG, "Exception:"+e.getMessage());
	  }
	      return -1l;     
	}

	/**
	 * 将"yyyyMMddHHmmss"格式的时间转换成时间毫秒
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Long timeStrToSecond2(String time)
	{
	  try
	  {
	      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	      Long second = format.parse(time).getTime()+1*60*60*1000;
	      return second;
	  } 
	  catch (Exception e)
	  {
	      e.printStackTrace();
	      Log.e(TAG, "Exception:"+e.getMessage());
	  }
          return -1l;
	}

	/**
	 * 将毫秒数转化为日期格式yyyy-MM-dd HH:mm:ss
	 * @param millis
	 * @return
	 */
	public static String getTime(long millis)
	{
		Date date = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}


	/**
	 * 获得日期格式为yyyy-MM-dd-HH-mm的当前时间
	 * @return
	 */
	public static String getTimeTomm()
	{	
		Calendar calendar = Calendar.getInstance();
		//年
		int year = calendar.get(Calendar.YEAR);
		//月
		int month = calendar.get(Calendar.MONTH)+1;
		//日
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		//小时
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		//分钟
		int minute = calendar.get(Calendar.MINUTE);
		return year+"-"+month+"-"+day+"-"+hour+"-"+minute;
	}

	/**
	 * 获得日期格式为yyyy-MM-dd-HH-mm的当前时间
	 * @return
	 */
	public static String getTimeTomm2()
	{	
		Calendar calendar = Calendar.getInstance();
		//年
		String year = calendar.get(Calendar.YEAR)+"";
		//月
		String month = calendar.get(Calendar.MONTH)+1+"";
		if(month.length()==1)month="0"+month;
		//日
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		if(day.length()==1)day="0"+day;
		//小时
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1)hour="0"+hour;
		//分钟
		String minute = calendar.get(Calendar.MINUTE)+"";
		if(minute.length()==1)minute="0"+minute;
		return year+"-"+month+"-"+day+"-"+hour+"-"+minute;
	}

	/**
	 * 获得日期格式为HH:mm的当前时间
	 * @return
	 */
	public static String getTimeTomm3()
	{
		Calendar calendar = Calendar.getInstance();
		//小时
		String hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		if(hour.length()==1)hour="0"+hour;
		//分钟
		String minute = calendar.get(Calendar.MINUTE)+"";
		if(minute.length()==1)minute="0"+minute;
		return hour+":"+minute;
	}
		
	
	/**
	 * 从时间(毫秒)中提取出时间(时:分) 时间格式: 时:分
	 * 
	 * @param millisecond
	 * @return
	 */
	public static String getTimeFromMillisecond(Long millisecond) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date(millisecond);
		String timeStr = simpleDateFormat.format(date);
		return timeStr;
	}
	
	/**
     * Android 音乐播放器应用里，读出的音乐时长为 long 类型以毫秒数为单位，例如：将 234736 转化为分钟和秒应为 03:55 （包含四舍五入）
     * @param duration 音乐时长
     * @return
     */
    public static String timeParse(int duration) {
        String time = "" ;
        int  hour   =  duration / 3600000;
        int minute =  duration % 3600000 / 60000;
        int seconds = duration % 3600000 % 60000 ;
        int second = Math.round((float)seconds/1000) ;
        if( hour < 10 ){
            time += "0" ;
        }
        time += hour+":" ;
        if( minute < 10 ){
            time += "0" ;
        }
        time += minute+":" ;
        if( second < 10 ){
            time += "0" ;
        }
        time += second ;
        Log.d(TAG, "timeparse"+time);
        return time ;
    }
        
	/**
	 * 获取时间间隔
	 * @param millisecond
	 * @return
	 */
	public static String getSpaceTime(Long millisecond) {
		Calendar calendar = Calendar.getInstance();
		Long currentMillisecond = calendar.getTimeInMillis();

		// 间隔秒
		Long spaceSecond = (currentMillisecond - millisecond) / 1000;

		// 一分钟之内
		if (spaceSecond >= 0 && spaceSecond < 60) {
			return "片刻之前";
		}
		// 一小时之内
		else if (spaceSecond / 60 > 0 && spaceSecond / 60 < 60) {
			return spaceSecond / 60 + "分钟之前";
		}
		// 一天之内
		else if (spaceSecond / (60 * 60) > 0 && spaceSecond / (60 * 60) < 24) {
			return spaceSecond / (60 * 60) + "小时之前";
		}
		// 3天之内
		else if (spaceSecond / (60 * 60 * 24) > 0 && spaceSecond / (60 * 60 * 24) < 3) {
			return spaceSecond / (60 * 60 * 24) + "天之前";
		} else {
			return getTime(millisecond);
		}
	}

	/**
	 * 获取时间描述
	 * @param millisecond
	 * @return
	 */
	public static String getDateFromMillisecond(Long millisecond) {

		Date date = null;
		try {
			date = new Date(millisecond);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar current = Calendar.getInstance();

		// //今天
		Calendar today = Calendar.getInstance();

		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));

		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		// 昨天
		Calendar yesterday = Calendar.getInstance();

		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH,
				current.get(Calendar.DAY_OF_MONTH) - 1);
		yesterday.set(Calendar.HOUR_OF_DAY, 0);
		yesterday.set(Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);

		// 今年
		Calendar thisYear = Calendar.getInstance();

		thisYear.set(Calendar.YEAR, current.get(Calendar.YEAR));
		thisYear.set(Calendar.MONTH, 0);
		thisYear.set(Calendar.DAY_OF_MONTH, 0);
		thisYear.set(Calendar.HOUR_OF_DAY, 0);
		thisYear.set(Calendar.MINUTE, 0);
		thisYear.set(Calendar.SECOND, 0);

		current.setTime(date);

		// 今年以前
		if (current.before(thisYear)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = format.format(date);
			return dateStr;
		} else if (current.after(today)) {
			return "今天";
		} else if (current.before(today) && current.after(yesterday)) {
			return "昨天";
		} else {
			SimpleDateFormat format = new SimpleDateFormat("MM-dd");
			String dateStr = format.format(date);
			return dateStr;
		}
	}

}
