package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view.do")  // 이 서블릿은 "/view.do" URL 요청을 처리합니다.
public class ViewController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // MVCBoardDAO 객체 생성하여 데이터베이스 연결
        MVCBoardDAO dao = new MVCBoardDAO();
        
        // 요청 파라미터 "idx" 값을 받아 게시물의 고유 ID를 얻음
        String idx = req.getParameter("idx");
        
        // 게시물 조회수 증가
        dao.updateVisitCount(idx); 
        
        // 해당 idx에 대한 게시물 정보 가져오기
        MVCBoardDTO dto = dao.selectView(idx);
        
        // DB 연결 종료
        dao.close();

        // 게시물 내용의 줄바꿈 문자를 <br/>로 변경하여 HTML에서 줄바꿈이 표시되게 함
        dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));

        // 파일 확장자를 추출하여 이미지 파일인지 확인
        String ext = null, fileName = dto.getSfile();
        if (fileName != null) {
            // 파일명에서 확장자 추출
            ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            System.out.println(ext);  // 확장자 출력 (디버깅용)
        }

        // 이미지 파일 확장자 목록 (png, jpg, gif)
        String[] mimeStr = {"png", "jpg", "gif"};
        List<String> mimeList = Arrays.asList(mimeStr);
        
        // 확장자가 이미지 목록에 포함되면 true로 설정
        boolean isImage = false;
        if (mimeList.contains(ext)) {
            isImage = true;  // 이미지 파일인 경우
        }

        // JSP 페이지로 게시물 DTO와 이미지 여부를 전달
        req.setAttribute("dto", dto);
        req.setAttribute("isImage", isImage);
        
        // View.jsp로 포워딩 (뷰 페이지로 데이터 전달)
        req.getRequestDispatcher("View.jsp").forward(req, resp);
    }
}
