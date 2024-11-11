<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>JSP - for문과 if문 사용</title>
</head>
<body>
    <h2>사용자 목록 (나이가 30세 이상인 사용자만 출력)</h2>

    <%
        // 사용자 목록을 배열로 정의
        String[] names = {"홍길동", "김철수", "이영희", "박지민", "최민수"};
        int[] ages = {25, 30, 35, 28, 40};
        
        // for문과 if문을 사용하여 조건에 맞는 사용자만 출력
        for (int i = 0; i < names.length; i++) {
            if (ages[i] >= 30) {  // 나이가 30세 이상인 사용자만 출력
    %>
                <p><%= names[i] %>님은 <%= ages[i] %>세입니다.</p>
    <%
            }
        }
    %>

    <hr>
    <h3>전체 사용자 목록</h3>
    <%
        // 모든 사용자 출력 (조건 없이)
        for (int i = 0; i < names.length; i++) {
    %>
        <p><%= names[i] %>님은 <%= ages[i] %>세입니다.</p>
    <%
        }
    %>
</body>
</html>