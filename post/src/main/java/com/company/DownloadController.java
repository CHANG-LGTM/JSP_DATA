package com.company;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download.do")  // "/download.do" URL에 대한 요청을 처리하는 서블릿
public class DownloadController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 요청 파라미터에서 파일 관련 정보 추출
        String ofile = req.getParameter("ofile");  // 원본 파일 이름
        String sfile = req.getParameter("sfile");  // 서버에 저장된 파일 이름
        String idx = req.getParameter("idx");     // 파일에 대한 인덱스 값

        // 파일 다운로드 처리 (FileUtil 클래스에서 다운로드 처리 메소드 호출)
        FileUtil.download(req, resp, "/Uploads", sfile, ofile);

        // 다운로드 후 해당 파일의 다운로드 카운트를 증가시킴
        MVCBoardDAO dao = new MVCBoardDAO();  // 데이터베이스 연결 객체 생성
        dao.downCountPlus(idx);  // 다운로드 카운트 증가
        dao.close();  // 데이터베이스 연결 종료
    }
}
