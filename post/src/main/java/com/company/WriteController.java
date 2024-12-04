package com.company;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

// 파일 업로드 설정 (최대 파일 크기 1MB, 전체 요청 크기 10MB)
@MultipartConfig(maxFileSize = 1024 * 1024 * 1, // 업로드 파일의 최대 크기 (1MB)
		maxRequestSize = 1024 * 1024 * 10 // 전체 요청 크기 (10MB)
)
@WebServlet("/write.do") // /write.do 요청을 처리하는 서블릿
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// GET 요청 처리 - 글쓰기 페이지로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("Write.jsp").forward(req, resp); // Write.jsp로 포워딩
	}

	// POST 요청 처리 - 글쓰기 폼 제출 후 데이터 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 업로드 파일을 저장할 디렉토리 경로
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");

		// 업로드된 파일의 원본 파일명
		String originalFileName = "";
		try {
			// 파일 업로드 처리
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		} catch (Exception e) {
			// 파일 업로드 실패 시 오류 메시지 출력 후 이전 페이지로 돌아감
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "write.do");
			return;
		}

		// DTO 객체에 폼 데이터 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(req.getParameter("name")); // 글쓴이
		dto.setTitle(req.getParameter("title")); // 제목
		dto.setContent(req.getParameter("content")); // 내용
		dto.setPass(req.getParameter("pass")); // 비밀번호

		// 파일이 업로드되었으면 파일명 설정
		if (originalFileName != "") {
			// 파일명 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			dto.setOfile(originalFileName); // 원본 파일명
			dto.setSfile(savedFileName); // 저장된 파일명
		}

		// DB에 글쓰기 내용 삽입
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto); // 글 등록

		// DB 연결 종료
		dao.close();

		// 글쓰기 성공 시 게시글 목록으로 리다이렉트
		if (result == 1) {
			resp.sendRedirect("list.do");
		} else {
			// 글쓰기 실패 시 오류 메시지 출력 후 글쓰기 페이지로 돌아감
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "write.do");
		}
	}
}
