package emp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDAO implements IEmpDAO {
	
	public int getEmpCount() {
		int rowCount = 0;
		String sql = "select count(*) from emp";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return rowCount;
	}
	
	public ArrayList<Integer> getAllEmpno() {
		ArrayList<Integer> empnoList = new ArrayList<Integer>();
		String sql = "select empno from emp";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				empnoList.add(rs.getInt("empno"));
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return empnoList;
	}
	
	public ArrayList<EmpVO> getAllEmps() {
		ArrayList<EmpVO> empList = new ArrayList<EmpVO>();
		String sql = "select * from emp";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		
		return empList;
	}
	
	public int insertEmp(EmpVO emp) {
		int count=0;
		String sql = "insert into emp "
				+ "(empno, ename, job, mgr, hiredate, sal, comm, deptno) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmpno());
			stmt.setString(2, emp.getEname());
			stmt.setString(3, emp.getJob());
			stmt.setInt(4, emp.getMgr());
			stmt.setDate(5, emp.getHiredate());
			stmt.setDouble(6, emp.getSal());
			stmt.setDouble(7, emp.getComm());
			stmt.setInt(8, emp.getDeptno());
			count = stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return count;
	}
	
	public int updateEmp(EmpVO emp) {
		int count=0;
		String sql = "update emp set ename=?, job=? where empno=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, emp.getEname());
			stmt.setString(2, emp.getJob());
			stmt.setInt(3, emp.getEmpno());
			count = stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return count;
	}
	
	public int deleteEmp(int empno) {
		int deletedRow = 0;
		String sql = "delete from emp where empno=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, empno);
			deletedRow = stmt.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return deletedRow;
	}

	public ArrayList<String> getAllColumnNames() {
		ArrayList<String> columnNames = new ArrayList<String>();
		String sql = "select * from emp";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for(int i=0; i<columnCount; i++) {
				columnNames.add(metaData.getColumnName(i+1).toUpperCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {} 
		}
		return columnNames;
	}

}
