
package net.smartworks.util;

public class PropertiesUtil {

	private static PropertiesUtil util;

	public static PropertiesUtil getInstance() {
		if(util == null)
			util = new PropertiesUtil();
		return util;
	}
	public static synchronized PropertiesUtil createInstance() {
		if(util == null)
			util = new PropertiesUtil();
		return util;
	}
	
	private String mailContentsUrl_Person;
	private String mailContentsUrl_Leader;
	private String serverUrl;
	
	//Mail
	
	private String mail_SenderMailServerSMTP;
	private String mail_SenderId;
	private String mail_SenderPassword;
	
	private String mail_TargetDomainAddressLock;
	private String mail_TargetDomainAddress;

	private String mail_log_folder;


	//Time Sheet
	private String timeSheetTableName;
	
	private String ts_CreateDateColumnName;
	private String ts_UserTypeColumnName;
	private String ts_DetpColumnName;
	private String ts_UserPositionColumnName;
	private String ts_UserNoColumnName;
	private String ts_UserNameColumnName;
	private String ts_UserEngNameColumnName;
	private String ts_ProjectColumnName;
	private String ts_StandardTimeColumnName;
	private String ts_OverTimeColumnName;
	private String ts_TotalTimeColumnName;

	//RnD User
	private String userTableName;
	
	private String user_UserIdColumnName;
	private String user_UserNoColumnName;
	private String user_UserTypeColumnName;
	private String user_UserNameColumnName;
	private String user_UserEngNameColumnName;
	private String user_UserDeptColumnName;
	private String user_UserPositionColumnName;
	private String user_UserMailAddrColumnName;
	private String user_UserResignColumnName;
	private String user_UserResignDateColumnName;
	
	private String user_UserTeamLeaderColumnName;
	
	//ProjectCode
	private String projectCodeTableName;
	
	private String prj_CodeColumn;
	private String prj_PmColumn;
	private String prj_SalesColumn;
	private String prj_DescriptionColumn;
	private String prj_CustomerColumn;
	private String prj_ProductTypeColumn;
	private String prj_DevStartColumn;
	private String prj_SopColumn;
	private String prj_ReportColumn;
	
	
	//etc
	private String date_Pattern;
	
	
	public String getMailContentsUrl_Person() {
		return mailContentsUrl_Person;
	}
	public void setMailContentsUrl_Person(String mailContentsUrl_Person) {
		this.mailContentsUrl_Person = mailContentsUrl_Person;
	}
	public String getMailContentsUrl_Leader() {
		return mailContentsUrl_Leader;
	}
	public void setMailContentsUrl_Leader(String mailContentsUrl_Leader) {
		this.mailContentsUrl_Leader = mailContentsUrl_Leader;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getTimeSheetTableName() {
		return timeSheetTableName;
	}
	public void setTimeSheetTableName(String timeSheetTableName) {
		this.timeSheetTableName = timeSheetTableName;
	}
	public String getTs_CreateDateColumnName() {
		return ts_CreateDateColumnName;
	}
	public void setTs_CreateDateColumnName(String ts_CreateDateColumnName) {
		this.ts_CreateDateColumnName = ts_CreateDateColumnName;
	}
	public String getTs_UserTypeColumnName() {
		return ts_UserTypeColumnName;
	}
	public void setTs_UserTypeColumnName(String ts_UserTypeColumnName) {
		this.ts_UserTypeColumnName = ts_UserTypeColumnName;
	}
	public String getTs_DetpColumnName() {
		return ts_DetpColumnName;
	}
	public void setTs_DetpColumnName(String ts_DetpColumnName) {
		this.ts_DetpColumnName = ts_DetpColumnName;
	}
	public String getTs_UserPositionColumnName() {
		return ts_UserPositionColumnName;
	}
	public void setTs_UserPositionColumnName(String ts_UserPositionColumnName) {
		this.ts_UserPositionColumnName = ts_UserPositionColumnName;
	}
	public String getTs_UserNoColumnName() {
		return ts_UserNoColumnName;
	}
	public void setTs_UserNoColumnName(String ts_UserNoColumnName) {
		this.ts_UserNoColumnName = ts_UserNoColumnName;
	}
	public String getTs_UserNameColumnName() {
		return ts_UserNameColumnName;
	}
	public void setTs_UserNameColumnName(String ts_UserNameColumnName) {
		this.ts_UserNameColumnName = ts_UserNameColumnName;
	}
	public String getTs_UserEngNameColumnName() {
		return ts_UserEngNameColumnName;
	}
	public void setTs_UserEngNameColumnName(String ts_UserEngNameColumnName) {
		this.ts_UserEngNameColumnName = ts_UserEngNameColumnName;
	}
	public String getTs_ProjectColumnName() {
		return ts_ProjectColumnName;
	}
	public void setTs_ProjectColumnName(String ts_ProjectColumnName) {
		this.ts_ProjectColumnName = ts_ProjectColumnName;
	}
	public String getTs_StandardTimeColumnName() {
		return ts_StandardTimeColumnName;
	}
	public void setTs_StandardTimeColumnName(String ts_StandardTimeColumnName) {
		this.ts_StandardTimeColumnName = ts_StandardTimeColumnName;
	}
	public String getTs_OverTimeColumnName() {
		return ts_OverTimeColumnName;
	}
	public void setTs_OverTimeColumnName(String ts_OverTimeColumnName) {
		this.ts_OverTimeColumnName = ts_OverTimeColumnName;
	}
	public String getTs_TotalTimeColumnName() {
		return ts_TotalTimeColumnName;
	}
	public void setTs_TotalTimeColumnName(String ts_TotalTimeColumnName) {
		this.ts_TotalTimeColumnName = ts_TotalTimeColumnName;
	}
	public String getUserTableName() {
		return userTableName;
	}
	public void setUserTableName(String userTableName) {
		this.userTableName = userTableName;
	}
	public String getUser_UserIdColumnName() {
		return user_UserIdColumnName;
	}
	public void setUser_UserIdColumnName(String user_UserIdColumnName) {
		this.user_UserIdColumnName = user_UserIdColumnName;
	}
	public String getUser_UserNoColumnName() {
		return user_UserNoColumnName;
	}
	public void setUser_UserNoColumnName(String user_UserNoColumnName) {
		this.user_UserNoColumnName = user_UserNoColumnName;
	}
	public String getUser_UserTypeColumnName() {
		return user_UserTypeColumnName;
	}
	public void setUser_UserTypeColumnName(String user_UserTypeColumnName) {
		this.user_UserTypeColumnName = user_UserTypeColumnName;
	}
	public String getUser_UserNameColumnName() {
		return user_UserNameColumnName;
	}
	public void setUser_UserNameColumnName(String user_UserNameColumnName) {
		this.user_UserNameColumnName = user_UserNameColumnName;
	}
	public String getUser_UserEngNameColumnName() {
		return user_UserEngNameColumnName;
	}
	public void setUser_UserEngNameColumnName(String user_UserEngNameColumnName) {
		this.user_UserEngNameColumnName = user_UserEngNameColumnName;
	}
	public String getUser_UserDeptColumnName() {
		return user_UserDeptColumnName;
	}
	public void setUser_UserDeptColumnName(String user_UserDeptColumnName) {
		this.user_UserDeptColumnName = user_UserDeptColumnName;
	}
	public String getUser_UserPositionColumnName() {
		return user_UserPositionColumnName;
	}
	public void setUser_UserPositionColumnName(String user_UserPositionColumnName) {
		this.user_UserPositionColumnName = user_UserPositionColumnName;
	}
	public String getUser_UserMailAddrColumnName() {
		return user_UserMailAddrColumnName;
	}
	public void setUser_UserMailAddrColumnName(String user_UserMailAddrColumnName) {
		this.user_UserMailAddrColumnName = user_UserMailAddrColumnName;
	}
	public String getUser_UserResignColumnName() {
		return user_UserResignColumnName;
	}
	public void setUser_UserResignColumnName(String user_UserResignColumnName) {
		this.user_UserResignColumnName = user_UserResignColumnName;
	}
	public String getUser_UserResignDateColumnName() {
		return user_UserResignDateColumnName;
	}
	public void setUser_UserResignDateColumnName(String user_UserResignDateColumnName) {
		this.user_UserResignDateColumnName = user_UserResignDateColumnName;
	}
	public String getProjectCodeTableName() {
		return projectCodeTableName;
	}
	public void setProjectCodeTableName(String projectCodeTableName) {
		this.projectCodeTableName = projectCodeTableName;
	}
	public String getPrj_CodeColumn() {
		return prj_CodeColumn;
	}
	public void setPrj_CodeColumn(String prj_CodeColumn) {
		this.prj_CodeColumn = prj_CodeColumn;
	}
	public String getPrj_PmColumn() {
		return prj_PmColumn;
	}
	public void setPrj_PmColumn(String prj_PmColumn) {
		this.prj_PmColumn = prj_PmColumn;
	}
	public String getPrj_SalesColumn() {
		return prj_SalesColumn;
	}
	public void setPrj_SalesColumn(String prj_SalesColumn) {
		this.prj_SalesColumn = prj_SalesColumn;
	}
	public String getPrj_CustomerColumn() {
		return prj_CustomerColumn;
	}
	public void setPrj_CustomerColumn(String prj_CustomerColumn) {
		this.prj_CustomerColumn = prj_CustomerColumn;
	}
	public String getPrj_ProductTypeColumn() {
		return prj_ProductTypeColumn;
	}
	public void setPrj_ProductTypeColumn(String prj_ProductTypeColumn) {
		this.prj_ProductTypeColumn = prj_ProductTypeColumn;
	}
	public String getPrj_DevStartColumn() {
		return prj_DevStartColumn;
	}
	public void setPrj_DevStartColumn(String prj_DevStartColumn) {
		this.prj_DevStartColumn = prj_DevStartColumn;
	}
	public String getPrj_SopColumn() {
		return prj_SopColumn;
	}
	public void setPrj_SopColumn(String prj_SopColumn) {
		this.prj_SopColumn = prj_SopColumn;
	}

	public String getPrj_DescriptionColumn() {
		return prj_DescriptionColumn;
	}
	public void setPrj_DescriptionColumn(String prj_DescriptionColumn) {
		this.prj_DescriptionColumn = prj_DescriptionColumn;
	}
	public String getPrj_ReportColumn() {
		return prj_ReportColumn;
	}
	public void setPrj_ReportColumn(String prj_ReportColumn) {
		this.prj_ReportColumn = prj_ReportColumn;
	}
	public String getDate_Pattern() {
		return date_Pattern;
	}
	public void setDate_Pattern(String date_Pattern) {
		this.date_Pattern = date_Pattern;
	}
	public String getMail_SenderMailServerSMTP() {
		return mail_SenderMailServerSMTP;
	}
	public void setMail_SenderMailServerSMTP(String mail_SenderMailServerSMTP) {
		this.mail_SenderMailServerSMTP = mail_SenderMailServerSMTP;
	}
	public String getMail_SenderId() {
		return mail_SenderId;
	}
	public void setMail_SenderId(String mail_SenderId) {
		this.mail_SenderId = mail_SenderId;
	}
	public String getMail_SenderPassword() {
		return mail_SenderPassword;
	}
	public void setMail_SenderPassword(String mail_SenderPassword) {
		this.mail_SenderPassword = mail_SenderPassword;
	}
	public String getMail_TargetDomainAddressLock() {
		return mail_TargetDomainAddressLock;
	}
	public void setMail_TargetDomainAddressLock(String mail_TargetDomainAddressLock) {
		this.mail_TargetDomainAddressLock = mail_TargetDomainAddressLock;
	}
	public String getMail_TargetDomainAddress() {
		return mail_TargetDomainAddress;
	}
	public void setMail_TargetDomainAddress(String mail_TargetDomainAddress) {
		this.mail_TargetDomainAddress = mail_TargetDomainAddress;
	}
	public String getUser_UserTeamLeaderColumnName() {
		return user_UserTeamLeaderColumnName;
	}
	public void setUser_UserTeamLeaderColumnName(String user_UserTeamLeaderColumnName) {
		this.user_UserTeamLeaderColumnName = user_UserTeamLeaderColumnName;
	}
	public String getMail_log_folder() {
		return mail_log_folder;
	}
	public void setMail_log_folder(String mail_log_folder) {
		this.mail_log_folder = mail_log_folder;
	}

}