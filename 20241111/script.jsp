
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 스크립트 Exam</title>
</head>
<body>
<h1> Script Exam</h1>
<%! String declaration = "Declaration" ;
%>
<%!
    public String decMethod(){
    return declaration;
    }
    %>
    
    <%
    String scriptlet = "scriptlet";
    String comment = "Comment";
    out.println("내장 객체를 이용한 출력:"+declaration+"<p>");
    %>
    선언문의 출력1:<%=declaration %><p>
    선언문의 출력2:<%=decMethod() %><p>
    스크립트릿의 출력: <%=scriptlet %><p>
    <!--  JSP주석 부분 -->
</body>
</html>