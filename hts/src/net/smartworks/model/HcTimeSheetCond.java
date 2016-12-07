/*	
 * file 		 : HcTimeSheet.java
 * created by    : kmyu
 * creation-date : 2016. 11. 28.
 */

package net.smartworks.model;

import java.util.Date;

public class HcTimeSheetCond {

	private String objId;
	
	private String gridType;
	
	private Date workDate;
	
	private Date workDateFrom;
	private Date workDateTo;
	
	private String type;
	private String Dept;
	private String position;
	private String userId;
	private String userNo;
	private String name;
	private String engName;
	private String prjCode;
	private String st;
	private String ot;
	
	private String notInputOnly;
	
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Date getWorkDateFrom() {
		return workDateFrom;
	}
	public void setWorkDateFrom(Date workDateFrom) {
		this.workDateFrom = workDateFrom;
	}
	public Date getWorkDateTo() {
		return workDateTo;
	}
	public void setWorkDateTo(Date workDateTo) {
		this.workDateTo = workDateTo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getPrjCode() {
		return prjCode;
	}
	public void setPrjCode(String prjCode) {
		this.prjCode = prjCode;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getOt() {
		return ot;
	}
	public void setOt(String ot) {
		this.ot = ot;
	}
	public String getGridType() {
		return gridType;
	}
	public void setGridType(String gridType) {
		this.gridType = gridType;
	}
	public String getNotInputOnly() {
		return notInputOnly;
	}
	public void setNotInputOnly(String notInputOnly) {
		this.notInputOnly = notInputOnly;
	}
}

