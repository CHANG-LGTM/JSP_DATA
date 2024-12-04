<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 언어 및 인코딩 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 게시판</title> <!-- 페이지 제목 설정 -->
<style>a{text-decoration:none;}</style> <!-- 링크 스타일을 기본 상태로 설정 (밑줄 없음) -->
</head>
<body>
<h2>게시판 목록 게시판 - 목록 보기(List)</h2> <!-- 페이지 제목 표시 -->

<!-- 검색 폼 -->
<form method="get">
    <table border="1" width="90%">
        <tr>
            <td align="center">
                <!-- 검색 조건을 선택할 수 있는 드롭다운 메뉴 -->
                <select name="searchField">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="searchWord" /> <!-- 검색어 입력란 -->
                <input type="submit" value="검색하기" /> <!-- 검색 버튼 -->
            </td>
        </tr>
    </table>
</form>

<!-- 게시판 목록 테이블 -->
<table border="1" width="90%">
    <tr>
        <th width="10%">번호</th> <!-- 게시물 번호 -->
        <th width="*">제목</th> <!-- 게시물 제목 -->
        <th width="15%">작성자</th> <!-- 작성자 -->
        <th width="10%">조회수</th> <!-- 조회수 -->
        <th width="15%">작성일</th> <!-- 작성일 -->
        <th width="8%">첨부</th> <!-- 첨부 파일 여부 -->
    </tr>

    <!-- 게시물이 없을 경우 메시지 출력 -->
    <c:choose>
        <c:when test="${ empty boardLists }"> 
            <tr>
                <td colspan="6" align="center">
                    등록된 게시물이 없습니다^^* <!-- 게시물이 없을 때 표시 -->
                </td>
            </tr>
        </c:when>
        <!-- 게시물이 있을 경우 목록 출력 -->
        <c:otherwise>
            <c:forEach items="${ boardLists }" var="row" varStatus="loop">
                <tr align="center">
                    <!-- 게시물 번호, 제목, 작성자, 조회수, 작성일, 첨부 여부 -->
                    <td>
                        ${map.totalCount - ((map.pageNum - 1) * map.pageSize + loop.index)} <!-- 게시물 번호 계산 -->
                    </td>
                    <td align="left">
                        <a href="view.do?idx=${ row.idx }">${ row.title}</a> <!-- 게시물 제목 클릭 시 상세보기로 이동 -->
                    </td>
                    <td>${ row.name }</td> <!-- 작성자 -->
                    <td>${ row.visitcount }</td> <!-- 조회수 -->
                    <td>${ row.postdate }</td> <!-- 작성일 -->
                    <td>
                        <!-- 첨부 파일이 있을 경우 다운로드 링크 표시 -->
                        <c:if test="${ not empty row.ofile }">
                            <a href="download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a> <!-- 첨부 파일 다운로드 링크 -->
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>

<!-- 페이지 네비게이션과 글쓰기 버튼 -->
<table border="1" width="90%">
    <tr>
        <td align="center">
            ${map.pagingImg} <!-- 페이지 네비게이션 이미지 출력 -->
        </td>
        <td width="100">
            <a href="Write.jsp" class="button">글쓰기</a> <!-- 글쓰기 페이지로 이동하는 버튼 -->
        </td>
    </tr>
</table>
</body>
</html>
