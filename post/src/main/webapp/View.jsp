<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 인코딩 설정 -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%> <!-- JSTL 태그 라이브러리 사용 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title> <!-- 페이지 제목 설정 -->
</head>
<body>
	<h2>파일 첨부형 게시판 - 상세 보기(View)</h2> <!-- 게시물 상세 보기 제목 -->

	<!-- 게시물 정보 테이블 -->
	<table border="1" width="90%">
		<colgroup>
			<col width="15%" /> <!-- 첫 번째 열의 너비 설정 -->
			<col width="35%" /> <!-- 두 번째 열의 너비 설정 -->
			<col width="15%" /> <!-- 세 번째 열의 너비 설정 -->
			<col width="*" /> <!-- 나머지 열의 너비 설정 -->
		</colgroup>
		<tr>
			<td>번호</td> <!-- 게시물 번호 -->
			<td>${dto.idx}</td> <!-- 게시물 번호 값 출력 -->
			<td>작성자</td> <!-- 작성자 -->
			<td>${dto.name}</td> <!-- 작성자 이름 값 출력 -->
		</tr>
		<tr>
			<td>작성일</td> <!-- 작성일 -->
			<td>${dto.postdate}</td> <!-- 작성일 값 출력 -->
			<td>조회수</td> <!-- 조회수 -->
			<td>${dto.visitcount}</td> <!-- 조회수 값 출력 -->
		</tr>
		<tr>
			<td colspan="3">제목</td> <!-- 제목 -->
			<td>${dto.title}</td> <!-- 제목 값 출력 -->
		</tr>
		<tr>
			<td>내용</td> <!-- 내용 -->
			<td colspan="3" height="100">${dto.content} <!-- 내용 값 출력 -->
				<!-- 첨부 파일이 이미지일 경우 이미지로 출력 -->
				<c:if test="${not empty dto.ofile and isImage eq true}">
					<br>
					<img src="../Uploads/${dto.sfile}" style="max-width: 100%;" /> <!-- 이미지 표시 -->
				</c:if>
			</td>
		</tr>

		<tr>
			<td>첨부 파일</td> <!-- 첨부 파일 -->
			<td><c:if test="${not empty dto.ofile}">
        ${dto.ofile} <!-- 첨부된 파일 이름 출력 -->
        <!-- 다운로드 링크 -->
        <a href="download.do?ofile=${dto.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">[다운로드] </a>
				</c:if></td>
			<td>다운로드수</td> <!-- 다운로드 수 -->
			<td>${dto.downcount}</td> <!-- 다운로드 수 값 출력 -->
		</tr>
		<tr>
			<td colspan="4" align="center">
				<!-- 수정, 삭제, 목록 바로가기 버튼 -->
				<button type="button" onclick="location.href='pass.do?mode=edit&idx=${param.idx}';">수정하기</button>
				<button type="button" onclick="location.href='pass.do?mode=delete&idx=${param.idx}';">삭제하기</button>
				<button type="button" onclick="location.href='list.do';">목록 바로가기</button>
			</td>
		</tr>
	</table>
</body>
</html>
