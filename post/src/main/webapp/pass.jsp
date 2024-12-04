<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 페이지 언어 및 인코딩 설정 -->
<taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" > <!-- JSTL 태그 라이브러리 선언 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title> <!-- 페이지 제목 설정 -->
<script type="text/javascript">
function validateForm(form) {
    // 비밀번호 입력 여부 확인
    if (form.pass.value == "") {
        alert("비밀번호를 입력하세요."); // 비밀번호가 입력되지 않으면 알림 표시
        form.pass.focus(); // 비밀번호 입력란에 포커스 이동
        return false; // 폼 제출을 막음
    }
    return true;  // 비밀번호가 입력되면 폼 제출을 계속 진행
}
</script>
</head>
<body>
<h2>파일 첨부형 게시판-비밀번호 검증(Pass)</h2> <!-- 페이지 제목 표시 -->

<!-- 비밀번호 확인을 위한 폼 -->
<form name="writeFrm" method="post" action="pass.do" onsubmit="return validateForm(this);">
    <!-- 숨겨진 입력 필드, idx와 mode 값을 전달 -->
    <input type="hidden" name="idx" value="${param.idx}" />
    <input type="hidden" name="mode" value="${param.mode}" />
    
    <!-- 비밀번호 입력 폼 -->
    <table>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="pass" style="width: 180px;" /></td> <!-- 비밀번호 입력란 -->
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit">검증하기</button> <!-- 비밀번호 검증 버튼 -->
                <button type="reset">RESET</button> <!-- 폼 초기화 버튼 -->
                <button type="button" onclick="location.href='list.do';">목록 바로가기</button> <!-- 목록 페이지로 이동하는 버튼 -->
            </td>
        </tr>
    </table>
</form>
</body>
</html>
