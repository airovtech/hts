<%@page import="java.util.List"%>
<%@page import="net.smartworks.model.UserCond"%>
<%@page import="net.smartworks.factory.ManagerFactory"%>
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

/* 	User user = ManagerFactory.getInstance().getManager().getUser("", "choib@keysafetyinc.com");

	out.print(user.getName()); */
	
	
	/* UserCond cond = new UserCond();
	cond.setType("PM");
	
	List<User> users = ManagerFactory.getInstance().getManager().getUsers("", cond);

	for (User user : users) {
		out.println(user.getName()+"<br>");
	} */
	
	List<String> result = ManagerFactory.getInstance().getManager().getAllDeptNameList("");
	for (String name : result) {
		out.println(name+"<br>");
	}

	List<String> result1 = ManagerFactory.getInstance().getManager().getAllUserTypeList("");
	for (String name : result1) {
		out.println(name+"<br>");
	}
	
	List<String> result2 = ManagerFactory.getInstance().getManager().getAllProjectNameList("");
	for (String name : result2) {
		out.println(name+"<br>");
	}
%>
</body>
</html>