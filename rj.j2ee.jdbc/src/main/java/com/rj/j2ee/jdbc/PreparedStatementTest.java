package com.rj.j2ee.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PreparedStatementTest {
	private static final String SQL_FIND_STUDENTNAME_BY_ID = "SELECT STUDENT_NAME FROM STUDENT WHERE STUDENT_ID = ?";
	private static final String SQL_INSERT_STUDENT = "INSERT INTO STUDENT VALUES (?,?)";
	private static final String SQL_SELECT_STUDENT = "SELECT * FROM STUDENT";
	private PreparedStatement ps;
	@Autowired
	private Connection con;
	private Statement st;

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		PreparedStatementTest pst = ctx.getBean(PreparedStatementTest.class);
		String studentName = pst.getStudentNameById(1);
		System.out.println("Student Name: " + studentName);
		/*int insertCount = pst.insertStudent(11, "vinay");
		System.out.println("Insert Count: " + insertCount);*/
		pst.showAllStudents();
		((AbstractApplicationContext) ctx).close();
	}

	public void showAllStudents() throws SQLException {
		st = con.createStatement();
		ResultSet rs = st.executeQuery(SQL_SELECT_STUDENT);
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " | " + rs.getString(2));
		}

	}
	
	private int insertStudent(int studentId, String studentName) throws SQLException {
		ps = con.prepareStatement(SQL_INSERT_STUDENT);
		ps.setInt(1, studentId);
		ps.setString(2, studentName);
		return ps.executeUpdate();
	}

	private String getStudentNameById(int studentId) throws SQLException {
		ps = con.prepareStatement(SQL_FIND_STUDENTNAME_BY_ID);
		ps.setInt(1, studentId);
		ResultSet rs = ps.executeQuery();
		return rs.next() ? rs.getString(1) : null;
	}
}
