<%@page import="com.company.MyFileDTO"%>
<%@page import="com.company.MyFileDAO"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <title>FileUpload</title>
</head>
<body>

<h2>DB에 등록된 파일 목록 보기</h2>

<!-- 파일 등록을 위한 링크 -->
<a href="FileUploadMain.jsp">파일등록1</a>
<a href="MultiUploadMain.jsp">파일등록2</a>

<%
    // MyFileDAO 객체 생성하여 DB에서 파일 목록 가져오기
    MyFileDAO dao = new MyFileDAO();
    List<MyFileDTO> fileLists = dao.MyFileList();  // DB에서 모든 파일 정보를 리스트로 가져옴
    dao.close();  // DB 자원 해제
%>

<!-- 파일 목록을 표 형식으로 출력 -->
<table border="1">
    <tr>
        <th>No</th>
        <th>제목</th>
        <th>카테고리</th>
        <th>원본 파일명</th>
        <th>저장된 파일명</th>
        <th>작성일</th>
        <th></th>
    </tr>
    
    <!-- fileLists 리스트에 포함된 모든 파일 정보 출력 -->
    <% for(MyFileDTO f : fileLists){ %>
  
    <tr>
        <td><%= f.getIdx() %></td> <!-- 파일의 ID 출력 -->
        <td><%= f.getTitle() %></td> <!-- 파일 제목 출력 -->
        <td><%= f.getCate() %></td> <!-- 파일 카테고리 출력 -->
        <td><%= f.getOfile() %></td> <!-- 원본 파일명 출력 -->
        <td><%= f.getSfile() %></td> <!-- 저장된 파일명 출력 -->
        <td><%= f.getPostdata() %></td> <!-- 작성일 출력 -->
        
        <!-- 다운로드 링크, 원본 파일명(oName)과 저장된 파일명(sName)을 URL 인코딩하여 전달 -->
        <td>
            <a href="Download.jsp?oName=<%= URLEncoder.encode(f.getOfile(), "UTF-8")
             			%>&sName=<%= URLEncoder.encode(f.getSfile(),"UTF-8") %>">[다운로드]</a>
        </td>
    </tr>
    <%  } %>
</table>

</body>
</html>
