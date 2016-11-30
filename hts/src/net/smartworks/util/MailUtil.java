/*	
 * file 		 : MailUtil.java
 * created by    : kmyu
 * creation-date : 2016. 11. 25.
 */

package net.smartworks.util;

import java.util.ArrayList;
import java.util.List;

import net.smartworks.model.Property;
import net.smartworks.model.User;

public class MailUtil {
	
	public static final String SENDTYPE_PERSON = "person";
	public static final String SENDTYPE_LEADER = "leader";
	
	
	public static void sendMail(String sendType, User[] users) throws Exception {
		
		if (users == null || users.length == 0)
			return;
		
		String mailContentsUrl = null;
		
		if (sendType == null) {
			sendType = SENDTYPE_PERSON;
		} else {
			if (sendType.equals(SENDTYPE_LEADER)) {
				mailContentsUrl = PropertiesUtil.getInstance().getMailContentsUrl_Leader();
			} else {
				mailContentsUrl = PropertiesUtil.getInstance().getMailContentsUrl_Person();
			}
		}
		
		for (int i = 0; i < users.length; i++) {
			User user = users[i];
			
			if (user.getUserId() == null || user.getMailAddress() == null || mailContentsUrl == null) {
				continue;
			}
			
			MailSender mailSender = new MailSender(user.getUserId(), user.getMailAddress(), mailContentsUrl, "from date..","to date..");
			mailSender.start();
		}
	}
}

class MailSender extends Thread {
	
	private String targetUserId;
	private String targetUserMailAddress;
	private String mailContentUrl;
	
	private String fromDate;
	private String toDate;
	
	public MailSender(String targetUserId, String targetUserMailAddress , String mailContentUrl, String fromDate, String toDate) {
		this.targetUserId = targetUserId;
		this.targetUserMailAddress = targetUserMailAddress;
		this.mailContentUrl = mailContentUrl;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public void run() {
		
		System.out.println(toMailContent(targetUserId, fromDate, toDate));
		
	}
	public String toMailContent(String userId, String fromDate, String toDate) {
		
		String content = null;
		if (mailContentUrl != null) {
			try {
				List paramList = new ArrayList();
				if (userId != null) {
					paramList.add(new Property("userId", userId));
				}
				if (fromDate != null) {
					paramList.add(new Property("fromDate", fromDate));
				}
				if (toDate != null) {
					paramList.add(new Property("toDate", toDate));
				}
				Property[] params = null;
				if (!paramList.isEmpty()) {
					params = new Property[paramList.size()];
					paramList.toArray(params);
				}
				content = ServletUtil.request(PropertiesUtil.getInstance().getServerUrl() + mailContentUrl, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}