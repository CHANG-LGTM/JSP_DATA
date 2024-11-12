<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어 및 문자 인코딩 설정 -->

<%
    // 캐시를 비활성화하는 헤더를 설정
    response.setHeader("Pragma", "no-cache");  // HTTP/1.0 캐시 방지 헤더 설정
    if(request.getProtocol().equals("HTTP/1.1")){  // HTTP/1.1일 경우
        response.setHeader("Cache-Control", "no-store");  // 캐시 저장 방지 헤더 설정
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지 문자 인코딩 설정 -->
<title>Response Exam</title> <!-- 페이지 제목 -->
</head>
<body>
<!-- 페이지 내용 -->
http://localhost/myapp/ch07/response1.jsp가<p/>
http://localhost/myapp/ch07/response1_1.jsp로 변경이 되었습니다.
</body>
</html>
