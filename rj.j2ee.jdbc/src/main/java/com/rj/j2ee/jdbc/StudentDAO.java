package com.rj.j2ee.jdbc;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
	List<Integer> getStudentIds() throws SQLException;
	Student getStudentDetails(Integer studentId) throws SQLException;
}
