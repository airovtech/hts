package net.smartworks.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import net.smartworks.model.HcTimeSheet;
import net.smartworks.util.DateUtil;
import net.smartworks.util.PropertiesUtil;

public class HcTimeSheetMapper implements RowMapper<HcTimeSheet> {

	@Override
	public HcTimeSheet mapRow(ResultSet rs, int rowNum) throws SQLException {

		HcTimeSheet hcTimeSheet = new HcTimeSheet();
		
		hcTimeSheet.setObjId(rs.getString("id"));
		Timestamp workDate = rs.getTimestamp((PropertiesUtil.getInstance().getTs_CreateDateColumnName()));
		//System.out.println(workDate);
		hcTimeSheet.setWorkDate(DateUtil.convertLocaleDate(workDate));
		hcTimeSheet.setType(rs.getString(PropertiesUtil.getInstance().getTs_UserTypeColumnName()));
		hcTimeSheet.setDept(rs.getString(PropertiesUtil.getInstance().getTs_DetpColumnName()));
		hcTimeSheet.setPosition(rs.getString(PropertiesUtil.getInstance().getTs_UserPositionColumnName()));
		hcTimeSheet.setUserNo(rs.getString(PropertiesUtil.getInstance().getTs_UserNoColumnName()));
		hcTimeSheet.setName(rs.getString(PropertiesUtil.getInstance().getTs_UserNameColumnName()));
		hcTimeSheet.setEngName(rs.getString(PropertiesUtil.getInstance().getTs_UserEngNameColumnName()));
		hcTimeSheet.setPrjCode(rs.getString(PropertiesUtil.getInstance().getTs_ProjectColumnName()));
		hcTimeSheet.setSt(rs.getString(PropertiesUtil.getInstance().getTs_StandardTimeColumnName()));
		hcTimeSheet.setOt(rs.getString(PropertiesUtil.getInstance().getTs_OverTimeColumnName()));

		return hcTimeSheet;
	}
	
}
