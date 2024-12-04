package com.company;

public class BoardPage {

    // 페이지네이션 문자열 생성
    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String url) {
        String pagingStr = "";
        
        // 총 페이지 수
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        // 블록의 시작 페이지, 끝 페이지
        int startPage = ((pageNum - 1) / blockPage) * blockPage + 1;
        int endPage = startPage + blockPage - 1;
        
        if (endPage > totalPages) {
            endPage = totalPages;
        }
        
        // 이전 블록
        if (startPage > blockPage) {
            pagingStr += "<a href='" + url + "?pageNum=" + (startPage - 1) + "'>[이전]</a> ";
        }

        // 페이지 번호들
        for (int i = startPage; i <= endPage; i++) {
            if (i == pageNum) {
                pagingStr += "<b>" + i + "</b> ";
            } else {
                pagingStr += "<a href='" + url + "?pageNum=" + i + "'>" + i + "</a> ";
            }
        }

        // 다음 블록
        if (endPage < totalPages) {
            pagingStr += "<a href='" + url + "?pageNum=" + (endPage + 1) + "'>[다음]</a>";
        }

        return pagingStr;
    }
}
