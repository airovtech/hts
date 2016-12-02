<%@page import="net.smartworks.util.MailUtil"%>
<%@page import="net.smartworks.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	User user1 = new User("kmyu@smartworks.net", "유광민" , "연구소" , "책임", "kmyu@smartworks.net.mail");
	User user2 = new User("kmyu@smartworks.net2", "유광민2" , "연구소2" , "책임2", "kmyu@smartworks.net.mail2");
	User user3 = new User("kmyu@smartworks.net3", "유광민3" , "연구소3" , "책임3", "kmyu@smartworks.net.mail3");

	User[] users = new User[3];
	users[0] = user1;
	users[1] = user2;
	users[2] = user3;
	
	MailUtil.sendMail(MailUtil.SENDTYPE_LEADER, users);
	
	
%>
</body>
</html>