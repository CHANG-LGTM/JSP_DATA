<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어와 문자 인코딩 설정 -->

<%
    // HTTP 요청에서 'name' 파라미터 값을 가져옵니다.
    String name = request.getParameter("name");
%>

<!-- 페이지의 상단 내용 출력 -->
include ActionTag의 Top입니다.<p/>

<!-- 'name' 변수 값 출력, bold 태그로 강조 -->
<b><%=name%> Fighting!!</b>

<!-- 구분선 -->
<hr/>
