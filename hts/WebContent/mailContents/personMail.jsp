<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.List"%>
<%@page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@page import="org.codehaus.jackson.type.TypeReference"%>
<%@page import="java.util.Map"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- <style type="text/css">
	body {
		font-size:0.8em;
	}
	.timeSheet {
    	border-collapse: collapse;
    	width:100%;
	}
	
	table ,td , th {
    	border: 1px solid #B8B8B8;
	}
	tr {
		height:25px;
	}
	.caption {
		background-color:#E4E4E4;
	}
	th {
		font-weight:normal;
	}
	.warn {
		color:red;
	}

</style> -->

</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%

	ObjectMapper om = new ObjectMapper();

	String paramString = new String(request.getParameter("paramString").getBytes("8859_1"),"utf-8");

	String returnColumnString = new String(request.getParameter("columnString").getBytes("8859_1"),"utf-8");
	
	try {

		List<Map<String, Object>> paramMapList = om.readValue(paramString, new TypeReference<List<Map<String, Object>>>(){});	
		
		if (paramMapList == null) {
			return;
		}
		
		List<Map<String, Object>> columnMapList = om.readValue(returnColumnString, new TypeReference<List<Map<String, Object>>>(){});	

		if (columnMapList == null) {
			return;
		}
		int totalColumnSize = columnMapList.size() + 6;	
		
	
%>
<body style="font-size:0.8em;">
	<h1>Time Sheet 미입력 레포트</h1>
	<p style="margin-top:30px;"> * 금주 <span class="warn" style="color:red;">미등록된</span> Hc Time Sheet 가 조회 되어, 자동 발송된 알림 메일입니다.</p>
	<p style="margin-top:20px;"> 아래 표를 확인하시어 해당 날짜의 Hc Time Sheet 를 '스마트웍스닷넷(HC Time Sheet 등록 업무)'에 등록 바랍니다.</p>
	<p style="margin-top:30px;"> O : 작성 , <span style="color:red;">X</span> : 미작성</p>
 
	<table class="timeSheet" style="border:1px solid #B8B8B8;border-collapse:collapse;width:95%;">
		<tr style="height:25px;">
			<td colspan="<%=totalColumnSize %>" class="caption" style="border:1px solid #B8B8B8;background-color:#E4E4E4;">Hc TIME SHEET - USER</td>
		</tr>
		<tr style="height:25px;">
			<th style="border:1px solid #B8B8B8;font-weight:normal;">사번</th>
			<th style="border:1px solid #B8B8B8;font-weight:normal;">이름</th>
			<th style="border:1px solid #B8B8B8;font-weight:normal;">Name</th>
			<th style="border:1px solid #B8B8B8;font-weight:normal;">부서</th>
			<th style="border:1px solid #B8B8B8;font-weight:normal;">구분</th>
			<th style="border:1px solid #B8B8B8;font-weight:normal;">직급</th>
<%
	for (int i = 0; i < columnMapList.size(); i++) {
		Map colMap = (Map)columnMapList.get(i);
		String date = (String)colMap.get("date");
%>
			<th style="border:1px solid #B8B8B8;font-weight:normal;"><%= date %></th>
<%
	}
%>
		</tr>
<%
	for (int i = 0; i < paramMapList.size(); i++) {
		
		Map userMap = (Map)paramMapList.get(i);
		
		String userNo = (String)userMap.get("userNo");
		String name = (String)userMap.get("name");
		String engName = (String)userMap.get("engName");
		String dept = (String)userMap.get("dept");
		String type = (String)userMap.get("type");
		String position = (String)userMap.get("position");
		
%>
		<tr style="height:25px;">
			<td align="center" style="border: 1px solid #B8B8B8;"><%= userNo %></td>
			<td align="center" style="border: 1px solid #B8B8B8;"><%= name %></td>
			<td align="center" style="border: 1px solid #B8B8B8;"><%= engName %></td>
			<td align="center" style="border: 1px solid #B8B8B8;"><%= dept %></td>
			<td align="center" style="border: 1px solid #B8B8B8;"><%= type %></td>
			<td align="center" style="border: 1px solid #B8B8B8;"><%= position %></td>
<%
		for (int j = 0 ; j < columnMapList.size(); j++) {
			Map colMap = (Map)columnMapList.get(j);
			String date = (String)colMap.get("date");
			String value = (String)userMap.get(date);
			if (value != null && value.equalsIgnoreCase("X")) {
%>			
				<td align="center" style="border: 1px solid #B8B8B8;color:red;"><%= value %></td>
<%
			} else {
%>
				<td align="center" style="border: 1px solid #B8B8B8;"><%= value %></td>
<%				
			}
		}
%>	
		</tr>
<%
	}

	} catch (Exception e) {
		System.out.println(paramString);
		e.printStackTrace();
	}
%>
</table>
</body>
</html>