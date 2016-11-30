/*	
 * file 		 : DaoImpl.java
 * created by    : kmyu
 * creation-date : 2016. 11. 15.
 */

package net.smartworks.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import net.smartworks.dao.IDao;
import net.smartworks.dao.mapper.HcTimeSheetMapper;
import net.smartworks.dao.mapper.UserMapper;
import net.smartworks.model.HcTimeSheet;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.model.User;
import net.smartworks.model.UserCond;
import net.smartworks.util.DateUtil;
import net.smartworks.util.PropertiesUtil;

/* Postgresql DAO */
public class DaoImpl implements IDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplateObject;
   
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public List<User> getUsers(String userId, UserCond cond) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ").append(PropertiesUtil.getInstance().getUserTableName());

		Object[] setParamsArray = null;
		
		if (cond != null) {
			List<Object> setParams = new ArrayList<Object>();
			
			sql.append(" WHERE 1=1 ");
			
			if (cond.getDept() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getUser_UserDeptColumnName()).append(" = ?");
				setParams.add(cond.getDept());
			}
			if (cond.getPosition() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getUser_UserPositionColumnName()).append(" = ?");
				setParams.add(cond.getPosition());
			}
			if (cond.getResign() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getUser_UserResignColumnName()).append(" = ?");
				setParams.add(cond.getResign());
			}
			if (cond.getType() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getUser_UserTypeColumnName()).append(" = ?");
				setParams.add(cond.getType());
			}
			if (setParams.size() != 0) {
				setParamsArray = new Object[setParams.size()];
				setParams.toArray(setParamsArray);
			}
		}
		sql.append(" ORDER BY ").append(PropertiesUtil.getInstance().getUser_UserDeptColumnName()).append(" , ").append(PropertiesUtil.getInstance().getUser_UserNameColumnName());
		
		List<User> userList = jdbcTemplateObject.query(sql.toString(), setParamsArray, new UserMapper());
		return userList;
	}

	@Override
	public User getUser(String userId, String targetUserId) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ").append(PropertiesUtil.getInstance().getUserTableName());
		sql.append(" WHERE ").append(PropertiesUtil.getInstance().getUser_UserIdColumnName()).append(" = ?");
		User user = jdbcTemplateObject.queryForObject(sql.toString(), new Object[] { targetUserId }, new UserMapper());
		
	    return user;
	}

	@Override
	public List<String> getAllDeptNameList(String userId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ").append(PropertiesUtil.getInstance().getUser_UserDeptColumnName()).append(" FROM ").append(PropertiesUtil.getInstance().getUserTableName());
		sql.append(" ORDER BY ").append(PropertiesUtil.getInstance().getUser_UserDeptColumnName());
		List<String> result = jdbcTemplateObject.queryForList(sql.toString(), String.class);
		
		return result;
	}

	@Override
	public List<String> getAllUserTypeList(String userId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ").append(PropertiesUtil.getInstance().getUser_UserTypeColumnName()).append(" FROM ").append(PropertiesUtil.getInstance().getUserTableName());
		sql.append(" ORDER BY ").append(PropertiesUtil.getInstance().getUser_UserTypeColumnName());
		
		List<String> result = jdbcTemplateObject.queryForList(sql.toString(), String.class);
		
		return result;
	}

	@Override
	public List<String> getAllProjectNameList(String userId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ").append(PropertiesUtil.getInstance().getPrj_CodeColumn()).append(" FROM ").append(PropertiesUtil.getInstance().getProjectCodeTableName());
		sql.append(" ORDER BY ").append(PropertiesUtil.getInstance().getPrj_CodeColumn());
		List<String> result = jdbcTemplateObject.queryForList(sql.toString(), String.class);
		
		return result;
	}

	@Override
	public List<HcTimeSheet> getHcTimeSheet(String userId, HcTimeSheetCond cond) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ").append(PropertiesUtil.getInstance().getTimeSheetTableName());

		Object[] setParamsArray = null;
		
		if (cond != null) {
			List<Object> setParams = new ArrayList<Object>();
			
			sql.append(" WHERE 1=1 ");
			
			if (cond.getWorkDateFrom() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getTs_CreateDateColumnName()).append(" >= ?");
				setParams.add(DateUtil.convertGMTDate(cond.getWorkDateFrom()));
			}
			if (cond.getWorkDateTo() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getTs_CreateDateColumnName()).append(" <= ?");
				setParams.add(DateUtil.convertGMTDate(cond.getWorkDateTo()));
			}
			if (cond.getDept() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getTs_DetpColumnName()).append(" = ?");
				setParams.add(cond.getDept());
			}
			if (cond.getType() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getTs_UserTypeColumnName()).append(" = ?");
				setParams.add(cond.getType());
			}
			if (cond.getPrjCode() != null) {
				sql.append(" AND ").append(PropertiesUtil.getInstance().getTs_ProjectColumnName()).append(" = ?");
				setParams.add(cond.getPrjCode());
			}
			if (setParams.size() != 0) {
				setParamsArray = new Object[setParams.size()];
				setParams.toArray(setParamsArray);
			}
		}
		
		List<HcTimeSheet> hcTimeSheetList = jdbcTemplateObject.query(sql.toString(), setParamsArray, new HcTimeSheetMapper());
		
		return hcTimeSheetList;
		
	}
	
	
	
	
	
	
	
	

//	private List<Object> setTaskQuery(StringBuffer query, TaskCond cond) throws Exception {
//		
//		//List<Filter> filters = cond.getFilters();
//		
//		String title = cond.getTitle();
//		String createdUser = cond.getCreatedUser();
//		
//		String taskType = cond.getTaskType();
//		String completeYn = cond.getCompleteYn();
//		
//		Date startDateFrom = cond.getStartDateFrom();
//		Date startDateTo = cond.getStartDateTo();
//		Date endDateFrom = cond.getEndDateFrom();
//		Date endDateTo = cond.getEndDateTo();
//		
//		
//		List<Object> setParams = new ArrayList<Object>();
//		
//		query.append(" 	FROM  ");
//		query.append(" 	TASK ");
//		query.append(" 	WHERE 1=1 ");
//		
//		if (title != null) {
//			query.append(" AND TITLE = ? ");
//			setParams.add(title);
//		}
//		if (createdUser != null) {
//			query.append(" AND CREATEDUSER = ? ");
//			setParams.add(createdUser);
//		}
//		if (taskType != null) {
//			query.append(" AND TASKTYPE = ? ");
//			setParams.add(taskType);
//		}
//		if (completeYn != null) {
//			query.append(" AND COMPLETEYN = ? ");
//			setParams.add(CommonUtil.toBoolean(completeYn));
//		}
//		if (startDateFrom != null) {
//			query.append(" AND STARTDATE > ? ");
//			setParams.add(startDateFrom);
//		}
//		if (startDateTo != null) {
//			query.append(" AND STARTDATE < ? ");
//			setParams.add(startDateTo);
//		}
//		if (endDateFrom != null) {
//			query.append(" AND ENDDATE > ? ");
//			setParams.add(endDateFrom);
//		}
//		if (endDateTo != null) {
//			query.append(" AND ENDDATE < ? ");
//			setParams.add(endDateTo);
//		}
//		return setParams;
//	}
//	
//	@Override
//	public long getTaskSize(String userId, TaskCond cond) throws Exception {
//		
//		StringBuffer query = new StringBuffer();
//		query.append("select count(*) ");
//		
//		List<Object> setParam = setTaskQuery(query, cond);
//		
//		Object[] setParamsArray = null;
//		if (setParam.size() != 0) {
//			setParamsArray = new Object[setParam.size()];
//			setParam.toArray(setParamsArray);
//		}
//		
//		Long totalSize = jdbcTemplateObject.queryForObject(query.toString(), setParamsArray ,Long.class);
//		
//		return totalSize;
//	}
//	
//	@Override
//	public List<Task> getTasks(String userId, TaskCond cond) throws Exception {
//		
//		final int pageNo = cond.getPageNo();
//		final int pageSize = cond.getPageSize();
//		final int offSet = pageNo == 0 ? 0 : pageNo * pageSize;
//		
//		StringBuffer query = new StringBuffer();
//		query.append(" select * ");
//		
//		List<Object> setParam = setTaskQuery(query, cond);
//		
//		
//		String orderColumn = cond.getOrderColumn();
//		if (orderColumn != null) {
//			query.append(" order by ").append(orderColumn);
//			if (cond.isDescending())
//				query.append(" desc ");
//		}
//		
//		if (pageSize != -1 && pageNo != -1) {
//			query.append(" limit ? ");
//			query.append(" offset ? ");
//			
//			setParam.add(pageSize);
//			setParam.add(offSet);
//		}
//		Object[] setParamsArray = null;
//		if (setParam.size() != 0) {
//			setParamsArray = new Object[setParam.size()];
//			setParam.toArray(setParamsArray);
//		}
//		
//		List<Task> attrList = jdbcTemplateObject.query(query.toString(), setParamsArray, new TaskMapper());
//		
//		return attrList;
//	}

}

