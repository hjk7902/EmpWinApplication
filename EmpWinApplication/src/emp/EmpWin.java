package emp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import emp.model.EmpDAO;
import emp.model.EmpVO;
import emp.model.IEmpDAO;

public class EmpWin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton selectButton = null;
	private JButton inputButton = null;
	private JButton deleteButton = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private int selectedRow = -1;
	private JButton modifyButton = null;
	private JLabel messageLabel = null;
	private InputDialog dialog = new InputDialog(this);

	IEmpDAO dao = new EmpDAO();

	private JLabel getMessageLabel() {
		if(messageLabel == null) {
			int count = dao.getEmpCount();
			messageLabel = new JLabel("시스템 메시지..."+(count));
		}
		return messageLabel;
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
			jPanel.add(getInputButton(), null);
			jPanel.add(getModifyButton(), null);
			jPanel.add(getDeleteButton(), null);
			jPanel.add(getSelectButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes selectButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSelectButton() {
		if (selectButton == null) {
			selectButton = new JButton();
			selectButton.setName("");
			selectButton.setText("조회");
			selectButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMessageLabel().setText("조회합니다.");
					displayData();
				}
			});
		}
		return selectButton;
	}

	/**
	 * This method initializes inputButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInputButton() {
		if (inputButton == null) {
			inputButton = new JButton();
			inputButton.setText("입력");
			inputButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMessageLabel().setText("데이터 입력 다이얼로그 띄움");
					dialog.setVisible(true);
					//dialog가 modal속성이 true인경우 dispose()되어야 아래 진행된다.
					getMessageLabel().setText("데이터 입력 다이얼로그 닫힘");
					getMessageLabel().setText("데이터 새로고침");
					displayData();
				}
			});
		}
		return inputButton;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("삭제");
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(selectedRow >=0) {
						getMessageLabel().setText("사원정보를 삭제합니다.");
						String empnoStr = (String)getJTable().getValueAt(selectedRow, 0);
						int empno = Integer.parseInt(empnoStr);
						dao.deleteEmp(empno);
						selectedRow = -1;
						displayData();
					}else {
						getMessageLabel().setText("삭제할 레코드를 선택하세요");
					}
				}
			});
		}
		return deleteButton;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
				public void propertyChange(java.beans.PropertyChangeEvent e) {
					JTable jTable = (JTable)e.getSource();
					modifyButton.setEnabled(true);
					deleteButton.setEnabled(false);
					if(jTable.getEditingRow() >= 0) {
						modifyButton.setEnabled(false);
						deleteButton.setEnabled(true);
						getMessageLabel().setText((String) jTable.getValueAt(jTable.getEditingRow(), jTable.getEditingColumn()));
						getMessageLabel().setText("데이터를 수정합니다.");
						
						String empnoStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 0);
						String ename = (String)getJTable().getValueAt(jTable.getEditingRow(), 1);
						String job = (String)getJTable().getValueAt(jTable.getEditingRow(), 2);
						String mgrStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 3);
						String hiredateStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 4);
						String salStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 5);
						String commStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 6);
						String deptnoStr = (String)getJTable().getValueAt(jTable.getEditingRow(), 7);

						int empno = Integer.parseInt(empnoStr);
						EmpVO vo = new EmpVO();
						vo.setEname(ename);
						vo.setEmpno(empno);
						vo.setJob(job);
						vo.setMgr(Integer.parseInt(mgrStr));
						vo.setHiredate(java.sql.Date.valueOf(hiredateStr));
						vo.setSal(Double.parseDouble(salStr));
						if(commStr == null || commStr.equals("")) {
							vo.setComm(-1);
						}else {
							vo.setComm(Double.parseDouble(commStr));
						}
						vo.setDeptno(Integer.parseInt(deptnoStr));
						try {
							dao.updateEmp(vo);
						}catch(Exception ex) {
							getMessageLabel().setText(ex.getMessage());
						}
					}
				}
			});
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					getMessageLabel().setText("mouseClicked()"); 
					selectedRow = getJTable().getSelectedRow();
					getMessageLabel().setText((String) getJTable().getValueAt(selectedRow, 1));
				}
			});
		}
		return jTable;
	}

	/**
	 * This method initializes modifyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifyButton() {
		if (modifyButton == null) {
			modifyButton = new JButton();
			modifyButton.setText("수정");
			modifyButton.setEnabled(false);
		}
		return modifyButton;
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				EmpWin thisClass = new EmpWin();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public EmpWin() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(570, 250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = dim.width;
		int y = dim.height;
		x = x - this.getWidth()/2;
		y = y - this.getHeight()/2;
		//getMessageLabel().setText(x + " " + y);
		//this.setLocation(x, y);
		
		this.setContentPane(getJContentPane());
		this.setTitle("EMP");
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
			jContentPane.add(getMessageLabel(), BorderLayout.NORTH);
			jContentPane.add(getJPanel(), BorderLayout.SOUTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private void displayData() {
		try {
			ArrayList<EmpVO> emps = dao.getAllEmps();
			ArrayList<String> columns = dao.getAllColumnNames();

			//JTable의 데이터
			String[][] data = new String[emps.size()][columns.size()];
			for(int i=0; i<data.length; i++) {
				data[i] = emps.get(i).toArray();
			}

			//JTable의 열 이름
			String[] columnNames = new String[columns.size()];
			columns.toArray(columnNames); 
			
			DefaultTableModel model = new DefaultTableModel(data, columnNames) ;
			getJTable().setModel(model);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			getMessageLabel().setText(e1.getMessage());
		}
		getModifyButton().setEnabled(false);
		getDeleteButton().setEnabled(true);
	}
} 