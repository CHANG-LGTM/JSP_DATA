<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어 및 문자 인코딩 설정 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지 문자 인코딩 설정 -->
<title>Response Exam</title> <!-- 페이지 제목 -->
</head>
<body>

<% 
    // response.sendRedirect()를 사용하여 클라이언트를 다른 페이지로 리다이렉션
    response.sendRedirect("response1_1.jsp"); 
    // 사용자가 이 페이지를 요청하면 "response1_1.jsp"로 자동으로 리다이렉트됩니다.
%>

</body>
</html>

