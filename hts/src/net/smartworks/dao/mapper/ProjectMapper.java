package net.smartworks.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.smartworks.model.Project;
import net.smartworks.util.PropertiesUtil;

public class ProjectMapper implements RowMapper<Project> {

	@Override
	public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

		Project prj = new Project();
		
		prj.setProjectCode(rs.getString(PropertiesUtil.getInstance().getPrj_CodeColumn()));
		prj.setPm(rs.getString(PropertiesUtil.getInstance().getPrj_PmColumn()));
		prj.setSales(rs.getString(PropertiesUtil.getInstance().getPrj_SalesColumn()));
		prj.setDescription(rs.getString(PropertiesUtil.getInstance().getPrj_DescriptionColumn()));
		prj.setCustomer(rs.getString(PropertiesUtil.getInstance().getPrj_CustomerColumn()));
		prj.setProductType(rs.getString(PropertiesUtil.getInstance().getPrj_ProductTypeColumn()));
		prj.setDevStart(rs.getString(PropertiesUtil.getInstance().getPrj_DevStartColumn()));
		prj.setSop(rs.getString(PropertiesUtil.getInstance().getPrj_SopColumn()));
		//prj.setReport(rs.getBoolean(PropertiesUtil.getInstance().getPrj_ReportColumn()) + "");

		return prj;
	}
	
}
