package com.company;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyFileDAO extends DBConnPool {

    public int insertFile(MyFileDTO dto) {
        int applyResult = 0;
      

        try {
     
            String query = "INSERT INTO myfile (idx, title, cate, ofile, sfile) " +
                    "VALUES (seq_board_num.nextval, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getCate());
            pstmt.setString(3, dto.getOfile());
            pstmt.setString(4, dto.getSfile());

            applyResult = pstmt.executeUpdate();

       
        } catch (SQLException e) {
    
                System.out.println("INSERTS 중 에외 발생");
            e.printStackTrace();
         
        }
        return applyResult;
    }
    public List<MyFileDTO> MyFileList(){
    	List<MyFileDTO> fileList= new  Vector<MyFileDTO>();
    	
    	String query = "SELECT * FROM myfile order by idx DESC ";
    	try {
    		
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(query);
    		while(rs.next()) {
    			
    			MyFileDTO dto = new MyFileDTO();
    			dto.setIdx(rs.getString(1));
    			dto.setTitle(rs.getString(2));
    			dto.setCate(rs.getString(3));
    			dto.setOfile(rs.getString(4));
    			dto.setSfile(rs.getString(5));
    			dto.setPostdata(rs.getString(6));
    			
    			fileList.add(dto);
    			
    		}
    		System.out.println(fileList.size());
			
		} catch (Exception e) {
			System.out.println("SELECT 시 예외 발생");
			e.printStackTrace();
		}
    	return fileList;
    	
    }
}

