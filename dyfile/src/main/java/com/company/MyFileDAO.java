package com.company;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool {

    // 파일 정보를 데이터베이스에 삽입하는 메소드
    public int insertFile(MyFileDTO dto) {
        int applyResult = 0;
        
        try {
            // 파일 정보를 삽입하기 위한 SQL 쿼리
            String query = "INSERT INTO myfile (idx, title, cate, ofile, sfile) " +
                    "VALUES (seq_board_num.nextval, ?, ?, ?, ?)";
            
            // PreparedStatement를 사용하여 쿼리 준비
            pstmt = con.prepareStatement(query);
            
            // PreparedStatement에 값 설정
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getCate());
            pstmt.setString(3, dto.getOfile());
            pstmt.setString(4, dto.getSfile());

            // 업데이트 실행 후 영향을 받은 행 수 반환
            applyResult = pstmt.executeUpdate();

        } catch (SQLException e) {
            // 삽입 중 예외가 발생하면 예외 메시지 출력
            System.out.println("INSERT 중 예외 발생");
            e.printStackTrace();
        }
        
        // 삽입 결과(영향을 받은 행 수) 반환
        return applyResult;
    }

    // 데이터베이스에서 파일 목록을 가져오는 메소드
    public List<MyFileDTO> MyFileList() {
        List<MyFileDTO> fileList = new Vector<MyFileDTO>();
        
        // 파일 목록을 가져오기 위한 SQL 쿼리 (idx 내림차순 정렬)
        String query = "SELECT * FROM myfile order by idx DESC ";
        
        try {
            // Statement를 생성하여 쿼리 실행
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            // 결과 집합을 순차적으로 처리
            while (rs.next()) {
                // MyFileDTO 객체를 생성하고, 각 열 값을 DTO에 설정
                MyFileDTO dto = new MyFileDTO();
                dto.setIdx(rs.getString(1));
                dto.setTitle(rs.getString(2));
                dto.setCate(rs.getString(3));
                dto.setOfile(rs.getString(4));
                dto.setSfile(rs.getString(5));
                dto.setPostdata(rs.getString(6));
                
                // DTO 객체를 리스트에 추가
                fileList.add(dto);
            }
            
            // 리스트의 크기를 출력 (디버깅용)
            System.out.println(fileList.size());
            
        } catch (Exception e) {
            // 조회 중 예외가 발생하면 예외 메시지 출력
            System.out.println("SELECT 시 예외 발생");
            e.printStackTrace();
        }
        
        // 파일 목록을 반환
        return fileList;
    }
}
