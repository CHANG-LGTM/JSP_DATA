<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL을 사용하기 위한 태그라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title> <!-- 페이지 제목 설정 -->
<script type="text/javascript">
function validateForm(form) {
    // 작성자, 제목, 내용 입력 여부를 확인하는 자바스크립트 함수
    if (form.name.value == "") {
        alert("작성자를 입력하세요."); // 작성자 입력하지 않으면 경고창
        form.name.focus();
        return false;
    }
    if (form.title.value == "") {
        alert("제목을 입력하세요."); // 제목 입력하지 않으면 경고창
        form.title.focus();
        return false;
    }
    if (form.content.value == "") {
        alert("내용을 입력하세요."); // 내용 입력하지 않으면 경고창
        form.content.focus();
        return false;
    }
}
</script>
</head>
<body>
<!-- form 태그로 게시물 수정 또는 새 글 작성 -->
<form id="writeForm" method="post" enctype="multipart/form-data" action="edit.do" onsubmit="return validateForm(this);">
  <!-- 기존 게시물 정보 전달을 위한 hidden input -->
  <input type="hidden" name="idx" value="${ dto.idx }"> <!-- 게시물 고유 ID -->
  <input type="hidden" name="prevOfile" value="${ dto.ofile }"> <!-- 기존 원본 파일명 -->
  <input type="hidden" name="prevSfile" value="${ dto.sfile }"> <!-- 기존 저장된 파일명 -->

  <table border="1" width="90%">
    <!-- 작성자 입력 -->
    <tr>
      <td>작성자</td>
      <td>
        <input type="text" name="name" style="width:150px;" value="${ dto.name }" /> <!-- 기존 작성자 정보 표시 -->
      </td>
    </tr>
    <!-- 제목 입력 -->
    <tr>
      <td>제목</td>
      <td>
        <input type="text" name="title" style="width:90%;" value="${ dto.title }" /> <!-- 기존 제목 정보 표시 -->
      </td>
    </tr>
    <!-- 내용 입력 -->
    <tr>
      <td>내용</td>
      <td>
        <textarea name="content" style="width:90%; height:100px;">${ dto.content }</textarea> <!-- 기존 내용 정보 표시 -->
      </td>
    </tr>
    <!-- 파일 첨부 -->
    <tr>
      <td>첨부 파일</td>
      <td>
        <input type="file" name="ofile" /> <!-- 새 파일 첨부 -->
      </td>
    </tr>
    <!-- 버튼들 -->
    <tr>
      <td colspan="2" align="center">
        <button type="submit">작성 완료</button> <!-- 작성 완료 버튼 -->
        <button type="reset">RESET</button> <!-- 입력 필드 초기화 버튼 -->
        <button type="button" onclick="location.href='list.do';">목록 바로가기</button> <!-- 목록 페이지로 이동 -->
      </td>
    </tr>
  </table>
</form>
</body>
</html>
