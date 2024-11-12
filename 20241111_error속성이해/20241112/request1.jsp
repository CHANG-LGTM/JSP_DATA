<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP 페이지의 언어 및 문자 인코딩 설정 -->

<%
    // 폼에서 전달된 파라미터 값을 가져옵니다.
    String name = request.getParameter("name");          // 사용자 이름
    String studentNum = request.getParameter("studentNum"); // 학번
    String gender = request.getParameter("gender");      // 성별
    String major = request.getParameter("major");        // 전공
    
    // 성별을 'man' 또는 'woman' 값에 따라 '남자' 또는 '여자'로 변경
    if(gender.equals("man")){
        gender = "남자";  // 'man'이면 성별을 '남자'로 변경
    }else{
        gender = "여자";  // 'woman'이면 성별을 '여자'로 변경
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 페이지 문자 인코딩 설정 -->
<title>Insert title here</title> <!-- 페이지 제목 -->
</head>
<body>
<h1>Request Exam</h1> <!-- 페이지 제목 -->

<!-- 사용자로부터 입력받은 정보 출력 -->
성명: <%=name%><p/>   <!-- 사용자가 입력한 이름 출력 -->
학번: <%=studentNum%><p/> <!-- 사용자가 입력한 학번 출력 -->
성별: <%=gender%><p/>  <!-- 성별을 '남자' 또는 '여자'로 출력 -->
학과: <%=major%><p/>   <!-- 사용자가 선택한 전공 출력 -->

</body>
</html>
