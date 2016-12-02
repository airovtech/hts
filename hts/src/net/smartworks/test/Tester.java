/*	
 * file 		 : Tester.java
 * created by    : kmyu
 * creation-date : 2016. 11. 29.
 */

package net.smartworks.test;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.smartworks.util.DateUtil;

public class Tester {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		String dateStr = "2016-01-25";
		String pattern = "yyyy-MM-dd";
		
		Date toDay = new Date();
		
		List result = DateUtil.getDateStringListByFromTo("yyyy-MM-dd", DateUtil.getFirstDayOfWeek(toDay), DateUtil.getLastDayOfWeek(toDay));
		
		
		JSONArray jsonA = JSONArray.fromObject(result);
		
		System.out.println(jsonA);
		
		
				
				
				
		
		
	}

}

