<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>while Exam</title>
</head>
<body>
<h1>while Exam</h1>
<%
request.setCharacterEncoding("utf-8");
String msg = request.getParameter("msg");
int number = Integer.parseInt(request.getParameter("number"));
int count = 0;
while(number>count){

%>
<b><%=msg%></b><br/><p/>

<%
count++;
}

%>

</body>
</html>