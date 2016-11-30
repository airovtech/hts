/*	
 * file 		 : User.java
 * created by    : kmyu
 * creation-date : 2016. 11. 25.
 */

package net.smartworks.model;

import java.util.Date;

public class UserCond {

	private String userId;//사용자아이디 
	private String userNo;//사용자사번 
	private String type; // 구분  
	private String name;//이름 
	private String engName;//영문이름 
	private String dept;//부서 
	private String position;//직급 
	private String mailAddress;//메일주소 
	private String resign;//퇴사여부 
	private Date resignDate;//퇴사일 
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getResign() {
		return resign;
	}
	public void setResign(String resign) {
		this.resign = resign;
	}
	public Date getResignDate() {
		return resignDate;
	}
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	
}

