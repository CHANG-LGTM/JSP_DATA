package com.company;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/edit.do")  // "/edit.do" URL에 대한 요청을 처리하는 서블릿
@MultipartConfig(  // 파일 업로드 설정: 최대 파일 크기 1MB, 최대 요청 크기 10MB
    maxFileSize = 1024 * 1024 * 1,
    maxRequestSize = 1024 * 1024 * 10
)
public class EditController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 수정할 게시글의 idx 값을 받아옴
        String idx = req.getParameter("idx");

        // DB에서 게시글 정보를 조회
        MVCBoardDAO dao = new MVCBoardDAO();
        MVCBoardDTO dto = dao.selectView(idx);

        // 조회한 게시글 정보를 JSP로 전달
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("Edit.jsp").forward(req, resp);  // Edit.jsp로 포워드
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 파일 업로드 디렉토리 설정
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");

        // 업로드된 파일의 원본 파일 이름
        String originalFileName = "";
        try {
            // 파일 업로드 처리
            originalFileName = FileUtil.uploadFile(req, saveDirectory);
        } catch (Exception e) {
            // 파일 업로드 오류 시 메시지 출력
            JSFunction.alertBack(resp, "파일 업로드 오류입니다.");
            return;
        }

        // 수정할 게시글의 기존 정보와 새 정보를 받아옴
        String idx = req.getParameter("idx");
        String prevofile = req.getParameter("prevofile");  // 기존 파일의 원본 파일명
        String prevSfile = req.getParameter("prevSfile");  // 기존 파일의 저장된 파일명
        String name = req.getParameter("name");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        // 세션에서 비밀번호 정보 가져오기
        HttpSession session = req.getSession();
        String pass = (String) session.getAttribute("pass");

        // DTO 객체에 수정할 내용 설정
        MVCBoardDTO dto = new MVCBoardDTO();
        dto.setIdx(idx);
        dto.setName(name);
        dto.setTitle(title);
        dto.setContent(content);
        dto.setPass(pass);

        // 새로운 파일이 업로드되었을 경우
        if (originalFileName != "") {
            // 파일 이름을 변경하여 저장
            String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
            dto.setOfile(originalFileName);  // 원본 파일명 저장
            dto.setSfile(savedFileName);   // 저장된 파일명 저장
            // 기존 파일 삭제
            FileUtil.deleteFile(req, "/Uploads", prevSfile);
        } else {
            // 파일이 없으면 기존 파일명 유지
            dto.setOfile(prevofile);
            dto.setSfile(prevSfile);
        }

        // DB에 게시글 업데이트
        MVCBoardDAO dao = new MVCBoardDAO();
        int result = dao.updatePost(dto);  // 게시글 수정
        dao.close();

        // 수정 결과 처리
        if (result == 1) {  // 수정 성공
            session.removeAttribute("pass");  // 비밀번호 세션 정보 삭제
            resp.sendRedirect("view.do?idx=" + idx);  // 수정된 게시글 조회 페이지로 리다이렉트
        } else {  // 수정 실패
            // 비밀번호 검증 실패 메시지 출력
            JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.", "view.do?idx=" + idx);
        }
    }
}
