package emp.model;

import java.util.ArrayList;

public interface IEmpDAO {
	public int deleteEmp(int empno);
	public ArrayList<Integer> getAllEmpno();
	public ArrayList<String> getAllColumnNames();
	public ArrayList<EmpVO> getAllEmps();
	public int getEmpCount();
	public int insertEmp(EmpVO vo);
	public int updateEmp(EmpVO vo);
}
