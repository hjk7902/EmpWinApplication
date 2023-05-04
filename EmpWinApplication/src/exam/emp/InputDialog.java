package exam.emp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import emp.model.DeptDAO;
import emp.model.DeptVO;
import emp.model.EmpDAO;
import emp.model.EmpVO;

public class InputDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton jButton = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JPanel jPanel2 = null;
	private JTextField empTextField = null;
	private JTextField enameTextField = null;
	private JTextField jobTextField = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JComboBox<Integer> mgrComboBox = null;
	private JTextField hiredateTextField = null;
	private JTextField salTextField = null;
	private JTextField commTextField = null;
	private JComboBox<DeptVO> deptnoComboBox = null;
	
	private int deptno;
	private int mgr;

	JFrame jFrame;
	EmpDAO empDao = new EmpDAO();
	DeptDAO deptDao = new DeptDAO();

	public InputDialog(Frame owner) {
		super(owner);
		jFrame = (JFrame)owner;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 276);
		this.setModal(true);
		this.setTitle("Insert Form");
		this.setContentPane(getJContentPane());
		setMgrList();
		setDeptNoList();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.SOUTH);
			jContentPane.add(getJPanel1(), BorderLayout.WEST);
			jContentPane.add(getJPanel2(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new FlowLayout());
			jPanel.add(getJButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("저장");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int empno = Integer.parseInt(getEmpTextField().getText());
						String ename = getEnameTextField().getText();
						String job = getJobTextField().getText();
						String hiredate = getHiredateTextField().getText();
						double sal = Double.parseDouble(getSalTextField().getText());
						String commStr = getCommTextField().getText().trim();
						double comm = 0;
						if(!commStr.equals("")) {
							comm = Double.parseDouble(commStr);
						}
						EmpVO vo = new EmpVO(empno, ename, job, mgr, java.sql.Date.valueOf(hiredate), sal, comm, deptno);
						EmpDAO db = new EmpDAO();

						int insertRowCount = db.insertEmp(vo);

						if(insertRowCount > 0) {
							clearField();
							dispose();
						}
					} catch(RuntimeException re) {
						JOptionPane.showMessageDialog(InputDialog.this, re.getMessage());
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("DEPTNO");
			jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel6 = new JLabel();
			jLabel6.setText("COMM");
			jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel5 = new JLabel();
			jLabel5.setText("SAL");
			jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4 = new JLabel();
			jLabel4.setText("HIREDATE");
			jLabel3 = new JLabel();
			jLabel3.setText("MGR");
			jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2 = new JLabel();
			jLabel2.setText("JOB");
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1 = new JLabel();
			jLabel1.setText("ENAME");
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(8);
			gridLayout1.setColumns(1);
			jLabel = new JLabel();
			jLabel.setText("EMPNO");
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setToolTipText("");
			jPanel1 = new JPanel();
			jPanel1.setLayout(gridLayout1);
			jPanel1.add(jLabel, null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(jLabel6, null);
			jPanel1.add(jLabel7, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(8);
			gridLayout.setColumns(1);
			jPanel2 = new JPanel();
			jPanel2.setLayout(gridLayout);
			jPanel2.add(getEmpTextField(), null);
			jPanel2.add(getEnameTextField(), null);
			jPanel2.add(getJobTextField(), null);
			jPanel2.add(getMgrComboBox(), null);
			jPanel2.add(getHiredateTextField(), null);
			jPanel2.add(getSalTextField(), null);
			jPanel2.add(getCommTextField(), null);
			jPanel2.add(getDeptnoComboBox(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes empTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEmpTextField() {
		if (empTextField == null) {
			empTextField = new JTextField();
		}
		return empTextField;
	}

	/**
	 * This method initializes enameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEnameTextField() {
		if (enameTextField == null) {
			enameTextField = new JTextField();
		}
		return enameTextField;
	}

	/**
	 * This method initializes jobTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJobTextField() {
		if (jobTextField == null) {
			jobTextField = new JTextField();
		}
		return jobTextField;
	}

	/**
	 * This method initializes mgrComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox<Integer> getMgrComboBox() {
		if (mgrComboBox == null) {
			mgrComboBox = new JComboBox<Integer>();
			mgrComboBox.addItemListener(new java.awt.event.ItemListener() {
				@SuppressWarnings("unchecked")
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					JComboBox<Integer> combo = (JComboBox<Integer>)e.getSource();
					Integer data = (Integer)combo.getSelectedItem();
					mgr = data.intValue();//1.4이전버전이면 mgr=data.intValue();로 해야함
				}
			});
		}
		return mgrComboBox;
	}

	/**
	 * This method initializes hiredateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHiredateTextField() {
		if (hiredateTextField == null) {
			hiredateTextField = new JTextField();
			hiredateTextField.setToolTipText("2015-01-01");
		}
		return hiredateTextField;
	}

	/**
	 * This method initializes salTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSalTextField() {
		if (salTextField == null) {
			salTextField = new JTextField();
		}
		return salTextField;
	}

	/**
	 * This method initializes commTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCommTextField() {
		if (commTextField == null) {
			commTextField = new JTextField();
		}
		return commTextField;
	}

	/**
	 * This method initializes deptnoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox<DeptVO> getDeptnoComboBox() {
		if (deptnoComboBox == null) {
			deptnoComboBox = new JComboBox<DeptVO>();
			deptnoComboBox.addItemListener(new java.awt.event.ItemListener() {
				@SuppressWarnings("unchecked")
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					JComboBox<DeptVO> combo = (JComboBox<DeptVO>)e.getSource();
					DeptVO data = (DeptVO)combo.getSelectedItem();
					//System.out.println(data.getDeptno());
					deptno = data.getDeptno();
				}
			});
		}
		return deptnoComboBox;
	}

	private void clearField() {
		getEmpTextField().setText("");
		getEnameTextField().setText("");
		getJobTextField().setText("");
		getHiredateTextField().setText("");
		getSalTextField().setText("");
		getCommTextField().setText("");
		//setMgrList();
		//setDeptNoList();
	}
	private void setMgrList() {
		//데이터베이스에서 DeptNo 조회해서...
		ArrayList<Integer> listData = empDao.getAllEmpno();
		//getMgrComboBox().removeAllItems();
		for(Integer data : listData) {
			getMgrComboBox().addItem(data);
		}
	}	
	private void setDeptNoList() {
		//데이터베이스에서 DeptNo 조회해서...
		ArrayList<DeptVO> listData = deptDao.getAllDepts();
		//getDeptnoComboBox().removeAllItems();
		for(DeptVO data : listData) {
			getDeptnoComboBox().addItem(data);
		}
	}
}

