package com.rj.j2ee.jdbc;

import java.sql.CallableStatement;
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
	private static final String SQL_PROCEDURE_INSERT_STUDENT = "{ CALL INSERT_STUDENT(?,?,?)}";
	private static final String SQL_FUNCTION_GET_STUDENT_NAME_BY_ID = "{ call GET_STUDENT_NAME(?) }";
	private static final String SQL_PROCEDURE_GET_STUDENT_NAME_WILD = "{CALL GET_STUDENT_NAME_WILD(?)}";
	/**
	 * DROP PROCEDURE GET_STUDENT_NAME_WILD IF EXISTS;
CREATE PROCEDURE GET_STUDENT_NAME_WILD(IN SNAME VARCHAR(20))
READS SQL DATA DYNAMIC RESULT SETS 2
BEGIN ATOMIC
    DECLARE thisCusor CURSOR WITH RETURN FOR 
    SELECT * FROM STUDENT WHERE STUDENT_NAME LIKE SNAME FOR READ ONLY;

    DECLARE thisCusor1 CURSOR WITH RETURN FOR 
    SELECT * FROM STUDENTCOURSE FOR READ ONLY;
   OPEN thisCusor;
   OPEN thisCusor1;

END
	 */

	private PreparedStatement ps;
	private CallableStatement cs;
	@Autowired
	private Connection con;
	private Statement st;

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		PreparedStatementTest pst = ctx.getBean(PreparedStatementTest.class);
		System.out.println(pst.con);
		/*
		 * int count = pst.insertStudentProcedure(15, "manohar");
		 * System.out.println(count); String studentName = pst.getStudentNameById(1);
		 * System.out.println("Student Name: " + studentName);
		 */
		int studentId = 5;
		System.out.println("Student Name By Id:"+studentId+" : "+pst.getStudentName(studentId));
		pst.getStudentNameWild("%");
		pst.showAllStudents();
		((AbstractApplicationContext) ctx).close();
	}
	
	public void getStudentNameWild(String wildpattern) throws SQLException {
		cs = con.prepareCall(SQL_PROCEDURE_GET_STUDENT_NAME_WILD);
		cs.setString(1, wildpattern);
		cs.execute();
		ResultSet rs = null ;
		while(!(cs.getMoreResults() == false && cs.getUpdateCount() == -1)) {
				rs = cs.getResultSet();
			System.out.println("in "+rs);
			while(rs != null && rs.next())
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));
			
		}
		System.out.println("done");
			
		
	
		
	}

	public String getStudentName(int studentId) throws SQLException {
		cs = con.prepareCall(SQL_FUNCTION_GET_STUDENT_NAME_BY_ID);
		cs.setInt(1, studentId);
		cs.execute();
		ResultSet rs = cs.getResultSet();
		return rs.next() ? rs.getString(1) : null;
	}

	public int insertStudentProcedure(int studentId, String studentName) throws SQLException {
		cs = con.prepareCall(SQL_PROCEDURE_INSERT_STUDENT);
		cs.setInt(1, studentId);
		cs.setString(2, studentName);
		// cs.registerOutParameter(3, java.sql.Types.INTEGER);
		cs.execute();
		return cs.getInt(3);
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
