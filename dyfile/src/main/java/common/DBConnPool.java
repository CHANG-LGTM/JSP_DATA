package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
    
    // 데이터베이스 연결 관련 객체
    public Connection con;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;
    
    // DBConnPool 생성자: 데이터베이스 연결 초기화
    public DBConnPool() {
        
        try {
            // JNDI를 이용하여 DataSource를 가져옴
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/Oracle");
            
            // DataSource로부터 Connection을 가져옴
            con = ds.getConnection();
            
            // 연결된 Connection 객체 출력 (디버깅용)
            System.out.println("con : " + con);
        } catch(Exception e) {
            // 예외 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }
    
    // 자원 반납 메소드: 사용 후 DB 자원 해제
    public void close() {
        try {
            // ResultSet, Statement, PreparedStatement, Connection을 닫음
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
            
            // 자원 해제 메시지 출력
            System.out.println("JDBC 자원 해제");
        } catch(Exception e) {
            // 예외 발생 시 스택 트레이스 출력
            e.printStackTrace();
        }
    }
}
