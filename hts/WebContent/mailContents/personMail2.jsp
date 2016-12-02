<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<head>

<style type="text/css">
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

</style>


</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%

	String paramString = (String)request.getParameter("paramString");
	String returnColumn = (String)request.getParameter("returnColumn");
%>
<body>
	<h1>HC TIME SHEET</h1>
	<p> * 금주 <span class='warn'>미등록된</span> Hc Time Sheet 가 조회 되어 발송된 메일입니다.</p>
	<p> 아래 표를 확인하시어 해당 날짜의 Hc Time Sheet 를 '스마트웍스닷넷(HC Time Sheet 등록 업무)'에 등록 바랍니다.</p>
	<p> 아래 표는 해당 부서 팀장에게도 발송 되었습니다.</p>
	<p> O : 작성 , X : 미작성</p>
	
	<table class="timeSheet">
		<tr>
			<td colspan='8' class='caption'>Hc TIME SHEET - USER</td>
		</tr>
		<tr>
			<th>사번</th>
			<th>성명</th>
			<th>Name</th>
			<th>부서</th>
			<th>구분</th>
			<th>직급</th>
			<th>2015/11/25</th>
			<th>2015/11/26</th>
		</tr>
		<tr>
			<td>kmyu</td>
			<td>유광민</td>
			<td>yukwangmin</td>
			<td>RnD</td>
			<td>OEM</td>
			<td>RE</td>
			<td align="center">O</td>
			<td align="center" class='warn'>X</td>
		</tr>
	</table>
</body>
</html>