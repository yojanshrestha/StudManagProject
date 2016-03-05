package com.javastud.studmproj;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.javastud.studmproj.doa.StudentDoa;
import com.javastud.studmproj.model.Student;

public class StudentManagement extends JFrame {

	private JPanel contentPane;
	private JLabel lblAdmin;
	private JLabel activeUser;
	private JButton btnLogout;
	private JPanel panel;
	private JLabel lblName;
	private JLabel lblBirthdate;
	private JLabel lblRollNo;
	private JTextField nameTxt;
	private JTextField birthDateTxt;
	private JTextField rollNoTxt;
	private JLabel lblFaculty;
	private JLabel lblCollegeName;
	private JLabel lblSemester;
	private JTextField facultyTxt;
	private JTextField collegeNameTxt;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JPanel panel_1;
	private JLabel lblAddress;
	private JTextField addressTxt;
	private JButton saveBtn;
	private JButton btnExit;
	private JTable studTable;
	private JScrollPane scrollPane;

	private StudentDoa studDoa = null;
	private JComboBox searchComboBox;
	private JTextField searchTxt;
	private JButton btnSearch;
	private JButton btnViewAll;
	private JButton btnDelete;
	private JComboBox semesterComboBox;
	private JButton btnEdit;
	private JButton btnUpdate;
	private JTextField idTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement frame = new StudentManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 20, 900, 700);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAdmin());
		contentPane.add(getActiveUser());
		contentPane.add(getBtnLogout());
		contentPane.add(getPanel());
		contentPane.add(getBtnExit());
		contentPane.add(getScrollPane());
		contentPane.add(getSearchComboBox());
		contentPane.add(getSearchTxt());
		contentPane.add(getBtnSearch());
		contentPane.add(getBtnViewAll());
		contentPane.add(getBtnDelete());
		contentPane.add(getBtnEdit());

		try {
			studDoa = new StudentDoa();
			showAllStudents();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public StudentManagement(String activeUser) {
		this();
		this.getActiveUser().setText(activeUser);

	}

	private JLabel getLblAdmin() {
		if (lblAdmin == null) {
			lblAdmin = new JLabel("Active User");
			lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblAdmin.setForeground(Color.DARK_GRAY);
			lblAdmin.setBounds(279, 16, 73, 14);
		}
		return lblAdmin;
	}

	private JLabel getActiveUser() {
		if (activeUser == null) {
			activeUser = new JLabel("Admin");
			activeUser.setForeground(Color.BLUE);
			activeUser.setFont(new Font("Tahoma", Font.BOLD, 13));
			activeUser.setBounds(362, 16, 73, 14);
		}
		return activeUser;
	}

	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setIcon(new ImageIcon("resource\\logout.png"));
			btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
			btnLogout.setForeground(Color.RED);
			btnLogout.setBounds(528, 7, 100, 23);

			btnLogout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					switchToLoginScreen();
				}
			});

		}
		return btnLogout;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "StudentForm",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(123, 59, 622, 204);
			panel.setLayout(null);
			panel.add(getLblName());
			panel.add(getPanel_1());
			panel.add(getLblBirthdate());
			panel.add(getLblRollNo());
			panel.add(getNameTxt());
			panel.add(getBirthDateTxt());
			panel.add(getRollNoTxt());
			panel.add(getLblFaculty());
			panel.add(getLblCollegeName());
			panel.add(getLblSemester());
			panel.add(getFacultyTxt());
			panel.add(getCollegeNameTxt());
			panel.add(getLblAddress());
			panel.add(getAddressTxt());
			panel.add(getSaveBtn());
			panel.add(getSemesterComboBox());
			panel.add(getBtnUpdate());
			panel.add(getIdTxt());
		}
		return panel;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name");
			lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblName.setBounds(28, 37, 46, 14);
		}
		return lblName;
	}

	private JLabel getLblBirthdate() {
		if (lblBirthdate == null) {
			lblBirthdate = new JLabel("Birth Date");
			lblBirthdate.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblBirthdate.setBounds(223, 40, 71, 14);
		}
		return lblBirthdate;
	}

	private JLabel getLblRollNo() {
		if (lblRollNo == null) {
			lblRollNo = new JLabel("Roll No");
			lblRollNo.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblRollNo.setBounds(443, 40, 46, 14);
		}
		return lblRollNo;
	}

	private JTextField getNameTxt() {
		if (nameTxt == null) {
			nameTxt = new JTextField();
			nameTxt.setBounds(76, 35, 118, 20);
			nameTxt.setColumns(10);
		}
		return nameTxt;
	}

	private JTextField getBirthDateTxt() {
		if (birthDateTxt == null) {
			birthDateTxt = new JTextField();
			birthDateTxt.setBounds(294, 38, 118, 20);
			birthDateTxt.setColumns(10);
		}
		return birthDateTxt;
	}

	private JTextField getRollNoTxt() {
		if (rollNoTxt == null) {
			rollNoTxt = new JTextField();
			rollNoTxt.setBounds(499, 37, 113, 20);
			rollNoTxt.setColumns(10);
		}
		return rollNoTxt;
	}

	private JLabel getLblFaculty() {
		if (lblFaculty == null) {
			lblFaculty = new JLabel("Faculty");
			lblFaculty.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFaculty.setBounds(28, 85, 46, 14);
		}
		return lblFaculty;
	}

	private JLabel getLblCollegeName() {
		if (lblCollegeName == null) {
			lblCollegeName = new JLabel("College Name");
			lblCollegeName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblCollegeName.setBounds(407, 85, 90, 14);
		}
		return lblCollegeName;
	}

	private JLabel getLblSemester() {
		if (lblSemester == null) {
			lblSemester = new JLabel("Semester");
			lblSemester.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblSemester.setBounds(223, 85, 57, 14);
		}
		return lblSemester;
	}

	private JTextField getFacultyTxt() {
		if (facultyTxt == null) {
			facultyTxt = new JTextField();
			facultyTxt.setBounds(76, 83, 118, 20);
			facultyTxt.setColumns(10);
		}
		return facultyTxt;
	}

	private JTextField getCollegeNameTxt() {
		if (collegeNameTxt == null) {
			collegeNameTxt = new JTextField();
			collegeNameTxt.setBounds(499, 83, 109, 20);
			collegeNameTxt.setColumns(10);
		}
		return collegeNameTxt;
	}

	private JRadioButton getMaleRadio() {
		if (maleRadio == null) {
			maleRadio = new JRadioButton("Male");
			maleRadio.setBounds(18, 20, 57, 23);
		}
		return maleRadio;
	}

	private JRadioButton getFemaleRadio() {
		if (femaleRadio == null) {
			femaleRadio = new JRadioButton("Female");
			femaleRadio.setBounds(96, 20, 74, 23);
		}
		return femaleRadio;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Gender",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(37, 114, 190, 57);
			panel_1.setLayout(null);
			panel_1.add(getMaleRadio());
			panel_1.add(getFemaleRadio());

			ButtonGroup bt = new ButtonGroup();
			bt.add(getMaleRadio());
			bt.add(getFemaleRadio());

		}
		return panel_1;
	}

	private JLabel getLblAddress() {
		if (lblAddress == null) {
			lblAddress = new JLabel("Address");
			lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblAddress.setBounds(318, 135, 62, 14);
		}
		return lblAddress;
	}

	private JTextField getAddressTxt() {
		if (addressTxt == null) {
			addressTxt = new JTextField();
			addressTxt.setBounds(390, 133, 122, 20);
			addressTxt.setColumns(10);
		}
		return addressTxt;
	}

	private JButton getSaveBtn() {
		if (saveBtn == null) {
			saveBtn = new JButton("Save");
			saveBtn.setIcon(new ImageIcon("resource\\save.png"));
			saveBtn.setBounds(259, 170, 89, 23);
			getBtnUpdate().setVisible(false);
			saveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Student stud = getStudentFormData();
						if (stud != null) {
							// studDoa = new StudentDoa(); already initialized
							// in
							// constructor
							studDoa.saveStud(stud);
							clearData();
							showAllStudents();

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			});

		}
		return saveBtn;
	}

	private void clearData() {
		nameTxt.setText("");
		birthDateTxt.setText("");
		rollNoTxt.setText("");
		facultyTxt.setText("");
		collegeNameTxt.setText("");
		nameTxt.setText("");
		addressTxt.setText("");
		semesterComboBox.setSelectedIndex(0);
		maleRadio.setSelected(false);
		femaleRadio.setSelected(false);
	}

	private void showAllStudents() throws SQLException {
		DefaultTableModel model;
		model = clearTable();

		// fetch record and show in table
		List<Student> students = studDoa.getAllStudents();
		addStudentRowRecord(model, students);

	}

	private DefaultTableModel clearTable() {
		DefaultTableModel model = (DefaultTableModel) studTable.getModel();
		model.setRowCount(0);
		return model;
	}

	private void addStudentRowRecord(DefaultTableModel model,
			List<Student> students) {
		for (Student stud : students) {
			model.addRow(new Object[] { stud.getId(), stud.getName(),
					stud.getBirthDate(), stud.getRollno(), stud.getGender(),
					stud.getCollegeName(), stud.getFaculty(),
					stud.getSemester(), stud.getAddress() });
		}

	}

	private Student getStudentFormData() {
		Student stud = new Student();

		stud.setName(nameTxt.getText());
		try {
			stud.setBirthDate(new SimpleDateFormat("YYYY-MM-DD")
					.parse(birthDateTxt.getText()));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null,
					"Birth Date has a invalid format !!");

			e.printStackTrace();
		}
		stud.setRollno(rollNoTxt.getText());
		stud.setFaculty(facultyTxt.getText());
		stud.setCollegeName(collegeNameTxt.getText());
		stud.setSemester((String) semesterComboBox.getSelectedItem());
		stud.setAddress(addressTxt.getText());

		if (maleRadio.isSelected()) {
			stud.setGender("Male");
		} else {
			stud.setGender("Female");
		}

		// stud.setName(getNameTxt().getText());
		// stud.setBirthDate(new
		// SimpleDateFormat("YYYY-MM-DD").parse(getBirthDateTxt().getText()));
		// stud.setRollno(getRollNoTxt().getText());
		// stud.setFaculty(getFacultyTxt().getText());
		// stud.setCollegeName(getCollegeNameTxt().getText());
		// stud.setSemester(getSemesterTxt().getText());
		// stud.setAddress(getAddressTxt().getText());
		//
		// if (getMaleRadio().isSelected()) {
		// stud.setGender("Male");
		// }else{
		// stud.setGender("Female");
		// }

		if (nameTxt.getText().equals("")
				|| rollNoTxt.getText().equals("")
				|| facultyTxt.getText().equals("")
				|| collegeNameTxt.getText().equals("")
				|| ((String) semesterComboBox.getSelectedItem())
						.equals("Select Semester")
				|| addressTxt.getText().equals("")
				|| maleRadio.isSelected() == false
				&& femaleRadio.isSelected() == false) {
			JOptionPane.showMessageDialog(null,
					"Blank Fields are not allowed!!");
			return null;
		} else {
			return stud;
		}
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.setHorizontalAlignment(SwingConstants.LEFT);
			btnExit.setIcon(new ImageIcon("resource\\exit2.png"));
			btnExit.setForeground(Color.RED);
			btnExit.setBounds(673, 628, 95, 28);

			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null,
							"Do you want to exit.", null,
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						WindowManager.ui.clear();
						System.exit(0);
					}
				}
			});

		}
		return btnExit;
	}

	private JTable getStudTable() {
		if (studTable == null) {
			studTable = new JTable();
			studTable.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "ID", "Name", "Birth Date", "Roll No",
							"Gender", "College Name", "Faculty", "Semester",
							"Address" }));
		}
		return studTable;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(72, 453, 751, 147);
			scrollPane.setViewportView(getStudTable());
		}
		return scrollPane;
	}

	private void switchToLoginScreen() {
		Preferences prefs = Preferences.userNodeForPackage(getClass());
		try {
			prefs.clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		// if ((Preferences) WindowManager.ui.get("pref") != null) {
		// try {
		// Preferences prefs = (Preferences) WindowManager.ui.get("pref");
		// // prefs.put("userName", "aaaa");
		// prefs.clear();
		// WindowManager.ui.put("pref", prefs);
		// } catch (BackingStoreException e) {
		// e.printStackTrace();
		// }
		//
		// }
		LoginScreenLayout loginWindow = (LoginScreenLayout) WindowManager.ui
				.get("LoginScreenLayout");
		loginWindow.setVisible(true);

		StudentManagement stuMangWindow = (StudentManagement) WindowManager.ui
				.get("StudentManagement");
		stuMangWindow.dispose();

	}

	private JComboBox getSearchComboBox() {
		if (searchComboBox == null) {
			searchComboBox = new JComboBox();
			searchComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
			searchComboBox.setModel(new DefaultComboBoxModel(new String[] {
					"name", "id", "birth Date", "address", "gender",
					"collegename", "faculty", "semester", "rollno" }));
			searchComboBox.setBounds(303, 332, 89, 23);
		}
		return searchComboBox;
	}

	private JTextField getSearchTxt() {
		if (searchTxt == null) {
			searchTxt = new JTextField();
			searchTxt.setBounds(111, 332, 165, 23);
			searchTxt.setColumns(10);
		}
		return searchTxt;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("Search");
			btnSearch.setHorizontalAlignment(SwingConstants.LEFT);
			btnSearch.setIcon(new ImageIcon("resource\\search.png"));
			btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnSearch.setBounds(402, 332, 100, 23);

			btnSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						List<Student> students = studDoa.searchStudent(
								searchTxt.getText(),
								(String) searchComboBox.getSelectedItem());

						showSearchedStudent(students);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			});

		}
		return btnSearch;
	}

	private void showSearchedStudent(List<Student> students) {

		DefaultTableModel model;
		model = clearTable();
		addStudentRowRecord(model, students);

	}

	private JButton getBtnViewAll() {
		if (btnViewAll == null) {
			btnViewAll = new JButton("View All");
			btnViewAll.setIcon(new ImageIcon("resource\\viewall.png"));
			btnViewAll.setHorizontalAlignment(SwingConstants.LEFT);
			btnViewAll.setBounds(528, 333, 108, 23);
			btnViewAll.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						showAllStudents();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			});
		}
		return btnViewAll;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
			btnDelete.setIcon(new ImageIcon("resource\\delete.png"));
			btnDelete.setBounds(297, 401, 95, 23);
			btnDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int count = studTable.getSelectedRowCount();
					if (count == 0) {
						JOptionPane.showMessageDialog(null,
								"Please select row to be deleted!! ");
					} else if (count == 1) {
						int row = studTable.getSelectedRow();
						deleteSingleRow(row);
					} else {
						int rows[] = studTable.getSelectedRows();
						deleteMultipleRow(rows);
					}
				}
			});
		}
		return btnDelete;
	}
	
	private void deleteMultipleRow(int rows[]){
		int j=0;
		for (int i : rows) {			
			deleteSingleRow(i-j);
			j++;
		}
		
	}

	private void deleteSingleRow(int row) {

		int option = JOptionPane.showConfirmDialog(null,
				"Are you sure want to delete record!! ", null,
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			int id = (int) studTable.getValueAt(row, 0);

			try {
				boolean b = studDoa.deleteStudent(id);

				if (b) {
					JOptionPane.showMessageDialog(null,
							"Student Record Deleted Successfully of id=" + id);
				} else {
					JOptionPane.showMessageDialog(null,
							"ERROR!! Record cannot be deleted. ");
				}

				showAllStudents();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	private JComboBox getSemesterComboBox() {
		if (semesterComboBox == null) {
			semesterComboBox = new JComboBox();
			semesterComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
			semesterComboBox.setModel(new DefaultComboBoxModel(new String[] {
					"Select Semester", "First", "Second", "Third", "Fourth",
					"Fifth", "Sixth", "Seventh", "Eighth" }));
			semesterComboBox.setBounds(294, 82, 103, 22);
		}
		return semesterComboBox;
	}

	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Edit");
			btnEdit.setIcon(new ImageIcon("resource\\edit.png"));
			btnEdit.setHorizontalAlignment(SwingConstants.LEFT);
			btnEdit.setBounds(176, 401, 100, 23);
			btnEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					btnUpdate.setVisible(true);
					saveBtn.setVisible(false);
					int row = studTable.getSelectedRow();
					int id = (int) studTable.getValueAt(row, 0);
					try {
						Student stud = studDoa.getInsertedStudent(id);
						idTxt.setText(Integer.toString(id));
						setStudentInfo(stud);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnEdit;
	}

	private void setStudentInfo(Student stud) {
		nameTxt.setText(stud.getName());
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
		String dateString = df.format(stud.getBirthDate());
		birthDateTxt.setText(dateString);
		rollNoTxt.setText(stud.getRollno());
		facultyTxt.setText(stud.getFaculty());
		semesterComboBox.setSelectedItem((Object) stud.getSemester());
		collegeNameTxt.setText(stud.getCollegeName());
		addressTxt.setText(stud.getAddress());
		if (stud.getGender().equals("Male")) {
			maleRadio.setSelected(true);
		} else {
			femaleRadio.setSelected(true);
		}

	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("Update");
			btnUpdate.setIcon(new ImageIcon("resource\\save.png"));
			btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
			btnUpdate.setBounds(259, 170, 100, 23);
			btnUpdate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Student stud = new Student();
						stud = getStudentFormData();
						stud.setId(Integer.parseInt(idTxt.getText()));
						if (stud != null) {
							if (studDoa.updateStud(stud) == true) {
								JOptionPane.showMessageDialog(null,
										"Record updated successfully!! ");
							} else {
								JOptionPane.showMessageDialog(null,
										"ERROR!! Record not updated!! ");
							}
							clearData();
							saveBtn.setVisible(true);
							btnUpdate.setVisible(false);
							showAllStudents();
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

		}
		return btnUpdate;
	}

	private JTextField getIdTxt() {
		if (idTxt == null) {
			idTxt = new JTextField();
			idTxt.setBounds(79, 171, 86, 20);
			idTxt.setColumns(10);
			idTxt.setVisible(false);
		}
		return idTxt;
	}
}
