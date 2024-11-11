<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="errorpage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error page</title>
</head>
<body>
<%
int one =1;
int zero=0;
%>
<h1>error page</h1>
one과 zero의 사칙연산<p/>
one+zero=<%=one+zero%><p/>
one-zero=<%=one-zero%><p/>
one*zero=<%=one*zero%><p/>
one/zero=<%=one/zero%><p/>




</body>
</html>