<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%
  Date date = new Date();

%>
<hr/>
include 지시자의 Bottom 부분 입니다.<p/>
<%=date.toLocaleString()%>
</body>
</html>