package com.rj.j2ee.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


public class PreparedStatementTest2 {
	private static final String SQL_FIND_STUDENTNAME_BY_ID = "SELECT STUDENT_NAME FROM STUDENT WHERE STUDENT_ID = ?";
	private static final String SQL_SELECT_STUDENT = "SELECT * FROM STUDENT";
	private static final String SQL_PROCEDURE_INSERT_STUDENT = "{ CALL INSERT_STUDENT(?,?,?)}";
	private static final String SQL_FUNCTION_GET_STUDENT_NAME_BY_ID = "{ call GET_STUDENT_NAME(?) }";
	private static final String SQL_INSERT_STUDENT = "{CALL INSERT_STUDENT(?,?,?,?)}";
	private static final String SQL_SHOW_STUDENTSDATA = "{CALL SHOW_STUDENTS_DATA()}";
	private static final String SQL_DELETE_PATTERN = "{?= CALL DELETE_STUDENTS_NAME_LIKE(?)}";
	private PreparedStatement ps;
	private CallableStatement cs;
	/*private  DataSource dataSource;*/
	@Autowired
	private Connection con;
	private Statement st;
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		long stime = System.currentTimeMillis();
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		PreparedStatementTest2 pst = ctx.getBean(PreparedStatementTest2.class);
		pst.insert_student(1, "RAJU", 1001);
		int deleteCount = pst.deleteStudentsNameLike("%R%");
		int studentCount =	pst.showStudentData();
		System.out.println("Delete Count: "+deleteCount);
		System.out.println("Total Student Count: "+studentCount);
		System.out.println(System.currentTimeMillis()-stime);
		//((AbstractApplicationContext) ctx).close();
	}
	
	public int deleteStudentsNameLike(String pattern) throws SQLException {
		cs=con.prepareCall(SQL_DELETE_PATTERN);
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setString(2, pattern);
		cs.execute();
		return (Integer) cs.getInt(1);
	}
	
	public int showStudentData() throws SQLException {
		cs = con.prepareCall(SQL_SHOW_STUDENTSDATA);
		cs.execute();		
		ResultSet rs = cs.getResultSet();
		while(rs.next())
			System.out.println(rs.getInt(1)+"  |  "+rs.getString(2));
		cs.getMoreResults();
		rs=cs.getResultSet();
		rs.next();
		return rs.getInt(1);
	}
	public boolean insert_student(int studentId,String studentName ,int courseId) throws SQLException {
		cs = con.prepareCall(SQL_INSERT_STUDENT);
		cs.setInt(1, studentId);
		cs.setString(2, studentName);
		cs.setInt(3,courseId);
		cs.registerOutParameter(4, Types.BOOLEAN);
		cs.execute();
		boolean sts =cs.getBoolean(4);
		if(sts)
			System.out.println("Record Inserted");
		else
			System.out.println("Error in record Insertion");
		return sts;
	}
	
	
}
