<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%

	String userId = (String)request.getParameter("userId");
	String fromDate = (String)request.getParameter("fromDate");
	String toDate = (String)request.getParameter("toDate");

%>

</head>
<body>
<h1>THIS IS LEADER MAIL</h1>
<div>USER ID : <%= userId%></div>
<div>FROM DATE : <%= fromDate%></div>
<div>TOD DATE : <%= toDate%></div>
</body>
</html>