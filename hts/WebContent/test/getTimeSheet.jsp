<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.smartworks.model.HcTimeSheetCond"%>
<%@page import="net.smartworks.factory.ManagerFactory"%>
<%@page import="net.smartworks.model.HcTimeSheet"%>
<%@page import="java.util.List"%>
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

	HcTimeSheetCond cond = new HcTimeSheetCond();

	String startDateStr = "2016-11-14";
	String endDateStr = "2016-11-16";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	Date startDate = sdf.parse(startDateStr);
	Date endDate = sdf.parse(endDateStr);
	
	cond.setWorkDateFrom(startDate);
	cond.setWorkDateTo(endDate);

	String result = ManagerFactory.getInstance().getManager().getHcTimeSheetJsonDatas("", cond);
	out.println(result);
%>
</body>
</html>