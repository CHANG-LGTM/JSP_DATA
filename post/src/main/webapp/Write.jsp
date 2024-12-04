<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!-- 페이지 인코딩 설정 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <!-- JSTL 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> <!-- 문자 인코딩 설정 -->

<!-- 폼 유효성 검사를 위한 자바스크립트 -->
<script type="text/javascript">
function validateForm(form) {
    if (form.name.value == "") {  // 작성자 입력 확인
        alert("작성자를 입력하세요.");
        form.name.focus();  // 작성자 입력란에 포커스
        return false;  // 폼 제출 중지
    }
    if (form.title.value == "") {  // 제목 입력 확인
        alert("제목을 입력하세요.");
        form.title.focus();  // 제목 입력란에 포커스
        return false;  // 폼 제출 중지
    }
    if (form.content.value == "") {  // 내용 입력 확인
        alert("내용을 입력하세요.");
        form.content.focus();  // 내용 입력란에 포커스
        return false;  // 폼 제출 중지
    }
    if (form.pass.value == "") {  // 비밀번호 입력 확인
        alert("비밀번호를 입력하세요.");
        form.pass.focus();  // 비밀번호 입력란에 포커스
        return false;  // 폼 제출 중지
    }
}
</script>
</head>
<body>
<h2>파일 첨부형 게시판 글쓰기(Write)</h2> <!-- 게시판 글쓰기 제목 -->

<!-- 글쓰기 폼 -->
<form name="writeFrm" method="post" enctype="multipart/form-data" action="write.do" onsubmit="return validateForm(this);">
<table border="1" width="90%">
  
  <!-- 작성자 입력란 -->
  <tr>
    <td>작성자</td>
    <td>
    <input type="text" name="name" style="width:150px;"/> <!-- 작성자 입력 필드 -->
  </tr>

  <!-- 제목 입력란 -->
  <tr>
    <td>제목</td>
    <td>
    <input type="text" name="title" style="width:90%;"/> <!-- 제목 입력 필드 -->
  </tr>

  <!-- 내용 입력란 -->
  <tr>
    <td>내용</td>
    <td>
    <textarea name="content" style="width:90%; height:100px;"></textarea> <!-- 내용 입력 필드 -->
    </td>
  </tr>

  <!-- 첨부 파일 입력란 -->
  <tr>
    <td>첨부 파일</td>
    <td>
    <input type="file" name="ofile"/> <!-- 파일 첨부 필드 -->
    </td>
  </tr>

  <!-- 비밀번호 입력란 -->
  <tr>
    <td>비밀번호</td>
    <td>
    <input type="password" name="pass" style="width:100px;"/> <!-- 비밀번호 입력 필드 -->
    </td>
  </tr>

  <!-- 버튼들 -->
  <tr>
    <td colspan="2" align="center">
        <button type="submit">작성 완료</button> <!-- 글쓰기 완료 버튼 -->
        <button type="reset">RESET</button> <!-- 폼 초기화 버튼 -->
        <button type="button" onclick="location.href='list.do';">목록 바로가기</button> <!-- 목록 바로가기 버튼 -->
    </td>
  </tr>
</table>
</form>

</body>
</html>
