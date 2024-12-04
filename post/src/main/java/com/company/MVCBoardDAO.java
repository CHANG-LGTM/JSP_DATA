package com.company;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {

    // 생성자 - 부모 클래스(DBConnPool)의 생성자를 호출하여 DB 연결
    public MVCBoardDAO() {
        super();
    }

    // 게시물의 총 개수를 조회하는 메서드
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard";

        // 검색어가 있을 경우, WHERE 조건을 추가
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1); // 총 게시물 개수 반환
        } catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }
        return totalCount;
    }

    // 페이징 처리가 적용된 게시물 목록을 조회하는 메서드
    public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();

        // SQL 쿼리 (ROWNUM을 사용하여 페이지네이션 처리)
        String query = " " +
        "SELECT * FROM (  " + 
        "  SELECT Tb.*, ROWNUM rNum FROM ( " + 
        "    SELECT * FROM mvcboard ";

        // 검색어가 있을 경우, WHERE 조건을 추가
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY idx DESC " + "    ) Tb " + " )  " + " WHERE rNum BETWEEN ? AND ?";

        try {
            // PreparedStatement를 사용하여 쿼리 실행
            psmt = con.prepareStatement(query);
            psmt.setString(1, map.get("start").toString()); // 시작 번호
            psmt.setString(2, map.get("end").toString());   // 끝 번호
            rs = psmt.executeQuery();

            // 결과를 DTO에 담아 List에 추가
            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();

                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));

                board.add(dto);
            }
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return board;
    }

    // 게시물 작성 메서드 (새 게시물 삽입)
    public int insertWrite(MVCBoardDTO dto) {
        int result = 0;
        try {
            // 게시물을 삽입하는 SQL 쿼리
            String query = "INSERT INTO mvcboard (" 
                        + "idx, name, title, content, ofile, sfile, pass)" 
                        + "VALUES ( "
                        + "seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";

            // PreparedStatement를 사용하여 쿼리 실행
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getPass());

            result = psmt.executeUpdate(); // 게시물 삽입 실행
        } catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 게시물 상세보기 메서드 (idx로 조회)
    public MVCBoardDTO selectView(String idx) {
        MVCBoardDTO dto = new MVCBoardDTO();
        String query = "SELECT * FROM mvcboard WHERE idx=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx); // idx를 이용하여 게시물 조회
            rs = psmt.executeQuery();

            if (rs.next()) {
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));
            }
        } catch (Exception e) {
            System.out.println("게시물 상세보기 중 예외 발생");
            e.printStackTrace();
        }
        return dto;
    }

    // 조회수 증가 메서드
    public void updateVisitCount(String idx) {
        String query = "UPDATE mvcboard SET "
                + "visitcount=visitcount+1 "
                + "WHERE idx=?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx); // idx에 해당하는 게시물의 조회수 증가
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 다운로드 카운트 증가 메서드
    public void downCountPlus(String idx) {
        String sql = "UPDATE mvcboard SET " +
                "downcount=downcount+1 " +
                "WHERE idx=?";
    
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, idx); // idx에 해당하는 게시물의 다운로드 수 증가
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("다운로드 카운트 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 비밀번호 확인 메서드
    public boolean confirmPassword(String pass, String idx) {
        boolean isCorr = true;
        try {
            String sql = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, pass);
            psmt.setString(2, idx);
            rs = psmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) { // 비밀번호가 일치하지 않으면 false 반환
                isCorr = false;
            }
        } catch (Exception e) {
            isCorr = false;
            e.printStackTrace();
        }
        return isCorr;
    }

    // 게시물 삭제 메서드
    public int deletePost(String idx) {
        int result = 0;
        try {
            String query = "DELETE FROM mvcboard WHERE idx=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx); // idx에 해당하는 게시물 삭제
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("게시물 삭제중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
    
    // 게시물 수정 메서드
    public int updatePost(MVCBoardDTO dto) {
        int result = 0;
        try {
            // 게시물 수정 SQL 쿼리 (비밀번호 확인 후 수정)
            String query = "UPDATE mvcboard"
                          + " SET title=?, name=?, content=?, ofile=?, sfile=?"
                          + " WHERE idx=? and pass=?";

            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getName());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getIdx());
            psmt.setString(7, dto.getPass());

            result = psmt.executeUpdate(); // 게시물 수정 실행

        } catch (Exception e) {
            System.out.println("게시물 수정 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
}
