package com.nieyue.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化类
 * @author yy
 *
 */
public class DateUtil {
	/**
	 * 获取当日开始时间
	 * @return
	 */
	 public static Date getStartTime(){  
		Date date =new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}  
	  
	/**
	 * 获取当日结束时间
	 * @return
	 */
	 public static Date getEndTime(){  
		Date date =new Date();
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	} 
	 /**
	  * 获取当前时间到当日结束时间差  
	  * 单位 ： 秒
	  * @return
	  */
	 public static long currentToEndTime(){
		 Date date=new Date();
		 long miao = (getEndTime().getTime()-date.getTime())/1000;
		 return miao;
	 } 
	/**
	 * 格式化时间yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime(){
	Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置显示格式
	String nowTime="";
	nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
	return nowTime;
	}
	/**
	 * 格式化时间yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentTimeDay(){
	Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
	String nowTime="";
	nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
	return nowTime;
	}
	/**
	 * date格式化时间 format
	 * @return
	 */
	public static String dateFormatSimpleDate(Date date,String format){
		DateFormat df = new SimpleDateFormat(format);//设置显示格式
		String nowTime="";
		nowTime= df.format(date);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	/**
	 * 格式化时间"yyyyMMddHHmmss
	 * @return
	 */
	public static String getOrdersTime(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	/**
	 * 格式化时间"yyyyMMdd
	 * @return
	 */
	public static String getImgDir(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyyMMdd");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    /** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }  
    /**
     * 获取精准时间格式存储数据库
     * @param args
     */
    public static Date getFormatCurrentTime(){  
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	 String time=getCurrentTime();

    	 Timestamp time1 = null;
		try {
			time1 = new Timestamp(sdf.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
    	return time1;  
    }  
    /**
     * 获取两日期之间的相隔天数
     * @param args
     * @throws ParseException 
     */
    public static Long getSeparatedTime() {  
    	String date01 = "2016-3-1 9:20:00";
    	String date02 = "2016-3-2 9:19:00";
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date d1 = null;
    	Date d2 = null;
		try {
			d1 = sdf.parse(date01);
			d2 = sdf.parse(date02);
			System.out.println(d1);
			System.out.println(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isSameDate(d1, d2)){//同一天
			return 0l;
		}else if(Math.abs((d2.getTime()-d1.getTime()))<=3600*24*1000){//差24小时以内算一天
			return 1l;
		}else{
			long daysBetween = (d2.getTime()-d1.getTime())/(3600*24*1000);//两日期之间相隔的天数 	
			return daysBetween;  
		}
    }  
    /**
     * 获取从起始日期开始几天后的日期
     * @param args
     * @throws ParseException 
     */
    public static Date getFirstToDay(Date firstDate,long coupleDay) {  
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date lastDate = new Date(coupleDay*3600*24*1000+firstDate.getTime());//两日期之间相隔的天数 
    	return lastDate;  
    }  
    /**
     * 获取从起始时间开始之后几分钟的时间
     * @param args
     * @throws ParseException 
     */
    public static Date getFirstToSecondsTime(Date firstDate,long coupleTime) {  
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date lastDate = new Date(coupleTime*60*1000+firstDate.getTime());//两日期之间相隔的天数 
    	return lastDate;  
    }  
    /**
     * 格式化时间从yyyy-MM-dd HH:mm:ss到Wed Mar 02 09:19:00 CST 2016
     * @param args
     * @throws ParseException 
     */
    public static Date parseDate(String date) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date da = sdf.parse(date);
    	return da;  
    }  
    /**
     * 是否同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                        .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    //  输出结果：  
    //  timeStamp=1417792627  
    //  date=2014-12-05 23:17:07  
    //  1417792627  
    //static HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    public static void main(String[] args) {  
    	System.out.println(getCurrentTime());
    	System.out.println(getOrdersTime());
    	String timeStamp = timeStamp();  
        System.out.println("timeStamp="+timeStamp);  
          
        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");  
        System.out.println("date="+date);  
          
        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");  
        System.out.println(timeStamp2); 
        System.out.println(getFormatCurrentTime().toLocaleString());
      //  HttpSession session = request.getSession();
       // session.setAttribute("nieyue", "dsfsdfsdf");
        //String nieyue = (String) session.getAttribute("nieyue");
       // System.out.println(session); 
        System.out.println(getSeparatedTime());
        System.out.println(getFirstToDay(new Date(), 1).toLocaleString());
        System.out.println(getFirstToSecondsTime(new Date(), 1).toLocaleString());
        System.out.println(getImgDir());
    }  
}
