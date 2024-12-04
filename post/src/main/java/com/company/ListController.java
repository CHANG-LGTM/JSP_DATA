package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list.do")  // "/list.do" URL에 대한 요청을 처리하는 서블릿
public class ListController extends HttpServlet { 

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException { 

        // DB 접근을 위한 DAO 객체 생성
        MVCBoardDAO dao = new MVCBoardDAO();

        // 검색 파라미터를 저장할 맵
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 검색 필드와 검색어를 파라미터로 받아옴
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        // 검색어가 있을 경우, 검색 조건을 맵에 추가
        if (searchWord != null) { 
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }

        // 총 게시물 수를 조회
        int totalCount = dao.selectCount(map); 

        // 페이지 당 게시물 수 및 블록 당 페이지 수 설정 (web.xml의 init-param을 사용)
        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

        // 현재 페이지 번호를 가져옴 (기본값: 1)
        int pageNum = 1; 
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals("")) {
            pageNum = Integer.parseInt(pageTemp);
        }

        // 현재 페이지에 해당하는 게시글 번호 범위 계산
        int start = (pageNum - 1) * pageSize + 1; 
        int end = pageNum * pageSize; 
        map.put("start", start);
        map.put("end", end);

        // 현재 페이지에 해당하는 게시글 목록을 조회
        List<MVCBoardDTO> boardLists = dao.selectListPage(map);

        // DB 연결 종료
        dao.close();

        // 페이징 처리를 위한 이미지 문자열 생성
        String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "list.do");
        map.put("pagingImg", pagingImg);  // 페이징 이미지 정보를 맵에 저장
        map.put("totalCount", totalCount);  // 총 게시물 수를 맵에 저장
        map.put("pageSize", pageSize);  // 페이지 당 게시물 수를 맵에 저장
        map.put("pageNum", pageNum);  // 현재 페이지 번호를 맵에 저장

        // 요청 속성에 게시글 목록과 페이징 정보를 설정
        req.setAttribute("boardLists", boardLists);
        req.setAttribute("map", map);

        // "List.jsp"로 포워드하여 게시글 목록과 페이징 정보를 전달
        req.getRequestDispatcher("List.jsp").forward(req, resp);
    }
}
