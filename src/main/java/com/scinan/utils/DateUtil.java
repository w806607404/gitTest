package com.scinan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.scinan.bean.DateNormal;

public class DateUtil {
	public final static String DATE_PATTERN_ALL = "z(Z) yyyy-MM-dd HH:mm:ss";
	public final static String DATE_PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_PATTERN_DAY = "yyyy-MM-dd";
	public final static String DATE_PATTERN_MONTH_ = "yyyy-MM";
	public final static String DATE_PATTERN_MONTH = "yyyyMM";
	public final static String DATE_PATTERN_TOSTRING_SSS = "yyyyMMddHHmmssSSS";
	public final static String DATE_PATTERN_TOSTRING_S_SSS = "yyyyMMddHHmmssSSS";
	public final static String DATE_PATTERN_CHINESE_YMD = "yyyy年MM月dd日";


	private final static DateFormat DF_ALL = new SimpleDateFormat(DATE_PATTERN_ALL);
	private final static DateFormat DF_STANDARD = new SimpleDateFormat(DATE_PATTERN_STANDARD);
	private final static DateFormat DF_DAY = new SimpleDateFormat(DATE_PATTERN_DAY);
	private final static DateFormat DF_MONTH = new SimpleDateFormat(DATE_PATTERN_MONTH);
	private final static DateFormat DF_TOSTRING_SSS = new SimpleDateFormat(DATE_PATTERN_TOSTRING_SSS);
	private final static DateFormat DF_TOSTRING_S_SSS = new SimpleDateFormat(DATE_PATTERN_TOSTRING_S_SSS);
	private final static DateFormat DF_TOSTRING_CHINESE_YMD = new SimpleDateFormat(DATE_PATTERN_CHINESE_YMD);

	private static String getDate2String(Date date, DateFormat df) {
		if (date == null) {
			return StringUtil.EMPTY_STRING;
		}
		return df.format(date);
	}
	
	   private static Date getString2Date(String dateString, DateFormat df) {
	        if (dateString == null) {
	            return null;
	        }
	        try {
                return df.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
	        return null;
	    }
	
	public static Date getString2Date(String dateString, String datePattern){
	    if (DATE_PATTERN_ALL.equals(datePattern)) {
            return getString2Date(dateString, DF_ALL);
        } else if (DATE_PATTERN_STANDARD.equals(datePattern)) {
            return getString2Date(dateString, DF_STANDARD);
        } else if (DATE_PATTERN_DAY.equals(datePattern)) {
            return getString2Date(dateString, DF_DAY);
        } else if (DATE_PATTERN_TOSTRING_SSS.equals(datePattern)) {
            return getString2Date(dateString, DF_TOSTRING_SSS);
        } else if (DATE_PATTERN_CHINESE_YMD.equals(datePattern)) {
            return getString2Date(dateString, DF_TOSTRING_CHINESE_YMD);
        }
        return getString2Date(dateString, new SimpleDateFormat(datePattern));
	}

	public static String getDate2String(Date date, String datePattern) {
		if (DATE_PATTERN_ALL.equals(datePattern)) {
			return getDate2String(date, DF_ALL);
		} else if (DATE_PATTERN_STANDARD.equals(datePattern)) {
			return getDate2String(date, DF_STANDARD);
		} else if (DATE_PATTERN_DAY.equals(datePattern)) {
			return getDate2String(date, DF_DAY);
		} else if (DATE_PATTERN_TOSTRING_SSS.equals(datePattern)) {
			return getDate2String(date, DF_TOSTRING_SSS);
		} else if (DATE_PATTERN_CHINESE_YMD.equals(datePattern)) {
			return getDate2String(date, DF_TOSTRING_CHINESE_YMD);
		}
		return getDate2String(date, new SimpleDateFormat(datePattern));
	}

	public static String ObjectToString(Object obe, String datePattern) {
		SimpleDateFormat FORMAT = new SimpleDateFormat(datePattern);
		String str;
		try {
			str = FORMAT.format(obe);
		} catch (RuntimeException e) {
			str = "";
		}
		return str;
	}

	public static int compareTo(Date date1, Date date2) {
		// Workaround for bug in JDK 1.5.x. This bug is fixed in JDK 1.5.07. See
		// http://bugs.sun.com/bugdatabase/view_bug.do;:YfiG?bug_id=6207898 for
		// more information.
		if ((date1 != null) && (date2 == null)) {
			return -1;
		} else if ((date1 == null) && (date2 != null)) {
			return 1;
		} else if ((date1 == null) && (date2 == null)) {
			return 0;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		if (time1 == time2) {
			return 0;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * add at 2007-05-08
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return months between (beginDate,endDate) pattern: "yyyyMM"
	 * 
	 */
	public static List<String> betweenMonths(Calendar beginDate,
			Calendar endDate) {
		if (beginDate == null || endDate == null) {
			return null;
		}
		if (beginDate.after(endDate)) {
			return null;
		}
		String beginMonth = getDate2String(beginDate.getTime(), DF_MONTH);
		String endMonth = getDate2String(endDate.getTime(), DF_MONTH);
		if (beginMonth.equals(endMonth)) {
			return null;
		}
		Calendar compareDate = Calendar.getInstance();
		compareDate.setTime(beginDate.getTime());
		String compareMonth = "";
		List<String> list = new ArrayList<String>();
		while (compareDate.before(endDate)) {
			compareDate.add(Calendar.MONTH, 1);
			compareMonth = getDate2String(compareDate.getTime(), DF_MONTH);
			if (!compareMonth.equals(endMonth)
					&& (Integer.parseInt(compareMonth) < Integer
							.parseInt(endMonth))) {
				list.add(compareMonth);
			}
		}
		return list;
	}

	public static String string2YearMonthDay(String date) {
		Date dd = null;
		try {
			dd = DF_TOSTRING_S_SSS.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DF_TOSTRING_CHINESE_YMD.format(dd);
	}
	
	public static void main(String[] args) {
		{
//		String[] dates = getDates(1);
//		System.out.println(dates[0] + ", " + dates[1]);
//		}
//		{
//		String[] dates = getDates(2);
//		System.out.println(dates[0] + ", " + dates[1]);
//		}
//		{
//		String[] dates = getDates(3);
//		System.out.println(dates[0] + ", " + dates[1]);
//		}
//		{
//		String[] dates = getDates(4);
//		System.out.println(dates[0] + ", " + dates[1]);
			System.out.println();
		}
		
		//System.out.println("formatTime==="+formatTime(6574675678l));
		
		 Date nowTime = new Date(System.currentTimeMillis());
	     System.out.println(System.currentTimeMillis());
         SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
         String retStrFormatNowDate = sdFormatter.format(nowTime);
         System.out.println(getFewMinutesTime(5));
	}

	// 根据周期计算起始和结束日期
	public static String[] getDates(int period) {
		String[] dates = new String[2];
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		switch (period) {
		case 1:
			dates[0] = new SimpleDateFormat("yyyy-MM-dd").format(start.getTime());
			end.add(Calendar.DAY_OF_YEAR, 1);
			dates[1] = new SimpleDateFormat("yyyy-MM-dd").format(end.getTime());
			break;
		case 2:
			start.add(Calendar.WEEK_OF_YEAR, -1);
			start.add(Calendar.DAY_OF_YEAR, 1);
			dates[0] = new SimpleDateFormat("yyyy-MM-dd").format(start.getTime());
			end.add(Calendar.DAY_OF_YEAR, 1);
			dates[1] = new SimpleDateFormat("yyyy-MM-dd").format(end.getTime());
			break;
		case 3:
			start.add(Calendar.MONTH, -1);
			start.add(Calendar.DAY_OF_YEAR, 1);
			dates[0] = new SimpleDateFormat("yyyy-MM-dd").format(start.getTime());
			end.add(Calendar.DAY_OF_YEAR, 1);
			dates[1] = new SimpleDateFormat("yyyy-MM-dd").format(end.getTime());
			break;
		case 4:
			start.add(Calendar.YEAR, -1);
			start.add(Calendar.DAY_OF_YEAR, 1);
			dates[0] = new SimpleDateFormat("yyyy-MM-dd").format(start.getTime());
			end.add(Calendar.DAY_OF_YEAR, 1);
			dates[1] = new SimpleDateFormat("yyyy-MM-dd").format(end.getTime());
			break;
		default:
			dates = null;
			break;
		}
		return dates;
	}
	
	
    /* 
     * 毫秒转化时分秒毫秒 
     */  
    public static String formatTime(Long ms) {  
        Integer ss = 1000;  
        Integer mi = ss * 60;  
        Integer hh = mi * 60;  
        Integer dd = hh * 24;  
      
        Long day = ms / dd;  
        Long hour = (ms - day * dd) / hh;  
        Long minute = (ms - day * dd - hour * hh) / mi;  
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
          
        StringBuffer sb = new StringBuffer();  
        if(day > 0) {  
            sb.append(day+"天");  
        }  
        if(hour > 0) {  
            sb.append(hour+"小时");  
        }  
        if(minute > 0) {  
            sb.append(minute+"分");  
        }  
        if(second > 0) {  
            sb.append(second+"秒");  
        }  
        if(milliSecond > 0) {  
            sb.append(milliSecond+"毫秒");  
        }  
        return sb.toString();  
    }  
    
    
    /**
    *
    * @param min
    * @return day hour min
    */
   public static String minConvertDayHourMin(Double min){
    String  html="0分";
    if(min!=null){
     Double m=(Double) min;
     String format;
     Object[] array;
     Integer days =(int) (m/(60*24));
     Integer hours = (int) (m/(60)-days*24);
     Integer minutes = (int) (m-hours*60-days*24*60);
     if(days>0){
      format="%1$,d天%2$,d小时%3$,d分";
      array=new Object[]{days,hours,minutes};
     }else if(hours>0){
      format="%1$,d小时%2$,d分";
      array=new Object[]{hours,minutes};
     }else{
      format="%1$,d分";
      array=new Object[]{minutes};
     }
     html= String.format(format, array);
    }
    return html;
   }
   
    
    
    /**
     * <一句话功能简述>得到当前时间前一天日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dBeforDay(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return  sdf.format(dBefore);    //格式化前一天
    }
    
    
    /**
     * <一句话功能简述>得到当前时间前一周日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dBeforWeek(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return  sdf.format(dBefore);    //格式化前一周
    }
    
    
    
    /**
     * <一句话功能简述>得到当前时间前一月日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dBeforMonth(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -30);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return  sdf.format(dBefore);    //格式化前一月
    }
    
    
    /**
     * <一句话功能简述>得到当前时间前三月日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dBeforThreeMonth(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -90);  //设置为前三个月
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return  sdf.format(dBefore);    //格式化前一月
    }
    
    
    /**
     * <一句话功能简述>得到当前时间前六月日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String dBeforSixMonth(){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -180);  //设置为六个月
        dBefore = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return  sdf.format(dBefore);    //格式化前一月
    }
    
    
    
    /**
     * <一句话功能简述>得到当前年日期
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String nowOfYeah(){
    	
        Date yeah = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        yeah = calendar.getTime();   //得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); //设置时间格式
        return  sdf.format(yeah);    //格式化当前年
    }
    
    /**
     * <一句话功能简述>得到当前日期
     * <功能详细描述>
     * @return now time
     * @see [类、类#方法、类#成员]
     */
    public static String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    
    /**
     * <一句话功能简述>得到当前日期,精确到时分秒
     * <功能详细描述>
     * @return now time
     * @see [类、类#方法、类#成员]
     */
    public static String getTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
    
    /**
     * <一句话功能简述>将时间戳转换为时间
     * <功能详细描述>
     * @return now time
     * @see [类、类#方法、类#成员]
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    
    /**
     * <一句话功能简述>将时间戳转换为时间
     * <功能详细描述>
     * @return now time
     * @see [类、类#方法、类#成员]
     */
    public static String getCurrentTime(long currentTime){
    	 try{
	    	 Date nowTime = new Date(currentTime);
		     System.out.println(System.currentTimeMillis());
		     SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     return sdFormatter.format(nowTime);
    	 }catch(Exception ex){
    		 return "";
    	 }
    }
    
    
    /**
     * <一句话功能简述>获取数分钟后的时间
     * <功能详细描述>
     * @return now time
     * @see [类、类#方法、类#成员]
     */
    public static String getFewMinutesTime(int minutes){
    	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 try{
    		  Calendar nowTime = Calendar.getInstance();
    		  nowTime.add(Calendar.MINUTE, minutes);
		     return sdFormatter.format(nowTime.getTime());
    	 }catch(Exception ex){
    		 return "";
    	 }
    }
    
    
    
    /** 
     * 获取最近12个月，经常用于统计图表的X轴 
     */  
    public static List<DateNormal> getLast12Months(){  
        List<DateNormal> list = new ArrayList<DateNormal>();    
        String dateString;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        dateString = sdf.format(cal.getTime());
        for (int i = 0; i < 13; i++) {
        dateString = sdf.format(cal.getTime());
        DateNormal bean = new DateNormal();
        bean.setDate(dateString);
        System.out.println("dateString"+dateString);
        list.add(bean);  
        cal.add(Calendar.MONTH, -1);
        }
        
        
        
        
        return list;  
    }  
    
    public static String addZeroForNum(String str, int strLength) {    
        int strLen = str.length();    
        if (strLen < strLength) {    
            while (strLen < strLength) {    
                StringBuffer sb = new StringBuffer();    
                sb.append("0").append(str);// 左补0    
                // sb.append(str).append("0");//右补0    
                str = sb.toString();    
                strLen = str.length();    
            }    
        }    
        return str;    
    }  
    
    
    
}
