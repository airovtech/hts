/*	
 * file 		 : IManager.java
 * created by    : kmyu
 * creation-date : 2016. 11. 15.
 */

package net.smartworks.manager;

import java.util.List;
import java.util.Map;

import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.model.Project;
import net.smartworks.model.User;
import net.smartworks.model.UserCond;

public interface IManager {

//	public long getTaskSize(String userId, TaskCond cond) throws Exception;
//	public List<Task> getTasks(String userId, TaskCond cond) throws Exception;
	
	public List<User> getUsers(String userId, UserCond cond) throws Exception;
	public User getUser(String userId, String targetUserId) throws Exception;
	
	public List<String> getAllDeptNameList(String userId) throws Exception;
	public List<String> getAllUserTypeList(String userId) throws Exception;
	
	public List<String> getAllProjectNameList(String userId) throws Exception;
	public List<Project> getAllProjectListToSelect(String userId) throws Exception;
	
	public Map getHcTimeSheetJsonDatasForJqgrid(String userId, HcTimeSheetCond cond) throws Exception;
	//public Map getHcTimeSheetJsonDatas(String userId, HcTimeSheetCond cond) throws Exception;
	
	//hc time sheet 작성유무에 대한 데이터
	public Map getUserInfoOfInputHcTimeSheetForJqgrid(String userId, HcTimeSheetCond cond) throws Exception;
	
	//부서 , 프로젝트 select 박스를 위한 기본 options 
	public Map getDefaultSelectOptionForJqgrid(String userId) throws Exception;
	
}

