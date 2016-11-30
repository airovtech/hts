/*	
 * file 		 : IDao.java
 * created by    : kmyu
 * creation-date : 2016. 11. 24.
 */

package net.smartworks.dao;

import java.util.List;

import net.smartworks.model.HcTimeSheet;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.model.User;
import net.smartworks.model.UserCond;

public interface IDao {

	public List<User> getUsers(String userId, UserCond cond) throws Exception;
	public User getUser(String userId, String targetUserId) throws Exception;
	
	public List<String> getAllDeptNameList(String userId) throws Exception;
	public List<String> getAllUserTypeList(String userId) throws Exception;
	
	public List<String> getAllProjectNameList(String userId) throws Exception;
	
	public List<HcTimeSheet> getHcTimeSheet(String userId, HcTimeSheetCond cond) throws Exception;
}

