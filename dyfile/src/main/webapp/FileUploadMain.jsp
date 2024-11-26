<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
// 폼 제출 시 유효성 검사 함수
function validateForm(form){
    // 제목이 비어있는지 확인
	if(form.title.value ==""){
		alert("제목을 입력하세요.");
		form.title.focus();  // 제목 입력 필드에 포커스를 맞춤
		return false;  // 폼 제출을 중단
	}
    // 첨부파일이 선택되지 않았는지 확인
	if(form.attachedFile.value ==""){
		alert("첨부 파일은 필수 입력입니다.");
		return false;  // 폼 제출을 중단
	}
    return true;  // 유효성 검사 통과 시 폼을 제출
}
</script>
<title>fileupload</title>
</head>
<body>
<h3>파일 업로드</h3>

<!-- 오류 메시지 출력, 서버에서 전달된 오류 메시지를 표시 -->
<span style="color:red">${errorMessage }</span>

<!-- 파일 업로드 폼 -->
<form name="fileForm" method="post" enctype="multipart/form-data"
 				action="UploadProcess.do" onsubmit="return validateForm(this);">
    제목: <input type="text" name="title"/><br/>
    
    <!-- 카테고리 선택 (체크박스) -->
    카테고리(선택사항):
    <input type="checkbox" name="cate" value="사진" checked/>사진
    <input type="checkbox" name="cate" value="과제"/>과제	
    <input type="checkbox" name="cate" value="워드"/>워드		
    <input type="checkbox" name="cate" value="음원"/>음원	<br/>
    
    첨부파일: <input type="file" name="ofile"/><br/>  <!-- 파일 선택 필드 -->
    
    <input type="submit" value="전송하기"/>  <!-- 폼 제출 버튼 -->
</form>

</body>
</html>
