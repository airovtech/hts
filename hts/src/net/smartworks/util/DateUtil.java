/*	
 * file 		 : DateUtil.java
 * created by    : kmyu
 * creation-date : 2016. 11. 29.
 */

package net.smartworks.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil {

	
	public static Date convertLocaleDate(Date gmtDate) {
		
		if (gmtDate == null)
			return null;
		
		long time = gmtDate.getTime();
		
		//9시간을 더한다 
		time += 32400000;

		gmtDate.setTime(time);
		
		return gmtDate;
	}
	public static Date convertGMTDate(Date localeDate) {
		
		if (localeDate == null)
			return null;
		
		long time = localeDate.getTime();
		
		//9시간을 더한다 
		time -= 32400000;

		localeDate.setTime(time);
		
		return localeDate;
	}
	
	//시작일부터 종료일까지의 날짜를 요일과 함께 리스트로 리턴한다. 
	public static List<Map<String, String>> getDateStringListByFromTo(String pattern, Date fromDate, Date toDate) throws Exception {
		
		if (fromDate == null || toDate == null) {
			return null;
		}
		
		Calendar fromDateCal = Calendar.getInstance();
		fromDateCal.setTime(fromDate);

		Calendar toDateCal = Calendar.getInstance();
		toDateCal.setTime(toDate);
		
		List resultList = new ArrayList();
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		String targetDateString = toDateCal.get(Calendar.YEAR) + "-" + (toDateCal.get(Calendar.MONTH) + 1) + "-" + toDateCal.get(Calendar.DATE);
		while (true) {
			String dateString = fromDateCal.get(Calendar.YEAR) + "-" + (fromDateCal.get(Calendar.MONTH) + 1) + "-" + fromDateCal.get(Calendar.DATE);
			
		    int dayNum = fromDateCal.get(Calendar.DAY_OF_WEEK) ;
		    String day = null;
		    switch(dayNum){
		        case 1:
		            day = "일";
		            break ;
		        case 2:
		            day = "월";
		            break ;
		        case 3:
		            day = "화";
		            break ;
		        case 4:
		            day = "수";
		            break ;
		        case 5:
		            day = "목";
		            break ;
		        case 6:
		            day = "금";
		            break ;
		        case 7:
		            day = "토";
		            break ;
		    }
			
			//System.out.println(dateString +"("+day+")");
		    Map resultMap = new HashMap();
		    resultMap.put("date" , sdf.format(fromDateCal.getTime()));
		    resultMap.put("week", day);
		    resultList.add(resultMap);
			
			if (dateString.equals(targetDateString)) {
				break;
			} else {
				fromDateCal.add(Calendar.DATE, 1);
			}
		}
		return resultList;
	}
	
	
	// 파라미터로 넘어온 날짜가 속해있는 주의 월요일 날짜를 리턴한다. 
	public static Date getFirstDayOfWeek(Date date) throws Exception {
		
		if (date == null)
			return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		//System.out.println(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH) + 1) +"-"+ cal.get(Calendar.DATE));

		return cal.getTime();

	}
	// 파라미터로 넘어온 날짜가 속해있는 주의 월요일 날짜를 리턴한다. 
	public static String getFirstDayOfWeek(String pattern, String dateStr) throws Exception {
		
		if (pattern == null || dateStr == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		String resultDateStr = sdf.format(cal.getTime());
		
		//System.out.println(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH) + 1) +"-"+ cal.get(Calendar.DATE));

		return resultDateStr;

	}
	// 파라미터로 넘어온 날짜가 속해있는 주의 일요일 날짜를 리턴한다. 
	public static Date getLastDayOfWeek(Date date) throws Exception {
		
		if (date == null)
			return null;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		//System.out.println(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH) + 1) +"-"+ cal.get(Calendar.DATE));

		return cal.getTime();
		
	}
	// 파라미터로 넘어온 날짜가 속해있는 주의 일요일 날짜를 리턴한다. 
	public static String getLastDayOfWeek(String pattern, String dateStr) throws Exception {
		
		if (pattern == null || dateStr == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(dateStr);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		String resultDateStr = sdf.format(cal.getTime());
		
		//System.out.println(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH) + 1) +"-"+ cal.get(Calendar.DATE));

		return resultDateStr;
		
	}
}

