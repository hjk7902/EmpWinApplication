package kr.co.javaspecialist.emp_start.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDAO implements IEmpDAO {
	
	public int getEmpCount() {
		int rowCount = 0;
		String sql = "select count(*) from emp";
		
		
		return rowCount;
	}
	
	public ArrayList<Integer> getAllEmpno() {
		ArrayList<Integer> listData = new ArrayList<Integer>();
		String sql = "select empno from emp";


		return listData;
	}
	
	public ArrayList<EmpVO> getAllEmps() {
		ArrayList<EmpVO> lists = new ArrayList<EmpVO>();
		String sql = "select * from emp";

		
		return lists;
	}
	
	public int insertEmp(EmpVO vo) {
		int count=0;
		String sql = "insert into emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)" +
				" values (?, ?, ?, ?, ?, ?, ?, ?)";

		
		return count;
	}
	
	public int updateEmp(EmpVO vo) {
		int count=0;
		String sql = "update emp set ename=?, job=? where empno=?";

		
		return count;
	}
	
	public int deleteEmp(int empno) {
		int deletedRow = 0;
		String sql = "delete from emp where empno=?";

		
		return deletedRow;
	}

	public ArrayList<String> getAllColumnNames() {
		ArrayList<String> columnNames = new ArrayList<String>();
		String sql = "select * from emp";
		Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for(int i=0; i<columnCount; i++) {
				columnNames.add(metaData.getColumnName(i+1).toUpperCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			closeConnection(con); 
		}
		
		return columnNames;
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
