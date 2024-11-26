<%@page import="utils.JSFunction"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 업로드된 파일이 저장된 경로와 파일 이름을 가져옵니다.
    String saveDirectory = "D:/Uploads";  // 파일이 저장된 디렉토리
    String saveFilename = request.getParameter("sName");  // 저장된 파일 이름 (서버에서 사용)
    String originalFilename = request.getParameter("oName");  // 원본 파일 이름 (클라이언트에서 사용)
    
    try {
        // 파일 객체 생성 (지정된 경로와 파일 이름으로)
        File file = new File(saveDirectory, saveFilename);  
        
        // 파일을 읽기 위한 InputStream 생성
        InputStream inStream = new FileInputStream(file);
        
        // 클라이언트의 User-Agent 정보(브라우저 종류 등) 확인
        String client = request.getHeader("User-Agent");
        
        // 만약 클라이언트가 "WOW64"가 포함되지 않으면 UTF-8 인코딩을 사용하여 파일 이름 설정
        if (client.indexOf("WOW64") == -1) {
            originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
        } else {  // 그렇지 않으면 KSC5601(한국어) 인코딩을 사용
            originalFilename = new String(originalFilename.getBytes("KSC5601"), "ISO-8859-1");
        }

        // 응답 초기화 및 헤더 설정
        response.reset();
        response.setContentType("application/octet-stream");  // 파일 다운로드를 위한 MIME 타입 설정
        response.setHeader("Content-Disposition", 
                           "attachment; filename=\"" + originalFilename + "\"");  // 파일 다운로드에 대한 헤더 설정
        response.setHeader("Content-Length", "" + file.length());  // 파일 크기 설정
        
        out.clear();  // 기존의 출력 버퍼를 비웁니다.
        
        // OutputStream을 사용하여 클라이언트로 파일 전송
        OutputStream outStream = response.getOutputStream();
        
        byte b[] = new byte[(int)file.length()];  // 파일 크기만큼 바이트 배열 생성
        int readBuffer = 0;  // 읽을 버퍼 크기
        
        // 파일을 읽으면서 클라이언트에게 전송
        while ((readBuffer = inStream.read(b)) > 0) {
            outStream.write(b, 0, readBuffer);  // 읽은 데이터를 클라이언트로 전송
        }

        // 스트림 닫기
        inStream.close();
        outStream.close();
    }
    catch (FileNotFoundException e) {  // 파일이 존재하지 않을 경우
        JSFunction.alertBack("파일을 찾을 수 없습니다.", out);  // 오류 메시지 출력
    }
    catch (Exception e) {  // 그 외의 예외 발생 시
        JSFunction.alertBack("예외가 발생하였습니다.", out);  // 오류 메시지 출력
    }
%>
