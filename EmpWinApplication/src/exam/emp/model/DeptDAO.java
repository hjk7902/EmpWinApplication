package exam.emp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeptDAO implements IDeptDAO {

	public ArrayList<DeptVO> getAllDepts() {
		ArrayList<DeptVO> deptList = new ArrayList<DeptVO>();
		String sql = "SELECT deptno, dname, loc FROM dept";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = EmpDataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				deptList.add(dept);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(Exception e) {}
			if(stmt!=null) try {stmt.close();} catch(Exception e) {}
			if(con!=null) try {con.close();} catch(Exception e) {}
		}
		return deptList;
	}
	
}
