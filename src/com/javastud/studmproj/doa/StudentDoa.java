package com.javastud.studmproj.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.javastud.studmproj.model.Student;

public class StudentDoa {
	private Connection conn = null;

	public StudentDoa() throws ClassNotFoundException, SQLException {

		conn = MysqlConnection.getConnection();

	}

	public void saveStud(Student stud) throws SQLException {

		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO student(name, address, birthdate, rollno, faculty, semester, collegename, gender) VALUES (?,?,?,?,?,?,?,?)");

		stmt.setString(1, stud.getName());
		stmt.setString(2, stud.getAddress());
		stmt.setDate(3, new java.sql.Date(stud.getBirthDate().getTime()));
		stmt.setString(4, stud.getRollno());
		stmt.setString(5, stud.getFaculty());
		stmt.setString(6, stud.getSemester());
		stmt.setString(7, stud.getCollegeName());
		stmt.setString(8, stud.getGender());

		stmt.executeUpdate();
	}

	public List<Student> getAllStudents() throws SQLException {

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT *FROM student");

		List<Student> students = new ArrayList<Student>();

		while (rs.next()) {
			Student stud = copyResultToStudent(rs);
			students.add(stud);

		}

		return students;

	}

	private Student copyResultToStudent(ResultSet rs) throws SQLException {
		Student stud = new Student();

		stud.setId(rs.getInt("id"));
		stud.setName(rs.getString("name"));

		stud.setBirthDate(rs.getDate("birthdate"));

		stud.setRollno(rs.getString("rollno"));
		stud.setFaculty(rs.getString("faculty"));
		stud.setSemester(rs.getString("semester"));
		stud.setCollegeName(rs.getString("collegename"));
		stud.setGender(rs.getString("gender"));
		stud.setAddress(rs.getString("address"));

		// for testing in searched sql
		// int a=stud.getId();
		// JOptionPane.showMessageDialog(null, a);

		return stud;
	}

	public List<Student> searchStudent(String searchTxt, String searchKey)
			throws SQLException {

		// SQL MISTAKE

		PreparedStatement stmt = conn
				.prepareStatement("SELECT *FROM student WHERE ? = ?");
		stmt.setString(1, searchKey);
		stmt.setString(2, searchTxt);

		// stmt.setString(2, "%"+ searchTxt+ "%" );

		// works
		// PreparedStatement stmt =
		// conn.prepareStatement("SELECT *FROM student WHERE  name = ?");
		// stmt.setString(1, searchTxt );

		ResultSet rs = stmt.executeQuery();

		List<Student> students = new ArrayList<Student>();

		while (rs.next()) {

			students.add(copyResultToStudent(rs));
		}

		return students;
	}

	public boolean deleteStudent(int id) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM student where id=?");
		stmt.setInt(1, id);
		boolean b = stmt.execute();
		return b;
	}

	public Student getInsertedStudent(int id) throws SQLException {

		// Statement stmt = conn.createStatement();
		// ResultSet rs =
		// stmt.executeQuery("SELECT *FROM student where id ="+id);

		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM student where id=?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		rs.next();

		Student stud = copyResultToStudent(rs);
		return stud;
	}

	public void updateStud(Student stud) throws SQLException {
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE student SET name=?, address=?, birthdate=?, rollno=?, faculty=?, semester=?, collegename=?, gender=? WHERE id=?");
		stmt.setString(1, stud.getName());
		stmt.setString(2, stud.getAddress());
		stmt.setDate(3, new java.sql.Date(stud.getBirthDate().getTime()));
		stmt.setString(4, stud.getRollno());
		stmt.setString(5, stud.getFaculty());
		stmt.setString(6, stud.getSemester());
		stmt.setString(7, stud.getCollegeName());
		stmt.setString(8, stud.getGender());
		stmt.setInt(9, stud.getId());

		stmt.executeUpdate();
	}

}
