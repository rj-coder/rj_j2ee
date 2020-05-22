package com.rj.j2ee.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StudentDAOImpl implements StudentDAO {
	private static final String SQL_GET_STUDENT_IDS = "SELECT STUDENT_ID FROM STUDENT";
	private static final String SQL_GET_STUDENT_DETAILS = "SELECT STUDENT_ID, STUDENT_NAME, SUBJECT1, SUBJECT2, SUBJECT3 FROM STUDENT WHERE STUDENT_ID = ?";
	@Autowired
	private Connection con;
	@Autowired
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;

	public static void main(String[] args) throws BeansException, SQLException {
		
	}

	public List<Integer> getStudentIds() throws SQLException {
		rs = st.executeQuery(SQL_GET_STUDENT_IDS);
		List<Integer> sidList = new ArrayList<Integer>();
		while (rs.next())
			sidList.add(rs.getInt(1));
		return sidList;
	}

	public Student getStudentDetails(Integer studentId) throws SQLException {
		ps = con.prepareStatement(SQL_GET_STUDENT_DETAILS);
		ps.setInt(1, studentId);
		rs= ps.executeQuery();
		while (rs.next()) {
			return new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
		}
		return null;
	}
}
