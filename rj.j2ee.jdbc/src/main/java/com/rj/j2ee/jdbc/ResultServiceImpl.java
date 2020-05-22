package com.rj.j2ee.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultServiceImpl implements ResultService {
	@Autowired
	private StudentDAO studentDAO;

	public List<Integer> getStudentIds() throws SQLException {
		return studentDAO.getStudentIds();
	}

	public Student getStudentDetails(Integer studentId) throws SQLException {
		return studentDAO.getStudentDetails(studentId);
	}

}
