package com.rj.j2ee.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ResultController {
	@Autowired
	ResultService resultService;
	
	public List<Integer> getStudentIds() throws SQLException {		
		return resultService.getStudentIds();	
	}
	
	public Student getStudentDetails(Integer studentId) throws SQLException {
		return resultService.getStudentDetails(studentId);
	}

}
