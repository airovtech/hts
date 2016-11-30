package net.smartworks.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.smartworks.model.User;
import net.smartworks.util.PropertiesUtil;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		
		user.setUserId(rs.getString(PropertiesUtil.getInstance().getUser_UserIdColumnName()));
		user.setUserNo(rs.getString(PropertiesUtil.getInstance().getUser_UserNoColumnName()));
		user.setType(rs.getString(PropertiesUtil.getInstance().getUser_UserTypeColumnName()));
		user.setName(rs.getString(PropertiesUtil.getInstance().getUser_UserNameColumnName()));
		user.setEngName(rs.getString(PropertiesUtil.getInstance().getUser_UserEngNameColumnName()));
		user.setDept(rs.getString(PropertiesUtil.getInstance().getUser_UserDeptColumnName()));
		user.setPosition(rs.getString(PropertiesUtil.getInstance().getUser_UserPositionColumnName()));
		user.setMailAddress(rs.getString(PropertiesUtil.getInstance().getUser_UserMailAddrColumnName()));
		user.setResign(rs.getString(PropertiesUtil.getInstance().getUser_UserResignColumnName()));
		user.setResignDate(rs.getDate(PropertiesUtil.getInstance().getUser_UserResignDateColumnName()));

		return user;
	}
	
}
