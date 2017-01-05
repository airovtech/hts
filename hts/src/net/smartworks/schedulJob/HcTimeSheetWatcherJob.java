/* 
 * $Id$
 * created by    : yukm
 * creation-date : 2013. 3. 13.
 * =========================================================
 * Copyright (c) 2012 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.schedulJob;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import net.sf.json.JSONArray;
import net.smartworks.factory.DaoFactory;
import net.smartworks.factory.ManagerFactory;
import net.smartworks.model.HcTimeSheetCond;
import net.smartworks.model.Property;
import net.smartworks.model.User;
import net.smartworks.model.UserCond;
import net.smartworks.util.PropertiesUtil;
import net.smartworks.util.ServletUtil;

public class HcTimeSheetWatcherJob  extends QuartzJobBean   {
	
	private static final String LEADER = "LEADER";
	private static final String PERSON = "PERSON";
	
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		try {

			String userId = "";
			HcTimeSheetCond cond = new HcTimeSheetCond();
			Map result = ManagerFactory.getInstance().getManager().getUserInfoOfInputHcTimeSheetForJqgrid(userId, cond);
			
			if (result == null) {
				System.out.println("Mail Target User is Null!!");
				return;
			}
			List mailTarget = new ArrayList();
			
			List userList = (List)result.get("resultDatas");
			List dateList = (List)result.get("resultColumnList");

			if (userList == null) {
				System.out.println("Mail Target UserList is Null!!");
				return;
			}
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(PropertiesUtil.getInstance().getDate_Pattern());
			String nowStr = sdf.format(now);
			
			for (int i = 0; i < userList.size(); i++) {
				
				Map userMap = (Map)userList.get(i);
				String userNo = (String)userMap.get("userNo");
				
				for (int j = 0; j < dateList.size(); j++) {
					
					Map dateMap = (Map)dateList.get(j);
					String date = (String)dateMap.get("date");
					String week = (String)dateMap.get("week");
					
					String flag = (String)userMap.get(date);
					
					//주말에는 작성하지 않아도 알림 메일이 가지 않는다. 
					if ((week != null && !week.equalsIgnoreCase("sun") && !week.equalsIgnoreCase("sat")) && (flag == null || flag.equalsIgnoreCase("X"))) {
						
						//오늘에 미등록도 메일이 가지 않는다. 2016/12/26
						if (nowStr.equals(date)) {
							continue;
						}
						
						mailTarget.add(userMap);
						break;
					}
				}
			}
			
			////////////////////////// 개인용 메일 발송 ////////////////////////
			
			for (int i = 0; i < mailTarget.size(); i++) {
				
				Map targetUserMap = (Map)mailTarget.get(i);
				//System.out.println("메일 발송 : " + targetUserMap.get("userNo") + "("+targetUserMap+")");
				
				String toUser = (String)targetUserMap.get("mailAddress");
				
				List targetUserMapList = new ArrayList();
				targetUserMapList.add(targetUserMap);

				System.out.println("PERSON MAIL SENDING...." + toUser);
				SendMailTask mailTask = new SendMailTask(toUser , targetUserMapList, HcTimeSheetWatcherJob.PERSON, dateList);
				mailTask.start();
			}
			
			///////////////////////// 팀장 메일 발송 /////////////////////////////
			
			//부서별 미작성자 정보 맵 
			//부서아이디: 미작성자 정보 맵리스트 
			Map<String, List> teamUserMap = new HashMap<String, List>();
			
			for (int i = 0; i < mailTarget.size(); i++) {
				
				Map targetUserMap = (Map)mailTarget.get(i);
				
				String toUser = (String)targetUserMap.get("mailAddress");
				String dept = (String)targetUserMap.get("dept");
				
				List teamUserList = (List)teamUserMap.get(dept);
				
				if (teamUserList == null) {
					List newTeamUserList = new ArrayList();
					newTeamUserList.add(targetUserMap);
					teamUserMap.put(dept, newTeamUserList);
				} else {
					teamUserList.add(targetUserMap);
				}
			}
			
			// 부서코드:리더 메일 주소 를 API 를 이용하여 받아와야 한다. 
			
			UserCond userCond = new UserCond();
			userCond.setTeamLeader("true");
			List<User> leaders = DaoFactory.getInstance().getDao().getUsers(userId, userCond);
			
			if (leaders == null || leaders.size() == 0) {
				System.out.println("TeamLeader is Null~");
				return;
			}
			Map teamLeaderMap = new HashMap();
			for (int i = 0; i < leaders.size(); i++) {
				User user = leaders.get(i);
				teamLeaderMap.put(user.getDept(), user.getMailAddress());
			}
			
			if (!teamUserMap.isEmpty()) {
				
				Iterator itr = teamUserMap.keySet().iterator();
				while(itr.hasNext()){
					
					String deptKey = (String)itr.next();
					
					List teamUserList = teamUserMap.get(deptKey);
					if (teamUserList == null || teamUserList.size() == 0) {
						continue;
					}
					String teamLeader = (String)teamLeaderMap.get(deptKey);
					if (teamLeader == null || teamLeader.length() == 0) {
						continue;
					}
					System.out.println("TEAM LEADER MAIL SENDING...." + teamLeader);
					SendMailTask mailTask = new SendMailTask(teamLeader , teamUserList, HcTimeSheetWatcherJob.LEADER, dateList);
					mailTask.start();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private class SendMailTask extends Thread {
		
		private String toUserAddress;
		
		private List<Map> userMapList;
		
		//PERSON, LEADER
		private String mailType;
		
		//date columnList
		private List columnList;
		
		public SendMailTask(String toUserAddress , List<Map> userMapList, String mailType, List columnList) {
			this.toUserAddress = toUserAddress;
			this.userMapList = userMapList;
			this.mailType = mailType;
			this.columnList = columnList;
		}
		public void run() {
			try {
				
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(PropertiesUtil.getInstance().getDate_Pattern());
				
				String to = toUserAddress;
				if (to == null)
					return;
				String from = PropertiesUtil.getInstance().getMail_SenderId();
				String title = null;
				if (mailType.equalsIgnoreCase(HcTimeSheetWatcherJob.LEADER)) {
					title = "Time Sheet 팀 미입력 레포트(팀장용) - " + sdf.format(today) + ".";
				} else {
					title = "Time Sheet 미입력 레포트 - " + sdf.format(today) + ".";
				}
				String content = toMailContent(userMapList, mailType, columnList);
				
				if (PropertiesUtil.getInstance().getMail_TargetDomainAddressLock() != null && PropertiesUtil.getInstance().getMail_TargetDomainAddressLock().equalsIgnoreCase("true")) {
					
					String targetDomain = PropertiesUtil.getInstance().getMail_TargetDomainAddress();
					
					if (to.indexOf(targetDomain) != -1) {
						sendMail(from, to, title, content);
					} else {
						System.out.println("TargetDomainAddress is Running! " + to + " : Mail Sending Fail! Only TargetDomain " + targetDomain);
					}
				} else {
					sendMail(from, to, title, content);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public String toMailContent(List<Map> userMapList , String target , List columnList) throws Exception {
			
			String content = "";
			
			if (userMapList == null || userMapList.size() == 0) {
				return null;
			}
			
			String paramString = JSONArray.fromObject(userMapList).toString();
			String returnColumnString = JSONArray.fromObject(columnList).toString();
			
			try {
				List paramList = new ArrayList();
				if (paramString != null)
					paramList.add(new Property("paramString", paramString));
				if (columnList != null)
					paramList.add(new Property("columnString", returnColumnString));
				
				Property[] params = null;
				if (!paramList.isEmpty()) {
					params = new Property[paramList.size()];
					paramList.toArray(params);
				}
				if (target.equalsIgnoreCase(HcTimeSheetWatcherJob.LEADER)) {
					content = ServletUtil.request(PropertiesUtil.getInstance().getServerUrl() + PropertiesUtil.getInstance().getMailContentsUrl_Leader(), params);
				} else if (target.equalsIgnoreCase(HcTimeSheetWatcherJob.PERSON)) {
					content = ServletUtil.request(PropertiesUtil.getInstance().getServerUrl() + PropertiesUtil.getInstance().getMailContentsUrl_Person(), params);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return content;
		}
	}	
	private static void sendMail(String from, String to , String subject, String messageText) throws Exception {
		
		String mailServerName = PropertiesUtil.getInstance().getMail_SenderMailServerSMTP();
		String id = PropertiesUtil.getInstance().getMail_SenderId();
		String pass = PropertiesUtil.getInstance().getMail_SenderPassword();
		
		//to = "kmyu@smartworks.net";
		
		sendMail(mailServerName, id, pass, from, to, subject, messageText);
		
	}
	private static void sendMail(String mailServerName, String id, String pass, String from, String to, String subject, String messageText) throws AddressException, MessagingException, UnsupportedEncodingException {

		if(mailServerName == null || id == null|| pass == null) {
			return;
		}
		
		Authenticator auth = new PassAuthenticator(id, pass);
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", mailServerName);
		mailProps.put("mail.smtp.auth", "true");
		if(mailServerName.equals("smtp.gmail.com")) {
			mailProps.put("mail.smtp.startls.enable", "true");
			mailProps.put("mail.transport.protocol", "smtp");
			mailProps.put("mail.smtp.port", "465");
			mailProps.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		} else if (mailServerName.equals("smtp.daum.net")) {
			mailProps.put("mail.smtp.port", "465");
			mailProps.put("mail.smtp.socketFactory.port", "465");
			mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			mailProps.put("mail.smtp.socketFactory.fallback", "false");
		} else if (mailServerName.equals("smtp.worksmobile.com")) {
			mailProps.put("mail.smtp.port", "465");
			mailProps.put("mail.smtp.socketFactory.port", "465");
			mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			mailProps.put("mail.smtp.socketFactory.fallback", "false");
		}
		Session mailSession = Session.getInstance(mailProps, auth);

		InternetAddress toAddrs = new InternetAddress(to);
		InternetAddress fromAddr = new InternetAddress(id, "KSS-ImageNext-HC TIME SHEET.");

		Message message = new MimeMessage(mailSession);
		message.setFrom(fromAddr);
		message.setRecipient(Message.RecipientType.TO, toAddrs);
		message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
		message.setContent(messageText.toString(), "text/html; charset=utf-8");
//		message.setContent(messageText.toString(), "text/html; charset=euc-kr");
		
		try {
			Transport.send(message);
			
			System.out.println("SendMail Completed to "+ to);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new MessagingException(ex.getMessage());
		}
	}
}
class PassAuthenticator extends Authenticator {
	private String id;
	private String pass;
	public PassAuthenticator(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.id, this.pass);
	}
}