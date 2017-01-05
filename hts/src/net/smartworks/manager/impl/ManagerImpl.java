/*	
 * file 		 : ManagerImpl.java
 * created by    : kmyu
 * creation-date : 2016. 11. 15.
 */

package net.smartworks.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.smartworks.factory.DaoFactory;
import net.smartworks.manager.IManager;
import net.smartworks.model.HcTimeSheet;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.model.Project;
import net.smartworks.model.User;
import net.smartworks.model.UserCond;
import net.smartworks.util.DateUtil;
import net.smartworks.util.PropertiesUtil;

public class ManagerImpl implements IManager{

	@Override
	public List<User> getUsers(String userId, UserCond cond) throws Exception {
		List<User> users = DaoFactory.getInstance().getDao().getUsers(userId, cond);
		return users;
	}

	@Override
	public User getUser(String userId, String targetUserId) throws Exception {
		User user = DaoFactory.getInstance().getDao().getUser(userId, targetUserId);
		return user;
	}

	@Override
	public List<String> getAllDeptNameList(String userId) throws Exception {
		List<String> result = DaoFactory.getInstance().getDao().getAllDeptNameList(userId);
		return result;
	}

	@Override
	public List<String> getAllUserTypeList(String userId) throws Exception {
		List<String> result = DaoFactory.getInstance().getDao().getAllUserTypeList(userId);
		return result;
	}

	@Override
	public List<String> getAllProjectNameList(String userId) throws Exception {
		List<String> result = DaoFactory.getInstance().getDao().getAllProjectNameList(userId);
		return result;
	}
	
	public List<Project> getAllProjectList(String userId) throws Exception {
		List<Project> result = DaoFactory.getInstance().getDao().getAllProjectList(userId);
		return result;
	}
	public List<Project> getAllProjectListToSelect(String userId) throws Exception {
		List<Project> result = DaoFactory.getInstance().getDao().getAllProjectListToSelect(userId);
		return result;
	}
	
	@Override
	public Map getHcTimeSheetJsonDatasForJqgrid(String userId, HcTimeSheetCond cond) throws Exception {

		if (cond == null) {
			cond = new HcTimeSheetCond();
		}
		
		//st ,ot, input
		String gridType = cond.getGridType();
		
		String pattern = PropertiesUtil.getInstance().getDate_Pattern();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		//기본으로 조회하는 주 
		Date fromDate = cond.getWorkDateFrom();
		Date toDate = cond.getWorkDateTo();
		
		if (fromDate == null && toDate == null) {
			Date toDay = new Date();
			cond.setWorkDateFrom(DateUtil.getFirstDayOfWeek(toDay));	
			cond.setWorkDateTo(DateUtil.getLastDayOfWeek(toDay));	
		} else {
			
			if (toDate.getTime() < fromDate.getTime()) {
				return null;
			}
			
		}

		//검색 범위의 모든 날짜들을 조회 한다 
		//Time sheet 를 작성하지 않은날도 표시가 되어야 한다.
		List<Map<String, String>> dateList = DateUtil.getDateStringListByFromTo(pattern, cond.getWorkDateFrom(), cond.getWorkDateTo());
		
		
		//Time sheet
		List<HcTimeSheet> timeSheetList = DaoFactory.getInstance().getDao().getHcTimeSheet(userId, cond);
		
		
		
		
		
		List<Project> prjList = getAllProjectList(userId);
		
		
		
		
		
		
		
		//사용자별로 데이터를 취합하기 위한 맵
		Map<String, Map> userNameMap = new LinkedHashMap<String, Map>();
		
		//데이터의 빠른 조합을 위하여 모든 데이터는 맵에 저장한다. : 맵의 키로 바로 데이터에 엑세스 하기 위해 
		for (int i = 0; i < timeSheetList.size(); i++) {
			
			HcTimeSheet timeSheet = timeSheetList.get(i);
			String targetUserNo = timeSheet.getUserNo();
			
			//맵에서 사용자 사번으로 검색하여 데이터를 찾는다. 없으면 만든다.
			Map<String, Object> userMap = (Map<String, Object>)userNameMap.get(targetUserNo);
			if (userMap == null) {
				userMap = new HashMap();
				
				userMap.put("name", timeSheet.getName());
				userMap.put("engName", timeSheet.getEngName());
				userMap.put("userNo", timeSheet.getUserNo());
				userMap.put("type", timeSheet.getType());
				userMap.put("position", timeSheet.getPosition());
				userMap.put("dept", timeSheet.getDept());
				
				userNameMap.put(targetUserNo, userMap);
			}				
			
			//맵에서 프로젝트별 날짜 데이터를 찾기 위해 맵을 호출한다. 없으면 만든다.
			Map projectDataMap = (Map)userMap.get("projectDatas");
			if (projectDataMap == null) {
				projectDataMap = new HashMap();
				userMap.put("projectDatas", projectDataMap);
			}

			String timeSheetProjectCode = timeSheet.getPrjCode();
			String timeSheetWorkDateString = sdf.format(timeSheet.getWorkDate());
			String timeSheetStTime = timeSheet.getSt();
			String timeSheetOtTime = timeSheet.getOt();
			
			Map timesMap = (Map)projectDataMap.get(timeSheetProjectCode);
			
			if (timesMap == null) {
				
				timesMap = new LinkedHashMap();
				
				//프로젝트가 처음 생성되는 순간, 검색범위 날짜 맵을 만들어서 초기화 시켜준다.
				for (int j = 0; j < dateList.size(); j++) {
					Map dateListMap = dateList.get(j);
					String dateString = (String)dateListMap.get("date");
					String weekString = (String)dateListMap.get("week");
					
					Map timeMap = new HashMap();
					timeMap.put("date", dateString);
					timeMap.put("week", weekString);
					timeMap.put("st", 0);
					timeMap.put("ot", 0);
					
					timesMap.put(dateString, timeMap);
				}
				
				projectDataMap.put(timeSheet.getPrjCode(), timesMap);
			}
			
			//초기화된 맵에서 Time sheet 의 날짜에 해당하는 맵을 찾아 실제 데이터를 삽입힌다. 
			Map timeMap = (Map)timesMap.get(timeSheetWorkDateString);
			if (timeSheetStTime != null) {
				timeMap.put("st", timeSheetStTime);
			}
			if (timeSheetOtTime != null) {
				timeMap.put("ot", timeSheetOtTime);
			}
		}
		
		if (userNameMap.keySet() == null) {
			return null;
		}
		
		//make object for json 만들어진 맵 데이터를 이용하여 json 오브젝트를 생성한다. 
		//맵을 먼저만드는 이유는 time sheet 가 건건이 조회(database쿼리) 되면서
		//데이터(리턴값)를 만들어 나가야 하므로 리스트를 돌려 해당 데이터에 접근하는 것 보다 맵 키를 이용하여 바로접근하는 것이 성능상 좋을 것이라 판단함 
		//맵으로 데이터셋을 완성한 후 해당 데이터셋을 이용하여 json 오브젝트를 생성 리턴한다. 
		
		List<Map> resultDataList = new ArrayList(userNameMap.values());
		
		for (int i = 0; i < resultDataList.size(); i++) {
			
			Map userMap = (Map)resultDataList.get(i);
			
			Map projectDataMap = (Map)userMap.get("projectDatas");
			
			if (projectDataMap != null) {
				
				Iterator itr = projectDataMap.keySet().iterator();
				
				List projectDataList = new ArrayList();
				while (itr.hasNext()) {
					String projectCode = (String)itr.next();

					Map dateDataMap = (Map)projectDataMap.get(projectCode);
					
					List dateDataList = new ArrayList(dateDataMap.values());
					
					Map newDataMap = new LinkedHashMap();
					newDataMap.put("projectCode", projectCode);
					newDataMap.put("sum", "empty");
					newDataMap.put("dateDataList", dateDataList);
					
					projectDataList.add(newDataMap);
				}
				
				userMap.put("projectDataList", projectDataList);
				userMap.remove("projectDatas");
			}
		}
		
		List resultDataForJqgridList = new ArrayList();
		
		
		float totalSum = 0;
		Map<String, Float> summaryMap = new HashMap<String, Float>();
		
		for (int i = 0; i < resultDataList.size(); i++) {
			
			Map userMap = resultDataList.get(i);
			
			String userNo = (String)userMap.get("userNo");
			String name = (String)userMap.get("name");
			String engName = (String)userMap.get("engName");
			String position = (String)userMap.get("position");
			String dept = (String)userMap.get("dept");
			String type = (String)userMap.get("type");

			
			List projectList = (List)userMap.get("projectDataList");
			
			if (projectList == null) {
				continue;
			}
			
			for (int j = 0; j < projectList.size(); j++) {
				
				Map projectMap = (Map)projectList.get(j);

				String projectCode = (String)projectMap.get("projectCode");
				//String sum = (String)projectMap.get("sum");
				List prjDateList = (List)projectMap.get("dateDataList");
				
				
				Map resultDataForJqgridMap = new LinkedHashMap();

				resultDataForJqgridMap.put("userNo", userNo);
				resultDataForJqgridMap.put("name", name);
				resultDataForJqgridMap.put("engName", engName);
				resultDataForJqgridMap.put("dept", dept);
				resultDataForJqgridMap.put("position", position);
				resultDataForJqgridMap.put("type", type);
				
				resultDataForJqgridMap.put("projectCode", projectCode);
				
				
				
				if (prjList != null && prjList.size() != 0) {
					for (int k = 0; k < prjList.size(); k++) {
						Project projectObj = prjList.get(k);
						if (projectCode.equalsIgnoreCase(projectObj.getProjectCode())) {
							resultDataForJqgridMap.put("project", projectObj);
							break;
						}
					}
				}
				
				float sum = 0; 
				float stSum = 0;
				for (int k = 0; k < prjDateList.size(); k++) {
					
					Map prjDateMap = (Map)prjDateList.get(k);
					
					String date = (String)prjDateMap.get("date");
					String st = prjDateMap.get("st")+"";
					String ot = prjDateMap.get("ot")+"";
					String week = (String)prjDateMap.get("week");
					
					
					String value = null;
					
					// standard , overtime 이냐에 따라서 들어가는 데이터가 달라져야 한다.
					if (gridType == null) {
						value = st;
					} else if (gridType.equals("st")){
						value = st;
					} else if (gridType.equals("ot")){
						value = ot;
					} else if (gridType.equals("tt")){
						value = (Float.parseFloat(st) + Float.parseFloat(ot))+"";
					} else {
						value = st;
					}
					
					resultDataForJqgridMap.put((String)prjDateMap.get("date"), value);
					
					if (value != null && value.length() != 0) {
						sum += Float.parseFloat(value);
					}
					stSum += Float.parseFloat(st);
					
				}
				resultDataForJqgridMap.put("sum", sum);
				
				if (summaryMap.get(type) == null) {
					summaryMap.put(type, stSum);
				} else {
					
					
					
					
					
					
					
					float summaryTotal = (float)summaryMap.get(type);
					
					
					
					
					
					
					
					summaryMap.put(type, summaryTotal + stSum);
				}
				
				totalSum += stSum;
				resultDataForJqgridList.add(resultDataForJqgridMap);
				
			}
		}
		
		Map returnMap = new HashMap();
		returnMap.put("resultDatas", resultDataForJqgridList);
		returnMap.put("resultColumnList", dateList);
		returnMap.put("totalSum", totalSum);
		List summaryList = new ArrayList();
		summaryList.add(summaryMap);
		returnMap.put("summary", summaryList);
		
		String caption = "Standard Time";
		if (gridType != null && gridType.equals("ot")) {
			caption = "Over Time";
		} else if (gridType != null && gridType.equals("tt")) {
			caption = "Total Time";
		}
		returnMap.put("caption", caption);
		
		return returnMap;
	}

	@Override
	public Map getUserInfoOfInputHcTimeSheetForJqgrid(String userId, HcTimeSheetCond cond) throws Exception {

		String pattern = PropertiesUtil.getInstance().getDate_Pattern();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		//기본으로 조회하는 주 
		Date fromDate = cond.getWorkDateFrom();
		Date toDate = cond.getWorkDateTo();
		
		if (fromDate == null && toDate == null) {
			Date toDay = new Date();
			cond.setWorkDateFrom(DateUtil.getFirstDayOfWeek(toDay));	
			cond.setWorkDateTo(DateUtil.getLastDayOfWeek(toDay));	
		}
		
		//검색 범위의 모든 날짜들을 조회 한다 
		//Time sheet 를 작성하지 않은날도 표시가 되어야 한다.
		List<Map<String, String>> dateList = DateUtil.getDateStringListByFromTo(pattern, cond.getWorkDateFrom(), cond.getWorkDateTo());
		
		//사용자 정보를 cond를 이용하여 가져온다. 

		String condDept = cond.getDept();
		String condType = cond.getType();
		String condPosition = cond.getPosition();

		UserCond userCond = new UserCond();
		userCond.setDept(condDept);
		userCond.setType(condType);
		userCond.setPosition(condPosition);
		
		List<User> userList = getUsers(userId, userCond);
		
		if (userList ==  null || userList.size() == 0) {
			return null;
		}

		
		Map usersMap = new LinkedHashMap();
		//사용자 정보를 바탕으로 jqGrid 에 넘길 기본데이터를 만든다.
		//{userNo=10116, name=목은희, engName=Eunhee Mok, dept=Research Management, position=Research Engineer, type=MGT, projectCode=A5002, 2016/11/28=0, 2016/11/29=3.5, 2016/11/30=0, 2016/12/01=0, 2016/12/02=0, 2016/12/03=0, 2016/12/04=0, sum=3.5}
		for (int i = 0; i < userList.size(); i++) {
			
			User user = userList.get(i);
			
			String userNo = user.getUserNo();
			String name = user.getName();
			String engName = user.getEngName();
			String dept = user.getDept();
			String position = user.getPosition();
			String type = user.getType();
			String mailAddr = user.getMailAddress();
			
			Map userMap = new HashMap();
			
			if (userNo != null) {
				userMap.put("userNo", userNo);
			}
			if (name != null) {
				userMap.put("name", name);
			}
			if (engName != null) {
				userMap.put("engName", engName);
			}
			if (dept != null) {
				userMap.put("dept", dept);
			}
			if (type != null) {
				userMap.put("type", type);
			}
			if (position != null) {
				userMap.put("position", position);
			}
			if (mailAddr != null) {
				userMap.put("mailAddress", mailAddr);
			}
			//프로젝트가 처음 생성되는 순간, 검색범위 날짜 맵을 만들어서 초기화 시켜준다.
			//기본값은 X
			for (int j = 0; j < dateList.size(); j++) {
				Map dateListMap = dateList.get(j);
				String dateString = (String)dateListMap.get("date");
				String weekString = (String)dateListMap.get("week");
				String isFuture = (String)dateListMap.get("isFuture");
				if (weekString != null && (weekString.equalsIgnoreCase("sun") || weekString.equalsIgnoreCase("sat"))) {
					userMap.put(dateString, "");
				} else {
					if (isFuture != null && isFuture.equalsIgnoreCase("true")) {
						userMap.put(dateString, "");
					} else {
						userMap.put(dateString, "X");
					}
				}
			}
			
			usersMap.put(userNo, userMap);
		}
		
		
		//cond 의 정보를 이용하여 HcTimeSheet 를 가져온다.
		//Time sheet
		List<HcTimeSheet> timeSheetList = DaoFactory.getInstance().getDao().getHcTimeSheet(userId, cond);
		
		// 기본데이터에 hcTimeSheet조회 날짜를 적용하여 리턴값을 만든다.
		for (int i = 0; i < timeSheetList.size(); i++) {
			
			HcTimeSheet timeSheet = timeSheetList.get(i);
			
			String timeSheetUserNo = timeSheet.getUserNo();
			String timeSheetDate = sdf.format(timeSheet.getWorkDate());
			
			Map userMap = (Map)usersMap.get(timeSheetUserNo);
			
			if (userMap == null) {
				continue;
			}
			userMap.put(timeSheetDate, "O");
		}


		
		boolean notInputOnly = false;

		String notInputOnlyStr = cond.getNotInputOnly();
		if (notInputOnlyStr != null) {
			if (notInputOnlyStr.equalsIgnoreCase("true")) {
				notInputOnly = true;
			} else {
				notInputOnly = false;
			}
		}
		
		if (notInputOnly) {
			//미입력만 
			Map usersTempMap = new LinkedHashMap();
			if (usersMap != null && !usersMap.isEmpty()) {
				
				Iterator itr = usersMap.keySet().iterator();
				while(itr.hasNext()) {
					
					String key = (String)itr.next();
					
					System.out.println(key);
					
					Map userMap = (Map)usersMap.get(key);
					
					Iterator dateItr = userMap.keySet().iterator();
					
					boolean isNotInput = false;
					while (dateItr.hasNext()) {
						
						String dateKey = (String)dateItr.next();
						String dateValue = (String)userMap.get(dateKey);
						
						if (dateValue != null && dateValue.equalsIgnoreCase("X")) {
							isNotInput = true;
							break;
						}
					}
					if (isNotInput) {
						usersTempMap.put(key, userMap);
					}
				}
			}
			usersMap = usersTempMap;
		} 
		
		
		
		
		
		List resultDataForJqgridList = resultDataForJqgridList = new ArrayList(usersMap.values());
		
		
		Map returnMap = new HashMap();
		returnMap.put("resultDatas", resultDataForJqgridList);
		returnMap.put("resultColumnList", dateList);
		returnMap.put("caption", "User");
		
		return returnMap;
	}

	@Override
	public Map getDefaultSelectOptionForJqgrid(String userId) throws Exception {

		Map returnMap = new HashMap();
		
		List deptList = getAllDeptNameList(userId);
		returnMap.put("deptList", deptList);	

		//List projectList = getAllProjectNameList(userId);
		List projectList = getAllProjectListToSelect(userId);
		returnMap.put("projectList", projectList);	
		
		return returnMap;
	}
	
	
	
//	//time sheet 결과를 json object 형식으로 리턴한다.
//	@Override
//	public Map getHcTimeSheetJsonDatas(String userId, HcTimeSheetCond cond) throws Exception {
//
//		if (cond == null) {
//			cond = new HcTimeSheetCond();
//		}
//		
//		String pattern = PropertiesUtil.getInstance().getDate_Pattern();
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//		
//		//기본으로 조회하는 주 
//		Date fromDate = cond.getWorkDateFrom();
//		Date toDate = cond.getWorkDateTo();
//		
//		if (fromDate == null && toDate == null) {
//			Date toDay = new Date();
//			cond.setWorkDateFrom(DateUtil.getFirstDayOfWeek(toDay));	
//			cond.setWorkDateTo(DateUtil.getLastDayOfWeek(toDay));	
//		}
//
//		//검색 범위의 모든 날짜들을 조회 한다 
//		//Time sheet 를 작성하지 않은날도 표시가 되어야 한다.
//		List<Map<String, String>> dateList = DateUtil.getDateStringListByFromTo(pattern, cond.getWorkDateFrom(), cond.getWorkDateTo());
//		
//		
//		//Time sheet
//		List<HcTimeSheet> timeSheetList = DaoFactory.getInstance().getDao().getHcTimeSheet(userId, cond);
//		
//		
//		//사용자별로 데이터를 취합하기 위한 맵
//		Map<String, Map> userNameMap = new LinkedHashMap<String, Map>();
//		
//		//데이터의 빠른 조합을 위하여 모든 데이터는 맵에 저장한다. : 맵의 키로 바로 데이터에 엑세스 하기 위해 
//		for (int i = 0; i < timeSheetList.size(); i++) {
//			
//			HcTimeSheet timeSheet = timeSheetList.get(i);
//			String targetUserNo = timeSheet.getUserNo();
//			
//			//맵에서 사용자 사번으로 검색하여 데이터를 찾는다. 없으면 만든다.
//			Map<String, Object> userMap = (Map<String, Object>)userNameMap.get(targetUserNo);
//			if (userMap == null) {
//				userMap = new HashMap();
//				
//				userMap.put("name", timeSheet.getName());
//				userMap.put("engName", timeSheet.getEngName());
//				userMap.put("userNo", timeSheet.getUserNo());
//				userMap.put("type", timeSheet.getType());
//				userMap.put("position", timeSheet.getPosition());
//				userMap.put("dept", timeSheet.getDept());
//				
//				userNameMap.put(targetUserNo, userMap);
//			}				
//			
//			//맵에서 프로젝트별 날짜 데이터를 찾기 위해 맵을 호출한다. 없으면 만든다.
//			Map projectDataMap = (Map)userMap.get("projectDatas");
//			if (projectDataMap == null) {
//				projectDataMap = new HashMap();
//				userMap.put("projectDatas", projectDataMap);
//			}
//
//			String timeSheetProjectCode = timeSheet.getPrjCode();
//			String timeSheetWorkDateString = sdf.format(timeSheet.getWorkDate());
//			String timeSheetStTime = timeSheet.getSt();
//			String timeSheetOtTime = timeSheet.getOt();
//			
//			Map timesMap = (Map)projectDataMap.get(timeSheetProjectCode);
//			
//			if (timesMap == null) {
//				
//				timesMap = new LinkedHashMap();
//				
//				//프로젝트가 처음 생성되는 순간, 검색범위 날짜 맵을 만들어서 초기화 시켜준다.
//				for (int j = 0; j < dateList.size(); j++) {
//					Map dateListMap = dateList.get(j);
//					String dateString = (String)dateListMap.get("date");
//					String weekString = (String)dateListMap.get("week");
//					
//					Map timeMap = new HashMap();
//					timeMap.put("date", dateString);
//					timeMap.put("week", weekString);
//					timeMap.put("st", 0);
//					timeMap.put("ot", 0);
//					
//					timesMap.put(dateString, timeMap);
//				}
//				
//				projectDataMap.put(timeSheet.getPrjCode(), timesMap);
//			}
//			
//			//초기화된 맵에서 Time sheet 의 날짜에 해당하는 맵을 찾아 실제 데이터를 삽입힌다. 
//			Map timeMap = (Map)timesMap.get(timeSheetWorkDateString);
//			if (timeSheetStTime != null) {
//				timeMap.put("st", timeSheetStTime);
//			}
//			if (timeSheetOtTime != null) {
//				timeMap.put("ot", timeSheetOtTime);
//			}
//		}
//		
//		if (userNameMap.keySet() == null) {
//			return null;
//		}
//		
//		//make object for json 만들어진 맵 데이터를 이용하여 json 오브젝트를 생성한다. 
//		//맵을 먼저만드는 이유는 time sheet 가 건건이 조회(database쿼리) 되면서
//		//데이터(리턴값)를 만들어 나가야 하므로 리스트를 돌려 해당 데이터에 접근하는 것 보다 맵 키를 이용하여 바로접근하는 것이 성능상 좋을 것이라 판단함 
//		//맵으로 데이터셋을 완성한 후 해당 데이터셋을 이용하여 json 오브젝트를 생성 리턴한다. 
//		
//		List<Map> resultDataList = new ArrayList(userNameMap.values());
//		
//		for (int i = 0; i < resultDataList.size(); i++) {
//			
//			Map userMap = (Map)resultDataList.get(i);
//			
//			Map projectDataMap = (Map)userMap.get("projectDatas");
//			
//			if (projectDataMap != null) {
//				
//				Iterator itr = projectDataMap.keySet().iterator();
//				
//				List projectDataList = new ArrayList();
//				while (itr.hasNext()) {
//					String projectCode = (String)itr.next();
//
//					Map dateDataMap = (Map)projectDataMap.get(projectCode);
//					
//					List dateDataList = new ArrayList(dateDataMap.values());
//					
//					Map newDataMap = new LinkedHashMap();
//					newDataMap.put("projectCode", projectCode);
//					newDataMap.put("sum", "empty");
//					newDataMap.put("dateDataList", dateDataList);
//					
//					projectDataList.add(newDataMap);
//				}
//				
//				userMap.put("projectDataList", projectDataList);
//				userMap.remove("projectDatas");
//			}
//		}
//		
//		
//		Map returnMap = new HashMap();
//		returnMap.put("resultDatas", resultDataList);
//		returnMap.put("resultColumnList", dateList);
//		
//		//JSONArray jsonA = JSONArray.fromObject(returnMap);
//		
//		//System.out.println(jsonA);
//		
//		return returnMap;
//	}
}

