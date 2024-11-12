<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!-- JSP 페이지의 언어와 문자 인코딩 설정 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 문자 인코딩 설정 -->
<title>Forward Tag Exam</title> <!-- 페이지 제목 -->
</head>
<body>
<h1>Forward Tag Exam</h1> <!-- 페이지의 큰 제목 -->

<!-- 현재 페이지에서 forwardTag1_2.jsp 페이지로 포워딩 -->
forward Tag 포워딩 되기 전의 페이지입니다.
<jsp:forward page="forwardTag1_2.jsp"/> <!-- forwardTag1_2.jsp로 요청을 전달 -->

</body>
</html>
