<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
String msg;
%>
<%
String name = request.getParameter("name");
String color = request.getParameter("color");

if(color.equals("blue")){
	msg = "파란색";
}else if(color.equals("red")){
	msg= "붉은색";
}else if(color.equals("orange")){
	msg= "오랜지색";
	
	
}else{
	color = "white";
	msg = "기타색";
}
%>
<body bgcolor=<%=color %>>
<b><%=name %></b>님이 좋아하는 색깔은 <b><%=msg %></b>입니다.
</body>
</html>