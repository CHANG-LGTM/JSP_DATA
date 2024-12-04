package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {

	// 데이터베이스 연결을 위한 객체들 선언
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;

	// DBConnPool 생성자 - 데이터베이스 연결을 설정
	public DBConnPool() {
		try {
			// InitialContext를 통해 JNDI 환경을 설정
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");

			// DataSource 객체를 통해 DB 연결 정보를 가져옴
			DataSource ds = (DataSource)envContext.lookup("jdbc/Oracle");

			// DB 연결을 얻어옴
			con = ds.getConnection();
			System.out.println("con : " + con); // DB 연결 확인 메시지 출력
		}catch(Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
		}
	}

	// 자원 반납 메서드 - 사용한 JDBC 자원을 닫음
	public void close() {
		try {
			// ResultSet, Statement, PreparedStatement, Connection 순으로 닫음
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();

			// 자원 해제 메시지 출력
			System.out.println("JDBC 자원 해제");
		}catch(Exception e) {
			e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
		}
	}
}
