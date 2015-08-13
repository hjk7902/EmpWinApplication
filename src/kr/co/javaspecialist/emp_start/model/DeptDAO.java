package kr.co.javaspecialist.emp_start.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeptDAO implements IDeptDAO {

	public DeptDAO() {
	}
	

	public ArrayList<DeptVO> getAllDepts() {
		ArrayList<DeptVO> listData = new ArrayList<DeptVO>();

		
		return listData;
	}
	
	public Connection getConnection() {
		String url = "jdbc:mariadb://127.0.0.1:3306/sample";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:ora11g";
		String id = "user";
		String pw = "user123";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pw);
		}catch(Exception e) {
			System.out.println("커넥션 연결 실패");
		}
		return con;
	}//end getConnection
	
	public void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(Exception e) {
				//nothing
			}
		}
	}//end closeConnection
}
