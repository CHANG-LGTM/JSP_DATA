<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어와 문자 인코딩 설정 -->

<%
    request.setCharacterEncoding("utf-8");  // 요청에서 전송된 데이터의 문자 인코딩을 UTF-8로 설정
    String name = "korea football";  // "name"이라는 변수에 문자열 값 할당
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지의 문자 인코딩 설정 -->
<title>Include Tag</title> <!-- 페이지 제목 -->
</head>
<body>
<h1>Include Tag Exam1</h1> <!-- 페이지 제목 -->

<!-- includeTagTop1.jsp 파일을 포함하는 태그 -->
<jsp:include page="includeTagTop1.jsp"/> <!-- 'includeTagTop1.jsp' 파일을 페이지에 포함 -->

<!-- 이 부분은 현재 페이지의 본문 내용 -->
include ActionTag의 Body입니다

</body>
</html>
