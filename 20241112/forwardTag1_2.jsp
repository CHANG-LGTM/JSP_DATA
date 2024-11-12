<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- JSP 페이지의 문자 인코딩 및 언어 설정 -->

<%
    // 요청 파라미터 'id'와 'pwd'를 받아오는 부분
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지의 문자 인코딩 설정 -->
<title>Insert title here</title> <!-- 페이지 제목 -->
</head>
<body>
<h1>Forward Tag Exam</h1> <!-- 페이지 제목 또는 큰 제목 -->

<!-- 사용자가 입력한 'id'와 'pwd'를 화면에 출력 -->
당신의 아이디는 <b><%=id%></b>이고<p/> <!-- 'id' 값 출력 -->
패스워드는 <b><%=pwd%></b>입니다. <!-- 'pwd' 값 출력 -->

</body>
</html>
